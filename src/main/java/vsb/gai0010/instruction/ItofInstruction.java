package vsb.gai0010.instruction;

import vsb.gai0010.stack.Element;
import vsb.gai0010.stack.Type;

import java.util.Stack;

public class ItofInstruction extends AInstruction {
    public ItofInstruction(Stack<Element> stack) {
        super(stack);
    }

    @Override
    public void execute() {
        Element element = this.getStack().pop();

        if (element.getType() != Type.INTEGER) {
            throw new IllegalArgumentException("Unable to itof with types float or string or boolean.");
        }

        Element elementNew = new Element((float)element.getValue(), Type.FLOAT);
        this.getStack().push(elementNew);
    }

    @Override
    public String toString() {
        return "itof";
    }
}
