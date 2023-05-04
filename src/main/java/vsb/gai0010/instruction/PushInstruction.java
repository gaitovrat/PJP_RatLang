package vsb.gai0010.instruction;

import vsb.gai0010.RMachine;
import vsb.gai0010.stack.Element;
import vsb.gai0010.stack.Type;

public class PushInstruction extends AInstruction {
    private final Object value;
    private final char type;

    public PushInstruction(RMachine machine, Object value, char type) {
        super(machine);
        this.value = value;
        this.type = type;
    }

    @Override
    public void execute() {
        this.getMachine().getStack().push(new Element(this.value, Type.get(this.type)));
    }

    @Override
    public String toString() {
        String valueString = value.toString();
        if (value instanceof String) {
            valueString = valueString.replace("\n", "\\n");
        }
        return "push " + type + " " + valueString;
    }
}
