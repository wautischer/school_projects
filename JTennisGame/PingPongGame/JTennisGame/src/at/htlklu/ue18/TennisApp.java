package at.htlklu.ue18;

import javax.swing.*;

public class TennisApp {
    private JPanel content;

    public static void main(String[] args) {
        JFrame frame = new JFrame("TennisApp");
        frame.setContentPane(new TennisPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
