package vsb.gai0010.instruction;

import vsb.gai0010.RMachine;
import vsb.gai0010.stack.Element;
import vsb.gai0010.stack.Type;

import java.util.Scanner;

public class ReadInstruction extends AInstruction {
    private final char type;

    public ReadInstruction(RMachine machine, char type) {
        super(machine);
        this.type = type;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        switch (Type.get(this.type)) {
            case INTEGER -> this.getMachine().getStack().push(new Element(scanner.nextInt(), Type.INTEGER));
            case FLOAT -> this.getMachine().getStack().push(new Element(scanner.nextFloat(), Type.FLOAT));
            case STRING -> this.getMachine().getStack().push(new Element(scanner.nextLine(), Type.STRING));
            case BOOLEAN -> this.getMachine().getStack().push(new Element(scanner.nextBoolean(), Type.BOOLEAN));
        }
    }

    @Override
    public String toString() {
        return "read " + type;
    }
}
