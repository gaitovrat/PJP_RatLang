package vsb.gai0010.instruction;

import vsb.gai0010.stack.Element;
import vsb.gai0010.stack.Type;

import java.util.Stack;

public class AddInstruction implements IInstruction {
    @Override
    public void execute(Stack<Element> stack) {
        Element e1 = stack.pop();
        Element e2 = stack.pop();

        if (e1.getType() == Type.BOOLEAN || e2.getType() == Type.BOOLEAN
            || e1.getType() == Type.STRING || e2.getType() == Type.STRING) {
            throw new IllegalArgumentException("Unable to add with types boolean or string.");
        }

        if (e1.getType() == Type.FLOAT || e2.getType() == Type.FLOAT) {
            float f1 = (float)e1.getValue();
            float f2 = (float)e2.getValue();

            stack.push(new Element(f1 + f2, Type.FLOAT));
        } else {
            int i1 = (int)e1.getValue();
            int i2 = (int)e2.getValue();

            stack.push(new Element(i1 + i2, Type.INTEGER));
        }
    }

    @Override
    public String toString() {
        return "add";
    }
}
