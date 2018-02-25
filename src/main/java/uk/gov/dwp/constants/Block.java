package uk.gov.dwp.constants;

public enum Block {
    START('S'),
    FINISH('F'),
    WALL('X'),
    EMPTY(' ');

    private char type;

    Block(final char type) {
        this.type = type;
    }

    public static Block fromString(final char s) {
        for (final Block block : values()) {
            if (block.type == s) {
                return block;
            }
        }
        return null;
    }
}
