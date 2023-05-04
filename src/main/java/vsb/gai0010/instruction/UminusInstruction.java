package vsb.gai0010.instruction;

import vsb.gai0010.RMachine;
import vsb.gai0010.stack.Element;
import vsb.gai0010.stack.Type;

public class UminusInstruction extends AInstruction {
    public UminusInstruction(RMachine machine) {
        super(machine);
    }

    @Override
    public void execute() {
        Element e = this.getMachine().getStack().pop();

        if (e.getType() == Type.BOOLEAN || e.getType() == Type.STRING) {
            throw new IllegalArgumentException("Unable to uminus with types boolean or string.");
        }

        Element eNew = new Element(null, e.getType());
        if (e.getType() == Type.FLOAT) {
            float value = (float) e.getValue();
            eNew.setValue(-value);
        } else {
            int value = (int) e.getValue();
            eNew.setValue(-value);
        }

        this.getMachine().getStack().push(eNew);
    }

    @Override
    public String toString() {
        return "uminus";
    }
}
