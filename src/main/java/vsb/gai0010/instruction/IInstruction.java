package vsb.gai0010.instruction;

import vsb.gai0010.RMachine;

public interface IInstruction {
    void execute();

    RMachine getMachine();
}
