package calculator;

import org.mariuszgromada.math.mxparser.Expression;
import java.text.MessageFormat;

class CalcController{
    static int index;
    static String performCalculations(String input) throws Exception {
        Expression expression = new Expression(input);
        if(!expression.checkSyntax()){
            throw new Exception(expression.getErrorMessage());
        }
        String equation = MessageFormat.format(
                "{0} = {1}", expression.getExpressionString(), expression.calculate());
        CalcModel.equations.add(expression.getExpressionString());
        return equation;
    }
    static String getNextEquation(){
        if (!CalcModel.equations.isEmpty()){
            if (index < CalcModel.equations.size()-1){
                index++;
            }
            String returned = "";
            if (index < CalcModel.equations.size()) {
                returned = CalcModel.equations.get(index);
            }
            return returned;
        }
        else return "";
    }
    static String getPreviousEquation(){
        if (!CalcModel.equations.isEmpty()){
            if (index>=0) {
                index--;
            }
            String returned = "";
            if (index > 0) {
                returned = CalcModel.equations.get(index);
            }
            return returned;
        }
        else return "";
    }
}
