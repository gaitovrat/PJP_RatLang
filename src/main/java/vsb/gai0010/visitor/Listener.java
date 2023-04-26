package vsb.gai0010.visitor;

import vsb.gai0010.RatLangBaseListener;
import vsb.gai0010.RatLangParser;
import vsb.gai0010.exception.OperandNotExistsException;

import java.util.Stack;

public class Listener extends RatLangBaseListener {
    private final Stack<Integer> stack;

    public Listener() {
        super();

        this.stack = new Stack<>();
    }

    @Override
    public void exitProg(RatLangParser.ProgContext ctx) {
        for (Integer item : stack) {
            System.out.println(item);
        }
    }

    @Override
    public void exitParentheses(RatLangParser.ParenthesesContext ctx) {
        ctx.expr().enterRule(this);
    }

    @Override
    public void exitDecNumber(RatLangParser.DecNumberContext ctx) {
        int number = Integer.parseInt(ctx.INT().toString());
        stack.push(number);
    }

    @Override
    public void exitOctNumber(RatLangParser.OctNumberContext ctx) {
        int number = Integer.parseInt(ctx.OCT().toString(), 8);
        stack.push(number);
    }

    @Override
    public void exitHexNumber(RatLangParser.HexNumberContext ctx) {
        String hexString = ctx.HEXA().toString();
        int number = Integer.parseInt(hexString.replace("0x", ""), 16);
        stack.push(number);
    }

    @Override
    public void exitAdd(RatLangParser.AddContext ctx) {
        String operand = ctx.op.getText();
        int number2 = stack.pop();
        int number1 = stack.pop();

        switch (operand.charAt(0)) {
            case '+' -> stack.push(number1 + number2);
            case '-' -> stack.push(number1 - number2);
            default -> System.err.println(new OperandNotExistsException(operand).getMessage());
        }
    }

    @Override
    public void exitMul(RatLangParser.MulContext ctx) {
        String operand = ctx.op.getText();
        int number2 = stack.pop();
        int number1 = stack.pop();

        switch (operand.charAt(0)) {
            case '*' -> stack.push(number1 * number2);
            case '/' -> stack.push(number1 / number2);
            default -> System.err.println(new OperandNotExistsException(operand).getMessage());
        }
    }
}
