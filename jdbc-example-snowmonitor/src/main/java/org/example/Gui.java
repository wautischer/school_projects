package org.example;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.text.NumberFormat;

public class Gui extends JFrame {
    public Gui() {
        JFrame frame = new JFrame("SnowMonitor");

        frame.setLayout(new GridLayout(10, 2));

        JLabel label = new JLabel("Ski Resort:");
        frame.add(label);

        String[] options = {"Option 1", "Option 2", "Option 3"};
        JComboBox<String> comboBox = new JComboBox<>(options);
        frame.add(comboBox);

        JLabel label_depth = new JLabel("Snow Depth");
        frame.add(label_depth);

        NumberFormat format = NumberFormat.getIntegerInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(Integer.MAX_VALUE);
        JFormattedTextField textField = new JFormattedTextField(formatter);
        textField.setColumns(3);
        frame.add(textField);


        JButton button = new JButton("Klick mich");
        frame.add(button);

        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Gui());
    }
}
