package vsb.gai0010.instruction;

import vsb.gai0010.RMachine;

public class LoadInstruction extends AInstruction {
    private final String id;

    public LoadInstruction(RMachine machine, String id) {
        super(machine);
        this.id = id;
    }

    @Override
    public void execute() {
        if (!this.getMachine().getIds().containsKey(id)) {
            throw new IllegalArgumentException("ids doesn't have " + id);
        }

        this.getMachine().getStack().push(this.getMachine().getIds().get(this.id));
    }

    @Override
    public String toString() {
        return "load " + this.id;
    }
}
