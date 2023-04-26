package vsb.gai0010.exception;

public class OperandNotExistsException extends Exception {
    public OperandNotExistsException(String message) {
        super("Operand " + message + " not exists.");
    }
}
