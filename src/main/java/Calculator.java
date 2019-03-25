import javax.swing.*;
import java.text.MessageFormat;
import java.util.Date;

public class Calculator {

    public static void main(String[] args) {
        JFrame calcForm = new CalcForm();
        calcForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        calcForm.pack();
        calcForm.setVisible(true);
    }
}
