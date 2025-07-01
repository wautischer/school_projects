import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class AgarPanel extends JPanel implements KeyListener, ActionListener {
    public int playerSIZE = 40;
    public int xOval = 300;
    public int yOval = 300;
    public int speed = 15;

    public static final int POINTS = 500;

    public static final int POINTSSIZE = 10;
    private int[] pX = new int[POINTS];
    private int[] pY = new int[POINTS];
    private boolean[] pAlive = new boolean[POINTS];
    private int counter = 50;

    Timer timer;

    public int score;
    public int vorgegScore = 750;
    public boolean winner = false;
    public boolean lose = false;

    //Enemy
    Random rand = new Random();
    public float speedEn;
    public double angel;
    public float xVRect;
    public float yVRect;
    public float xRect;
    public float yRect;
    public boolean isInit = false;
    public int EnemySIZE = 30;

    public static boolean isPlayerOnPoint(int xOval, int yOval, int playerSIZE, int pX, int pY, int POINTSSIZE) {
        return xOval + playerSIZE >= pX &&
                xOval <= pX + POINTSSIZE &&
                yOval + playerSIZE >= pY &&
                yOval <= pY + POINTSSIZE;
    }
    private void initRechteck(){
        xRect = 10;
        yRect = 10;
        angel = rand.nextDouble() * 2 * Math.PI;
        xVRect = (float) (speedEn * Math.cos(angel));
        yVRect = (float) (speedEn * Math.sin(angel));
    }

    public AgarPanel() {
        setPreferredSize(new Dimension(600,600));
        addKeyListener(this);
        setFocusable(true);


        timer = new Timer(1000 / 60, this);
        timer.start();

        for (int i = 0; i < POINTS; i++) {
            pX[i] = (int) (Math.random() * 590);
            pY[i] = (int) (Math.random() * 590);
        }

        for (int i = 0; i < counter ; i++) {
            pAlive[i] = true;
        }


        xRect = 20;
        yRect = 20;
        speedEn = (float) 3.4;
        winner = false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setBackground(Color.BLACK);
        g2d.clearRect(0,0,getWidth(),getHeight());

        if (!winner && !lose) {
            //Player
            g2d.setColor(Color.WHITE);
            g2d.fillOval(xOval, yOval, playerSIZE, playerSIZE);

            //Points
            for (int i = 0; i < POINTS; i++) {
                if (pAlive[i]) {
                    if (i % 10 == 0) {
                        g2d.setColor(Color.RED);
                        g2d.fillOval(pX[i], pY[i], POINTSSIZE * 2, POINTSSIZE * 2);
                    }
                    else {
                        g2d.setColor(Color.GREEN);
                        g2d.fillOval(pX[i], pY[i], POINTSSIZE, POINTSSIZE);
                    }
                }
            }

            //Score
            g2d.setFont(new Font("Courier", Font.BOLD, 15));
            g2d.setColor(Color.white);
            g2d.drawString("Score:" + score + "/" + vorgegScore, getWidth() - 140, 20);
            if (score == vorgegScore){
                winner = true;
                repaint();
            }
            if (playerSIZE < 0 || score < 0){
                lose = true;
            }

            //Enemy
            g2d.setColor(Color.red);
            g2d.fillOval((int) xRect, (int) yRect, EnemySIZE, EnemySIZE);
            if (!isInit) {
                isInit = true;
                initRechteck();
            }
            if (isPlayerOnPoint(xOval, yOval,playerSIZE, (int)xRect, (int)yRect, EnemySIZE)){
                lose = true;
            }

        }
        if (winner){
            g2d.setFont(new Font("Courier", Font.BOLD, 50));
            g2d.drawString("Game Finished", getWidth()/2-200,getHeight()/2);

        }
        if (lose) {
            g2d.setFont(new Font("Courier", Font.BOLD, 50));
            g2d.drawString("U trash boii", getWidth()/2-200,getHeight()/2);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //Player
        int code = e.getKeyCode();
        switch (code){
            case KeyEvent.VK_LEFT:
                if (xOval > 0) {
                    xOval -= speed;
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (xOval <= getWidth()-playerSIZE) {
                    xOval += speed;
                }
                break;
            case KeyEvent.VK_UP:
                if (yOval > 0) {
                    yOval -= speed;
                }
                break;
            case KeyEvent.VK_DOWN:
                if (yOval <= getHeight()-playerSIZE) {
                    yOval += speed;
                }
                break;
        }

        for (int i = 0; i < POINTS; i++) {
            if (pAlive[i]) {
                if (isPlayerOnPoint(xOval, yOval, playerSIZE, pX[i], pY[i], POINTSSIZE)) {
                    pAlive[i] = false;
                    if (i % 10 == 0) {
                        playerSIZE-= 10;
                        if (!winner){
                            score -= 100;
                        }
                    }
                    else {
                        playerSIZE += 1;
                    }
                    if (!winner) {
                        score += 10;
                    }
                }
            }
        }



        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {


    }

    long last = 0;
    @Override
    public void actionPerformed(ActionEvent e) {
        // 0.5sec timer
        long now = System.currentTimeMillis();
        if (now - last >= 500) {
            pAlive[counter] = true;
            counter++;
            last = now;
        }

        //Enemy
        xRect += xVRect;
        yRect += yVRect;
        if (xRect >= getWidth() - 30) {
            xVRect *= -1;
        }
        if (xRect <= 0) {
            xVRect *= -1;
        }
        if (yRect >= getHeight() - 30) {
            yVRect *= -1;
        }
        if (yRect <= 0) {
            yVRect *= -1;
        }
        if (isPlayerOnPoint((int) xRect, (int) yRect, 5, yOval, 10, 100)) {
            xVRect *= -1;
        }
        if (isPlayerOnPoint((int) xRect, (int) yRect, getWidth() - 45, yOval, 10, 100)) {
            xVRect *= -1;
        }
        repaint();
    }
}
