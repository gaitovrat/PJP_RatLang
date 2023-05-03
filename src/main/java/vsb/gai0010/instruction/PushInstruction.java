package vsb.gai0010.instruction;

import vsb.gai0010.stack.Element;
import vsb.gai0010.stack.Type;

import java.util.Stack;

public class PushInstruction extends AInstruction {
    private final Object value;
    private final char type;

    public PushInstruction(Stack<Element> stack, Object value, char type) {
        super(stack);
        this.value = value;
        this.type = type;
    }

    @Override
    public void execute() {
        this.getStack().push(new Element(this.value, Type.get(this.type)));
    }

    @Override
    public String toString() {
        return "push " + type + " " + value.toString();
    }
}
