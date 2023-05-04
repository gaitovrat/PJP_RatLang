package vsb.gai0010.instruction;

import vsb.gai0010.RMachine;

public class PopInstruction extends AInstruction {
    public PopInstruction(RMachine machine) {
        super(machine);
    }

    @Override
    public void execute() {
        if (this.getMachine().getStack().empty()) {
            return;
        }
        this.getMachine().getStack().pop();
    }

    @Override
    public String toString() {
        return "pop";
    }
}
