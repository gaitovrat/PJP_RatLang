package vsb.gai0010.instruction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import vsb.gai0010.RMachine;

@AllArgsConstructor
@Getter
public abstract class AInstruction implements IInstruction {
    private final RMachine machine;
}
