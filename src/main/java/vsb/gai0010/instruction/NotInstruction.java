package vsb.gai0010.instruction;

import vsb.gai0010.stack.Element;
import vsb.gai0010.stack.Type;

import java.util.Stack;

public class NotInstruction extends AInstruction {
    public NotInstruction(Stack<Element> stack) {
        super(stack);
    }

    @Override
    public void execute() {
        Element e = this.getStack().pop();

        if (e.getType() != Type.BOOLEAN) {
            throw new IllegalArgumentException("Unable to not with types int or float or string.");
        }

        boolean value = (boolean) e.getValue();
        this.getStack().push(new Element(!value, Type.BOOLEAN));
    }

    @Override
    public String toString() {
        return "not";
    }
}
