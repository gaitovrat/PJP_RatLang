package vsb.gai0010.instruction;

import vsb.gai0010.RMachine;
import vsb.gai0010.stack.Element;
import vsb.gai0010.stack.Type;

public class ItofInstruction extends AInstruction {
    public ItofInstruction(RMachine machine) {
        super(machine);
    }

    @Override
    public void execute() {
        Element element = this.getMachine().getStack().pop();

        if (element.getType() != Type.INTEGER) {
            throw new IllegalArgumentException("Unable to itof with types float or string or boolean.");
        }

        Element elementNew = new Element(element.getValue(), Type.FLOAT);
        this.getMachine().getStack().push(elementNew);
    }

    @Override
    public String toString() {
        return "itof";
    }
}
