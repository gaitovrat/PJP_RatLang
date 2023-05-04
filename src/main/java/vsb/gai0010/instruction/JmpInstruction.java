package vsb.gai0010.instruction;

import vsb.gai0010.RMachine;

public class JmpInstruction extends AInstruction {
    private final String label;

    public JmpInstruction(RMachine machine, String label) {
        super(machine);
        this.label = label;
    }

    @Override
    public void execute() {
        this.getMachine().setPc(this.getMachine().getLabels().get(this.label));
    }

    @Override
    public String toString() {
        return "jmp " + label;
    }
}
