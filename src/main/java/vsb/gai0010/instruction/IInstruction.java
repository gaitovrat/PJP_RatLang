package vsb.gai0010.instruction;

import vsb.gai0010.stack.Element;

import java.util.Stack;

public interface IInstruction {
    void execute();

    Stack<Element> getStack();
}
