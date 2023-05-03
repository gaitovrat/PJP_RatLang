package vsb.gai0010.instruction;

import vsb.gai0010.stack.Element;
import vsb.gai0010.stack.Type;

import java.util.Stack;

public class ConcatInstruction extends AInstruction {
    public ConcatInstruction(Stack<Element> stack) {
        super(stack);
    }

    @Override
    public void execute() {
        Element e1 = this.getStack().pop();
        Element e2 = this.getStack().pop();

        if (e1.getType() != Type.STRING || e2.getType() != Type.STRING) {
            throw new IllegalArgumentException("Unable to concat with types boolean or int or float.");
        }

        String s1 = (String) e1.getValue();
        String s2 = (String) e2.getValue();

        this.getStack().push(new Element(s2 + s1, Type.STRING));
    }

    @Override
    public String toString() {
        return "concat";
    }
}
