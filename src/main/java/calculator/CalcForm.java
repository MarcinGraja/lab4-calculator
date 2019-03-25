package calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalcForm extends JFrame {
    private JPanel mainPanel;
    private JButton evaluate;
    private JList operations;
    JTextArea equations;
    JTextField input;

    public CalcForm(){
        setMinimumSize(new Dimension(500,500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        equations.setEditable(false);
        setTitle("Calculator");
        setVisible(true);
        setContentPane(mainPanel);

        JMenu settings = new JMenu("Options");
        JButton exit = new JButton("Exit");
        JButton reset = new JButton("Reset");
        settings.add(reset);
        settings.add(exit);
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(settings);
        setJMenuBar(menuBar);
        exit.addActionListener(e -> System.exit(0));
        reset.addActionListener(e -> {
            equations.setText("");
            input.setText("");
        });
        evaluate.addActionListener(e -> {
            try{
                equations.append(CalcController.performCalculations(input.getText())+'\n');
                input.setText("");
            }
            catch (Exception exception){
                equations.append(exception.toString());
            }

        });
        input.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP){
                    input.setText(CalcController.getPreviousEquation());
                }
                else if (e.getKeyCode() == KeyEvent.VK_DOWN){
                    input.setText(CalcController.getNextEquation());
                }
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    try {
                        equations.append(CalcController.performCalculations(input.getText())+'\n');
                        input.setText("");
                    }
                    catch (Exception exception){
                        equations.append(exception.toString());
                    }
                }
            }
        });
    }
}