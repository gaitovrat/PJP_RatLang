package vsb.gai0010.instruction;

import vsb.gai0010.RMachine;

public class LabelInstruction extends AInstruction {
    private final String label;
    private final int position;

    public LabelInstruction(RMachine machine, String label, int position) {
        super(machine);
        this.label = label;
        this.position = position;
    }

    @Override
    public void execute() {
        this.getMachine().getLabels().put(this.label, this.position);
    }

    @Override
    public String toString() {
        return "label " + this.label;
    }
}
