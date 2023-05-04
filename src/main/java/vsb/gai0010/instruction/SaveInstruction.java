package vsb.gai0010.instruction;

import vsb.gai0010.RMachine;
import vsb.gai0010.stack.Element;

public class SaveInstruction extends AInstruction {
    private final String id;

    public SaveInstruction(RMachine machine, String id) {
        super(machine);
        this.id = id;
    }

    @Override
    public void execute() {
        Element element = this.getMachine().getStack().pop();

        this.getMachine().getIds().put(id, element);
    }

    @Override
    public String toString() {
        return "save " + id;
    }
}
