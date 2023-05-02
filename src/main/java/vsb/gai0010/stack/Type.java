package vsb.gai0010.stack;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Type {
    INTEGER('I'), FLOAT('F'),
    BOOLEAN('B'), STRING('S');

    private final char id;

    public static Type get(char id) {
        return switch (id) {
            case 'I' -> Type.INTEGER;
            case 'F' -> Type.FLOAT;
            case 'B' -> Type.BOOLEAN;
            case 'S' -> Type.STRING;
            default -> throw new IllegalArgumentException("Id '" + id + "' doesn't exists.");
        };
    }
}
