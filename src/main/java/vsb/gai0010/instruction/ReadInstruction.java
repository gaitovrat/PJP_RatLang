package vsb.gai0010.instruction;

import vsb.gai0010.stack.Element;
import vsb.gai0010.stack.Type;

import java.util.Scanner;
import java.util.Stack;

public class ReadInstruction extends AInstruction {
    private final char type;

    public ReadInstruction(Stack<Element> stack, char type) {
        super(stack);
        this.type = type;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        switch (Type.get(this.type)) {
            case INTEGER -> this.getStack().push(new Element(scanner.nextInt(), Type.INTEGER));
            case FLOAT -> this.getStack().push(new Element(scanner.nextFloat(), Type.FLOAT));
            case STRING -> this.getStack().push(new Element(scanner.nextLine(), Type.STRING));
            case BOOLEAN -> this.getStack().push(new Element(scanner.nextBoolean(), Type.BOOLEAN));
        }
    }

    @Override
    public String toString() {
        return "read " + type;
    }
}
