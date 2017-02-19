package pl.bzawadka.drawing;

public enum CommandType {
    CREATE_CANVAS('C'),
    DRAW_LINE('L'),
    DRAW_RECTANGLE('R'),
    BRUSH('B'),
    QUIT('Q');

    private Character code;

    CommandType(Character code) {
        this.code = code;
    }

    public Character getCode() {
        return code;
    }

    public static CommandType parseFrom(char code) {
        for (CommandType type : CommandType.values()) {
            if (type.code.toString().equalsIgnoreCase(String.valueOf(code)))
                return type;
        }
        throw new IllegalArgumentException("Unrecognized command: " + code);
    }
}
