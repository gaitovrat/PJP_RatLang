package vsb.gai0010.instruction;

import vsb.gai0010.RMachine;
import vsb.gai0010.stack.Element;
import vsb.gai0010.stack.Type;

public class GtInstruction extends AInstruction {
    public GtInstruction(RMachine machine) {
        super(machine);
    }

    @Override
    public void execute() {
        Element e1 = this.getMachine().getStack().pop();
        Element e2 = this.getMachine().getStack().pop();

        if (e1.getType() == Type.STRING || e2.getType() == Type.STRING
                || e1.getType() == Type.BOOLEAN || e2.getType() == Type.BOOLEAN) {
            throw new IllegalArgumentException("Unable to gt with types boolean or string.");
        }

        Element eNew = new Element(null, Type.BOOLEAN);
        boolean value;
        if (e1.getType() == Type.FLOAT || e2.getType() == Type.FLOAT) {
            float f1 = (float) e1.getValue();
            float f2 = (float) e2.getValue();

            value = f2 > f1;
        } else {
            int i1 = (int) e1.getValue();
            int i2 = (int) e2.getValue();

            value = i2 > i1;
        }
        eNew.setValue(value);

        this.getMachine().getStack().push(eNew);
    }

    @Override
    public String toString() {
        return "gt";
    }
}
