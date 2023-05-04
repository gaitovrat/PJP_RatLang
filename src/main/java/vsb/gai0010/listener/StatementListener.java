package vsb.gai0010.listener;

import org.antlr.v4.runtime.tree.TerminalNode;
import vsb.gai0010.RMachine;
import vsb.gai0010.RatLangBaseListener;
import vsb.gai0010.RatLangParser;
import vsb.gai0010.instruction.*;
import vsb.gai0010.stack.Element;
import vsb.gai0010.stack.Type;

import java.util.List;

public class StatementListener extends RatLangBaseListener {
    private long id;
    private final RMachine machine;

    public StatementListener(RMachine machine) {
        this.machine = machine;
        this.id = 0;
    }

    @Override
    public void exitDecValue(RatLangParser.DecValueContext ctx) {
        int i = Integer.parseInt(ctx.DEC().toString());
        this.machine.getInstructions().add(new PushInstruction(this.machine, i, Type.INTEGER.getId()));
    }

    @Override
    public void exitHexValue(RatLangParser.HexValueContext ctx) {
        String hexString = ctx.HEXA().toString();
        int number = Integer.parseInt(hexString.replace("0x", ""), 16);
        this.machine.getInstructions().add(new PushInstruction(this.machine, number, Type.INTEGER.getId()));
    }

    @Override
    public void exitFloatValue(RatLangParser.FloatValueContext ctx) {
        float f = Float.parseFloat(ctx.FLOAT_VALUE().toString());
        this.machine.getInstructions().add(new PushInstruction(this.machine, f, Type.FLOAT.getId()));
    }

    @Override
    public void exitStringValue(RatLangParser.StringValueContext ctx) {
        String value = ctx.STRING_VALUE().toString();
        value = value.replace("\"", "");
        this.machine.getInstructions().add(new PushInstruction(this.machine, value, Type.STRING.getId()));
    }

    @Override
    public void exitOctValue(RatLangParser.OctValueContext ctx) {
        int number = Integer.parseInt(ctx.OCT().toString(), 8);
        this.machine.getInstructions().add(new PushInstruction(this.machine, number, Type.INTEGER.getId()));
    }

    @Override
    public void exitBoolValue(RatLangParser.BoolValueContext ctx) {
        String boolValue = ctx.boolValues().getText();
        boolean value = boolValue.equals("true");
        this.machine.getInstructions().add(new PushInstruction(this.machine, value, Type.BOOLEAN.getId()));
    }

    @Override
    public void exitUnary(RatLangParser.UnaryContext ctx) {
        this.machine.getInstructions().add(new UminusInstruction(this.machine));
    }

    @Override
    public void exitWrite(RatLangParser.WriteContext ctx) {
        int size = ctx.expression().size();
        this.machine.getInstructions().add(new PushInstruction(machine, "\n", Type.STRING.getId()));
        this.machine.getInstructions().add(new PrintInstruction(this.machine, size + 1));
    }

    @Override
    public void exitExpressionEval(RatLangParser.ExpressionEvalContext ctx) {
        this.machine.getInstructions().add(new PopInstruction(this.machine));
    }

    @Override
    public void exitNot(RatLangParser.NotContext ctx) {
        this.machine.getInstructions().add(new NotInstruction(this.machine));
    }

    @Override
    public void exitMul(RatLangParser.MulContext ctx) {
        char op = ctx.op.getText().charAt(0);
        List<IInstruction> instructions = this.machine.getInstructions();

        switch (op) {
            case '*' -> instructions.add(new MulInstruction(this.machine));
            case '/' -> instructions.add(new DivInstruction(this.machine));
            case '%' -> instructions.add(new ModInstruction(this.machine));
        }
    }

    @Override
    public void exitAdd(RatLangParser.AddContext ctx) {
        char op = ctx.op.getText().charAt(0);
        List<IInstruction> instructions = this.machine.getInstructions();

        switch (op) {
            case '+' -> instructions.add(new AddInstruction(this.machine));
            case '-' -> instructions.add(new SubInstruction(this.machine));
            case '.' -> instructions.add(new ConcatInstruction(this.machine));
        }
    }

    @Override
    public void exitRelation(RatLangParser.RelationContext ctx) {
        char op = ctx.op.getText().charAt(0);
        List<IInstruction> instructions = this.machine.getInstructions();

        switch (op) {
            case '<' -> instructions.add(new LtInstruction(this.machine));
            case '>' -> instructions.add(new GtInstruction(this.machine));
        }
    }

    @Override
    public void exitComparison(RatLangParser.ComparisonContext ctx) {
        String op = ctx.op.getText();
        List<IInstruction> instructions = this.machine.getInstructions();

        instructions.add(new EqInstruction(this.machine));
        if (op.equals("!=")) {
            instructions.add(new NotInstruction(this.machine));
        }
    }

    @Override
    public void exitAnd(RatLangParser.AndContext ctx) {
        this.machine.getInstructions().add(new AndInstruction(this.machine));
    }

    @Override
    public void exitOr(RatLangParser.OrContext ctx) {
        this.machine.getInstructions().add(new OrInstruction(this.machine));
    }

    @Override
    public void exitIdValue(RatLangParser.IdValueContext ctx) {
        String id = ctx.ID().toString();
        this.machine.getInstructions().add(new LoadInstruction(this.machine, id));
    }

    @Override
    public void exitAssignment(RatLangParser.AssignmentContext ctx) {
        String id = ctx.ID().toString();
        this.machine.getInstructions().add(new SaveInstruction(this.machine, id));
    }

    @Override
    public void exitVariableDeclaration(RatLangParser.VariableDeclarationContext ctx) {
        List<TerminalNode> ids = ctx.ID();
        for (TerminalNode id : ids) {
            Type type = Type.get(ctx.primitiveType().getText().toUpperCase().charAt(0));

            this.machine.getInstructions().add(new PushInstruction(this.machine, type.getDefaultValue(), type.getId()));
            this.machine.getIds().put(id.getText(), new Element(type.getDefaultValue(), type));
            this.machine.getInstructions().add(new SaveInstruction(this.machine, id.getText()));
        }
    }

    @Override
    public void exitRead(RatLangParser.ReadContext ctx) {
        List<TerminalNode> ids = ctx.ID();
        for (TerminalNode id : ids) {
            Element element = this.machine.getIds().get(id.getText());
            this.machine.getInstructions().add(new ReadInstruction(this.machine, element.getType().getId()));
            this.machine.getInstructions().add(new SaveInstruction(this.machine, id.getText()));
        }
    }

    @Override
    public void enterWhile(RatLangParser.WhileContext ctx) {
        this.addLabel();
    }

    @Override
    public void exitWhile(RatLangParser.WhileContext ctx) {
        this.machine.getInstructions().add(new JmpInstruction(this.machine, String.valueOf(this.id - 1)));
        this.addLabel();
    }

    @Override
    public void exitIf(RatLangParser.IfContext ctx) {
        this.addLabel();
    }

    @Override
    public void enterElse_(RatLangParser.Else_Context ctx) {
        this.machine.getInstructions().add(new JmpInstruction(this.machine, String.valueOf(id + 1)));
        this.addLabel();
    }

    @Override
    public void exitCondition(RatLangParser.ConditionContext ctx) {
        this.machine.getInstructions().add(new NotInstruction(this.machine));
        this.machine.getInstructions().add(new FjmpInstruction(this.machine, String.valueOf(this.id)));
    }

    private void addLabel() {
        List<IInstruction> instructions = this.machine.getInstructions();
        String id = String.valueOf(this.id);
        int size = instructions.size();

        instructions.add(new LabelInstruction(this.machine, id, size));
        this.machine.getLabels().put(id, size);
        this.id++;
    }
}
