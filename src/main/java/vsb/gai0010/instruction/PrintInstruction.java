package vsb.gai0010.instruction;

import vsb.gai0010.RMachine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrintInstruction extends AInstruction {
    private final int n;

    public PrintInstruction(RMachine machine, int n) {
        super(machine);
        this.n = n;
    }

    @Override
    public void execute() {
        List<Object> objects = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            objects.add(this.getMachine().getStack().pop().getValue());
        }

        Collections.reverse(objects);

        for (Object object : objects) {
            System.out.println(object.toString());
        }
    }

    @Override
    public String toString() {
        return "print " + n;
    }
}
