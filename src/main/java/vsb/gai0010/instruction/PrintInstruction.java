package vsb.gai0010.instruction;

import vsb.gai0010.stack.Element;

import java.util.Stack;

public class PrintInstruction extends AInstruction {
    private final int n;

    public PrintInstruction(Stack<Element> stack, int n) {
        super(stack);
        this.n = n;
    }

    @Override
    public void execute() {
        for (int i = 0; i < n; ++i) {
            System.out.println(this.getStack().pop().getValue().toString());
        }
    }

    @Override
    public String toString() {
        return "print " + n;
    }
}
