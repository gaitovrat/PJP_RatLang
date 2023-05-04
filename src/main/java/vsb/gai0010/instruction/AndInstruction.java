package vsb.gai0010.instruction;

import vsb.gai0010.RMachine;
import vsb.gai0010.stack.Element;
import vsb.gai0010.stack.Type;

public class AndInstruction extends AInstruction {
    public AndInstruction(RMachine machine) {
        super(machine);
    }

    @Override
    public void execute() {
        Element e1 = this.getMachine().getStack().pop();
        Element e2 = this.getMachine().getStack().pop();

        if (e1.getType() != Type.BOOLEAN || e2.getType() != Type.BOOLEAN) {
            throw new IllegalArgumentException("Unable to and with types boolean.");
        }

        boolean b1 = (boolean) e1.getValue();
        boolean b2 = (boolean) e2.getValue();

        this.getMachine().getStack().push(new Element(b1 && b2, Type.BOOLEAN));
    }

    @Override
    public String toString() {
        return "and";
    }
}
