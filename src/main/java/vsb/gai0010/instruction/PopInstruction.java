package vsb.gai0010.instruction;

import vsb.gai0010.stack.Element;

import java.util.Stack;

public class PopInstruction extends AInstruction {
    public PopInstruction(Stack<Element> stack) {
        super(stack);
    }

    @Override
    public void execute() {
        this.getStack().pop();
    }

    @Override
    public String toString() {
        return "pop";
    }
}
