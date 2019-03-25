import org.mariuszgromada.math.mxparser.Expression;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class CalcForm extends JFrame {
    private JPanel mainPanel;
    private JButton evaluate;
    private JTextArea equations;
    private JTextField input;
    private JList operations;
    private List<String> equationsList ;
    private ListIterator<String> equationsListIterator;

    CalcForm(){
        setMinimumSize(new Dimension(500,500));
        equations.setEditable(false);
        equationsList = new ArrayList<>();
        setTitle("Calculator");
        setContentPane(mainPanel);
        JMenu settings = new JMenu();
        JButton exit = new JButton();
        JButton reset = new JButton();
        settings.add(exit);
        settings.add(reset);
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(settings);
        setJMenuBar(menuBar);
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                equations.setText("");
                input.setText("");
            }
        });
        evaluate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                performCalculations();
            }
        });
        input.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP && equationsListIterator.hasPrevious()){
                    input.setText(equationsListIterator.previous());
                }
                else if (e.getKeyCode() == KeyEvent.VK_DOWN && equationsListIterator.hasNext()){
                    input.setText(equationsListIterator.next());
                }
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    performCalculations();
                }
            }
        });
    }
    private void performCalculations() {
        Expression expression = new Expression(input.getText());
        if(!expression.checkSyntax()){
            equations.append(expression.getErrorMessage());
        }
        String equation = MessageFormat.format(
                "{0} = {1}\n", expression.getExpressionString(), expression.calculate());
        equations.append(equation);
        equationsList.add(expression.getExpressionString());
        equationsListIterator = equationsList.listIterator(equationsList.size());
        input.setText("");
    }

}
