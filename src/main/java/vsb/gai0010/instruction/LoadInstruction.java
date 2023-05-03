package vsb.gai0010.instruction;

import vsb.gai0010.stack.Element;

import java.util.Map;
import java.util.Stack;

public class LoadInstruction extends AInstruction {
    private final String id;
    private final Map<String, Element> ids;

    public LoadInstruction(Stack<Element> stack, Map<String, Element> ids, String id) {
        super(stack);
        this.ids = ids;
        this.id = id;
    }

    @Override
    public void execute() {
        if (!ids.containsKey(id)) {
            throw new IllegalArgumentException("ids doesn't have " + id);
        }
        this.getStack().push(ids.get(id));
    }

    @Override
    public String toString() {
        return "load " + this.id;
    }
}
