package vsb.gai0010.instruction;

import vsb.gai0010.stack.Element;

import java.util.Map;
import java.util.Stack;

public class SaveInstruction extends AInstruction {
    private final String id;
    private final Map<String, Element> ids;

    public SaveInstruction(Stack<Element> stack, Map<String, Element> ids, String id) {
        super(stack);
        this.ids = ids;
        this.id = id;
    }

    @Override
    public void execute() {
        Element element = this.getStack().pop();

        this.ids.put(id, element);
    }

    @Override
    public String toString() {
        return "save " + id;
    }
}
