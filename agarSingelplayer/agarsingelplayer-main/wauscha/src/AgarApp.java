import javax.swing.*;

public class AgarApp {
    public static void main(String[] args) {
        JFrame frame = new JFrame("AgarApp");
        frame.setContentPane(new AgarPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }

    private JPanel content;
}
