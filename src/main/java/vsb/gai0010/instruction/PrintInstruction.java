package vsb.gai0010.instruction;

import vsb.gai0010.stack.Element;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class PrintInstruction extends AInstruction {
    private final int n;

    public PrintInstruction(Stack<Element> stack, int n) {
        super(stack);
        this.n = n;
    }

    @Override
    public void execute() {
        List<Object> objects = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            objects.add(this.getStack().pop().getValue());
        }

        Collections.reverse(objects);

        for (Object object : objects) {
            System.out.println(object.toString());
        }
    }

    @Override
    public String toString() {
        return "print " + n;
    }
}
