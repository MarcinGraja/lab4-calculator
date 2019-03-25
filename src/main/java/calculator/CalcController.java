package calculator;

import org.mariuszgromada.math.mxparser.Expression;
import java.text.MessageFormat;

class CalcController {
    static String performCalculations(String input) throws Exception {
        Expression expression = new Expression(input);
        if(!expression.checkSyntax()){
            throw new Exception(expression.getErrorMessage());
        }
        String equation = MessageFormat.format(
                "{0} = {1}", expression.getExpressionString(), expression.calculate());
        CalcModel.equationsDequeue.addLast(expression.getExpressionString());
        return equation;
    }
    static String getNextEquation(){
        if (!CalcModel.equationsDequeue.isEmpty()){
            String temp = CalcModel.equationsDequeue.removeFirst();
            CalcModel.equationsDequeue.addLast(temp);
            return temp;
        }
        else return "";
    }
    static String getPreviousEquation(){
        if (!CalcModel.equationsDequeue.isEmpty()){
            String temp = CalcModel.equationsDequeue.removeLast();
            CalcModel.equationsDequeue.addFirst(temp);
            return temp;
        }
        else return "";
    }
}
