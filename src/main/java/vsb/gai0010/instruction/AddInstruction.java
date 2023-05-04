package vsb.gai0010.instruction;

import vsb.gai0010.RMachine;
import vsb.gai0010.stack.Element;
import vsb.gai0010.stack.Type;

public class AddInstruction extends AInstruction {

    public AddInstruction(RMachine machine) {
        super(machine);
    }

    @Override
    public void execute() {
        Element e1 = this.getMachine().getStack().pop();
        Element e2 = this.getMachine().getStack().pop();

        if (e1.getType() == Type.BOOLEAN || e2.getType() == Type.BOOLEAN
            || e1.getType() == Type.STRING || e2.getType() == Type.STRING) {
            throw new IllegalArgumentException("Unable to add with types boolean or string.");
        }

        if (e1.getType() == Type.FLOAT || e2.getType() == Type.FLOAT) {
            float f1 = (float)e1.getValue();
            float f2 = (float)e2.getValue();

            this.getMachine().getStack().push(new Element(f1 + f2, Type.FLOAT));
        } else {
            int i1 = (int)e1.getValue();
            int i2 = (int)e2.getValue();

            this.getMachine().getStack().push(new Element(i1 + i2, Type.INTEGER));
        }
    }

    @Override
    public String toString() {
        return "add";
    }
}
