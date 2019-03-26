package calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalcForm extends JFrame {
    private JPanel mainPanel;
    private JButton evaluate;
    private JList<Function> operations;
    JTextArea equations;
    JTextField input;

    public CalcForm() {
        setMinimumSize(new Dimension(500, 500));
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
            try {
                equations.append(CalcController.performCalculations(input.getText()) + '\n');
                input.setText("");
            } catch (Exception exception) {
                equations.append(exception.toString());
            }

        });
        input.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    input.setText(CalcController.getPreviousEquation());
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    input.setText(CalcController.getNextEquation());
                }
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        equations.append(CalcController.performCalculations(input.getText()) + '\n');
                        input.setText("");
                    } catch (Exception exception) {
                        equations.append(exception.toString());
                    }
                }
            }
        });
        operations.addListSelectionListener(e -> {
            if (e.getValueIsAdjusting()) return;
            input.setCaretPosition(input.getDocument().getLength());
            String toInsert = operations.getSelectedValue().getName();
            input.replaceSelection(toInsert);
            System.out.println(toInsert);
            System.out.println(operations.getSelectedValue().getName());
            input.requestFocusInWindow();
            if (input.getDocument().getLength() > 1 && toInsert.length() > 1 && toInsert.substring(toInsert.length() - 2).equals("()")) {
                input.setCaretPosition(input.getDocument().getLength() - 1);
            }

        });
    }

    private void createUIComponents() {
        DefaultListModel<Function> listModel = new DefaultListModel<>();
        listModel.addElement(new Function("sine", "sin()"));
        listModel.addElement(new Function("cosine", "cos()"));
        listModel.addElement(new Function("tangent", "tg()"));
        listModel.addElement(new Function("cotangent", "ctg()"));
        listModel.addElement(new Function("secant", "sec()"));

        listModel.addElement(new Function("π", "pi"));
        listModel.addElement(new Function("e", "e"));
        listModel.addElement(new Function("φ (golden ratio)", "[phi]"));

        listModel.addElement(new Function("+", "+"));
        listModel.addElement(new Function("-", "-"));
        listModel.addElement(new Function("*", "*"));
        listModel.addElement(new Function("/", "/"));
        operations = new JList<>(listModel);
    }
}