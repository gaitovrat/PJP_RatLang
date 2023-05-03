package vsb.gai0010.instruction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import vsb.gai0010.stack.Element;

import java.util.Stack;

@AllArgsConstructor
@Getter
public abstract class AInstruction implements IInstruction {
    private final Stack<Element> stack;
}
