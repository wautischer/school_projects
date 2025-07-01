package at.htlklu.ue18;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class TennisPanel extends JPanel implements KeyListener, ActionListener {
    Timer timer;
    public boolean VIS;
    public int delta;
    public int spieler1pos;
    public int spieler2pos;
    public int count1;
    public int count2;
    public float xRect;
    public float yRect;
    public float xVRect;
    public float yVRect;
    public boolean isInit = false;
    public boolean move;
    public double angel;
    public float speed;
    public boolean winner;
    public String winnerP;
    public int winns1;
    public int winns2;
    Random rand = new Random();

    public TennisPanel() {
        this.setPreferredSize(new Dimension(800,570));
        this.addKeyListener(this);
        setFocusable(true);
        timer = new Timer(25,this);
        timer.start();
        VIS = false;
        delta = 40;
        spieler1pos = 0;
        spieler2pos = 0;
        count1 = 0;
        count2 = 0;
        xRect = 20;
        yRect = 20;
        speed = (float) 8.2;
        winner = false;
        winns1 = 0;
        winns2 = 0;

    }

    public boolean isPointInRect(int pX, int pY, int rX, int rY, int w, int h) {
        return pX >= rX && pX <= rX+w && pY >= rY && pY <= rY+h;
    }
    private void initRechteck(){
        xRect = getWidth()/2;
        yRect = getHeight()/2;
        angel = rand.nextDouble() * 2 * Math.PI;
        xVRect = (float) (speed * Math.cos(angel));
        yVRect = (float) (speed * Math.sin(angel));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.clearRect(0,0,getWidth(),getHeight());

        //Rahmen
        g2d.setStroke(new BasicStroke(6));
        g2d.setColor(Color.BLACK);
        g2d.drawRect(0,0,getWidth()-1,getHeight()-1);

        //Wenn Ball eingefügt wurde move = true.
        if (VIS){
            move = true;
        }
        //Spielstand
        if (!winner) {
            g2d.setColor(Color.green);
            g2d.setFont(new Font("ARIAL", Font.BOLD, 60));
            g2d.drawString(count1 + ":" + count2, getWidth() / 2 - 50, 60);
            if (xRect >= getWidth() - 30) {
                count1 += 1;
            }
            if (xRect <= 0) {
                count2 += 1;
            }
        }

        //Press SPACE to insert ball
        if (!VIS) {
            g2d.setColor(Color.blue);
            g2d.setFont(new Font("ARIAL", Font.BOLD, 40));
            g2d.drawString("Press SPACE to insert ball", getWidth() / 2 - 250, 380);
        }

        //Spieler 1
        if (!winner) {
            g2d.setColor(Color.orange);
            g2d.fillRect(5, spieler1pos, 10, 100);

        //Spieler 2
            g2d.setColor(Color.RED);
            g2d.fillRect(getWidth() - 15, spieler2pos, 10, 100);
        }

        //Ball
        if (!winner) {
            if (VIS) {
                g2d.setColor(Color.red);
                g2d.fillRect((int) xRect, (int) yRect, 30, 30);
            }
            if (!isInit) {
                isInit = true;
                initRechteck();
            }
        }

        //Reset
        if (!winner) {
            if (VIS) {
                g2d.setColor(Color.RED);
                g2d.setFont(new Font("ARIAL", Font.BOLD, 24));
                g2d.drawString("Press ENTER to reset", getWidth() / 2 - 120, getHeight() - 10);
            }
        }
        //Won Games
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("ARIAL",Font.BOLD,15));
        g2d.drawString("Won games:"+winns1,20,20);
        g2d.drawString("Won games:"+winns2,getWidth()-120,20);
        //Winner
        if (count1 == 13) {
            winnerP = "PLAYER1 WON!!";
            winner = true;
            //Won Games
            winns1 ++;
        }
        if (count2 == 13){
            winnerP = "PLAYER2 WON!!";
            winner = true;
            //Won Games
            winns2 ++;
        }
        if (winner){
            move = false;
            count1 = 0;
            count2 = 0;
            xRect = getWidth()/2;
            yRect = getHeight()/2;
            g2d.setColor(new Color(0,0,50));
            g2d.fillRect(0, 0, getWidth()-1, getHeight()-1);
            g2d.setColor(Color.yellow);
            g2d.setFont(new Font("ARIAL",Font.BOLD, 60));
            g2d.drawString(winnerP, getWidth()/2-220,getHeight()/2);
            g2d.setColor(Color.red);
            g2d.setFont(new Font("ARIAL",Font.BOLD,30));
            g2d.drawString("Press N to play again",getWidth()/2-140,getHeight()/2+180 );
        }

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        //Ball
        if (!winner) {
            if (move) {
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

                //Wurde der Ball von einem Spieler getroffen?
                if (isPointInRect((int) xRect, (int) yRect, 5, spieler1pos, 10, 100)) {
                    xVRect *= -1;
                }
                if (isPointInRect((int) xRect, (int) yRect, getWidth() - 45, spieler2pos, 10, 100)) {
                    xVRect *= -1;
                }
            }
        }
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //Insert
        if (!winner) {
            if (KeyEvent.VK_SPACE == e.getKeyCode()) {
                VIS = true;
            }
            //Reset
            if (KeyEvent.VK_ENTER == e.getKeyCode()) {
                VIS = false;
                count1 = 0;
                count2 = 0;
                move = false;
                angel = rand.nextDouble() * 2 * Math.PI;
                xVRect = (float) (speed * Math.cos(angel));
                yVRect = (float) (speed * Math.sin(angel));
            }
        }
        //Neues Spiel Starten
        if (KeyEvent.VK_N == e.getKeyCode()){
            winner = false;
            move = false;
            VIS = false;
        }
        //Spieler 1&2 bewegen
        int code = e.getKeyCode();
        switch (code){
            case KeyEvent.VK_W:
                if (spieler1pos >= 40) {
                    spieler1pos -= delta;
                }
                break;
            case KeyEvent.VK_S:
                if (spieler1pos <= getHeight() - 85) {
                    spieler1pos += delta;
                }
                break;
            case KeyEvent.VK_UP:
                if (spieler2pos >= 40) {
                    spieler2pos -= delta;
                }
                break;
            case KeyEvent.VK_DOWN:
                if (spieler2pos <= getHeight() - 85) {
                    spieler2pos += delta;
                }
                break;
        }
        repaint();

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
