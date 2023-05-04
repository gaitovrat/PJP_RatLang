package vsb.gai0010.instruction;

import vsb.gai0010.RMachine;
import vsb.gai0010.stack.Element;
import vsb.gai0010.stack.Type;

public class NotInstruction extends AInstruction {
    public NotInstruction(RMachine machine) {
        super(machine);
    }

    @Override
    public void execute() {
        Element e = this.getMachine().getStack().pop();

        if (e.getType() != Type.BOOLEAN) {
            throw new IllegalArgumentException("Unable to not with types int or float or string.");
        }

        boolean value = (boolean) e.getValue();
        this.getMachine().getStack().push(new Element(!value, Type.BOOLEAN));
    }

    @Override
    public String toString() {
        return "not";
    }
}
