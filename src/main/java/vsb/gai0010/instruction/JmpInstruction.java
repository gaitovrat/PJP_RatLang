package vsb.gai0010.instruction;

import vsb.gai0010.stack.Element;

import java.util.List;
import java.util.Map;
import java.util.Stack;

public class JmpInstruction extends AInstruction {
    private final Map<String, Integer> labels;
    private final String label;

    public JmpInstruction(Stack<Element> stack, Map<String, Integer> labels, String label) {
        super(stack);
        this.labels = labels;
        this.label = label;
    }

    @Override
    public void execute() {
        this.labels.put("pc", this.labels.get(this.label));
    }

    @Override
    public String toString() {
        return "jmp " + label;
    }
}
