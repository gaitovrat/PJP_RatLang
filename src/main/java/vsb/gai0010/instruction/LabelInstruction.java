package vsb.gai0010.instruction;

import vsb.gai0010.stack.Element;

import java.util.List;
import java.util.Map;
import java.util.Stack;

public class LabelInstruction extends AInstruction {
    private final Map<String, Integer> labels;
    private final List<IInstruction> instructions;
    private final String label;

    public LabelInstruction(Stack<Element> stack, Map<String, Integer> labels, List<IInstruction> instructions, String label) {
        super(stack);
        this.labels = labels;
        this.instructions = instructions;
        this.label = label;
    }

    @Override
    public void execute() {
        this.labels.put(this.label, this.instructions.size() - 1);
    }

    @Override
    public String toString() {
        return "label " + this.label;
    }
}
