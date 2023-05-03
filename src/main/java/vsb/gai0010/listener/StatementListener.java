package vsb.gai0010.listener;

import lombok.AllArgsConstructor;
import org.antlr.v4.runtime.ParserRuleContext;
import vsb.gai0010.RMachine;
import vsb.gai0010.RatLangBaseListener;
import vsb.gai0010.RatLangParser;
import vsb.gai0010.instruction.*;
import vsb.gai0010.stack.Type;

import java.util.List;

@AllArgsConstructor
public class StatementListener extends RatLangBaseListener {
    private final RMachine machine;

    @Override
    public void exitDecValue(RatLangParser.DecValueContext ctx) {
        int i = Integer.parseInt(ctx.DEC().toString());
        this.machine.getInstructions().add(new PushInstruction(this.machine.getStack(), i, Type.INTEGER.getId()));
    }

    @Override
    public void exitHexValue(RatLangParser.HexValueContext ctx) {
        String hexString = ctx.HEXA().toString();
        int number = Integer.parseInt(hexString.replace("0x", ""), 16);
        this.machine.getInstructions().add(new PushInstruction(this.machine.getStack(), number, Type.INTEGER.getId()));
    }

    @Override
    public void exitFloatValue(RatLangParser.FloatValueContext ctx) {
        float f = Float.parseFloat(ctx.FLOAT_VALUE().toString());
        this.machine.getInstructions().add(new PushInstruction(this.machine.getStack(), f, Type.FLOAT.getId()));
    }

    @Override
    public void exitStringValue(RatLangParser.StringValueContext ctx) {
        String value = ctx.STRING_VALUE().toString();
        this.machine.getInstructions().add(new PushInstruction(this.machine.getStack(), value, Type.STRING.getId()));
    }

    @Override
    public void exitOctValue(RatLangParser.OctValueContext ctx) {
        int number = Integer.parseInt(ctx.OCT().toString(), 8);
        this.machine.getInstructions().add(new PushInstruction(this.machine.getStack(), number, Type.INTEGER.getId()));
    }

    @Override
    public void exitBoolValue(RatLangParser.BoolValueContext ctx) {
        String boolValue = ctx.boolValues().getText();
        boolean value = boolValue.equals("true");
        this.machine.getInstructions().add(new PushInstruction(this.machine.getStack(), value, Type.BOOLEAN.getId()));
    }

    @Override
    public void exitUnary(RatLangParser.UnaryContext ctx) {
        this.machine.getInstructions().add(new UminusInstruction(this.machine.getStack()));
    }

    @Override
    public void exitWrite(RatLangParser.WriteContext ctx) {
        int size = ctx.expression().size();
        this.machine.getInstructions().add(new PrintInstruction(this.machine.getStack(), size));
    }

    @Override
    public void exitExpressionEval(RatLangParser.ExpressionEvalContext ctx) {
        this.machine.getInstructions().add(new PopInstruction(this.machine.getStack()));
    }

    @Override
    public void exitNot(RatLangParser.NotContext ctx) {
        this.machine.getInstructions().add(new NotInstruction(this.machine.getStack()));
    }

    @Override
    public void exitMul(RatLangParser.MulContext ctx) {
        char op = ctx.op.getText().charAt(0);
        List<IInstruction> instructions = this.machine.getInstructions();

        switch (op) {
            case '*' -> instructions.add(new MulInstruction(this.machine.getStack()));
            case '/' -> instructions.add(new DivInstruction(this.machine.getStack()));
            case '%' -> instructions.add(new ModInstruction(this.machine.getStack()));
        }
    }
}
