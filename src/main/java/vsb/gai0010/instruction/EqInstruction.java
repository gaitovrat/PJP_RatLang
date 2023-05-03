package vsb.gai0010.instruction;

import vsb.gai0010.stack.Element;
import vsb.gai0010.stack.Type;

import java.util.Stack;

public class EqInstruction extends AInstruction {
    public EqInstruction(Stack<Element> stack) {
        super(stack);
    }

    @Override
    public void execute() {
        Element e1 = this.getStack().pop();
        Element e2 = this.getStack().pop();

        if (e1.getType() == Type.BOOLEAN || e2.getType() == Type.BOOLEAN) {
            throw new IllegalArgumentException("Unable to eq with types boolean.");
        }

        Element eNew = new Element(null, Type.BOOLEAN);
        boolean value;
        if (e1.getType() == Type.STRING && e2.getType() == Type.STRING) {
            String s1 = (String) e1.getValue();
            String s2 = (String) e2.getValue();

            value = s2.equals(s1);
        } else if (e1.getType() == Type.FLOAT || e2.getType() == Type.FLOAT) {
            float f1 = (float) e1.getValue();
            float f2 = (float) e2.getValue();

            value = f2 == f1;
        } else {
            int i1 = (int) e1.getValue();
            int i2 = (int) e2.getValue();

            value = i2 == i1;
        }
        eNew.setValue(value);

        this.getStack().push(eNew);
    }

    @Override
    public String toString() {
        return "eq";
    }
}
