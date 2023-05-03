package vsb.gai0010.instruction;

import vsb.gai0010.stack.Element;
import vsb.gai0010.stack.Type;

import java.util.Map;
import java.util.Stack;

public class FjmpInstruction extends AInstruction {
    private final Map<String, Integer> labels;
    private final String label;

    public FjmpInstruction(Stack<Element> stack, Map<String, Integer> labels, String label) {
        super(stack);
        this.labels = labels;
        this.label = label;
    }

    @Override
    public void execute() {
        Element element = this.getStack().pop();
        if (element.getType() != Type.BOOLEAN) {
            throw new IllegalArgumentException("Unable to fjmp with types int or float or string.");
        }

        boolean value = (boolean) element.getValue();
        if (value) {
            this.labels.put("pc", this.labels.get(label));
        }
    }

    @Override
    public String toString() {
        return "fjmp " + this.label;
    }
}
