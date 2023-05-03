package vsb.gai0010.instruction;

import vsb.gai0010.stack.Element;
import vsb.gai0010.stack.Type;

import java.util.Stack;

public class UminusInstruction extends AInstruction {
    public UminusInstruction(Stack<Element> stack) {
        super(stack);
    }

    @Override
    public void execute() {
        Element e = this.getStack().pop();

        if (e.getType() == Type.BOOLEAN || e.getType() == Type.STRING) {
            throw new IllegalArgumentException("Unable to uminus with types boolean or string.");
        }

        Element eNew = new Element(null, e.getType());
        if (e.getType() == Type.FLOAT) {
            float value = (float) e.getValue();
            eNew.setValue(-value);
        } else {
            int value = (int) e.getValue();
            eNew.setValue(-value);
        }

        this.getStack().push(eNew);
    }

    @Override
    public String toString() {
        return "uminus";
    }
}
