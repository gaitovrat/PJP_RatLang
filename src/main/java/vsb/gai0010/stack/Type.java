package vsb.gai0010.stack;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Type {
    INTEGER('I', 0), FLOAT('F', 0.0f),
    BOOLEAN('B', false), STRING('S', "");

    private final char id;
    private final Object defaultValue;

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
