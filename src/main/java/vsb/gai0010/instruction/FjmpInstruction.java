package vsb.gai0010.instruction;

import vsb.gai0010.RMachine;
import vsb.gai0010.stack.Element;
import vsb.gai0010.stack.Type;

public class FjmpInstruction extends AInstruction {
    private final String label;

    public FjmpInstruction(RMachine machine, String label) {
        super(machine);
        this.label = label;
    }


    @Override
    public void execute() {
        Element element = this.getMachine().getStack().pop();
        if (element.getType() != Type.BOOLEAN) {
            throw new IllegalArgumentException("Unable to fjmp with types int or float or string.");
        }

        boolean value = (boolean) element.getValue();
        if (value) {
            this.getMachine().setPc(this.getMachine().getLabels().get(label));
        }
    }

    @Override
    public String toString() {
        return "fjmp " + this.label;
    }
}
