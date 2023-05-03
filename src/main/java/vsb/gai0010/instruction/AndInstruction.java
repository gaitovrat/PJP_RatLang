package vsb.gai0010.instruction;

import vsb.gai0010.stack.Element;
import vsb.gai0010.stack.Type;

import java.util.Stack;

public class AndInstruction extends AInstruction {
    public AndInstruction(Stack<Element> stack) {
        super(stack);
    }

    @Override
    public void execute() {
        Element e1 = this.getStack().pop();
        Element e2 = this.getStack().pop();

        if (e1.getType() != Type.BOOLEAN || e2.getType() != Type.BOOLEAN) {
            throw new IllegalArgumentException("Unable to and with types boolean.");
        }

        boolean b1 = (boolean) e1.getValue();
        boolean b2 = (boolean) e2.getValue();

        this.getStack().push(new Element(b1 && b2, Type.BOOLEAN));
    }

    @Override
    public String toString() {
        return "and";
    }
}
