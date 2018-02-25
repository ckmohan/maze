package uk.gov.dwp.constants;

public enum Direction {
    NORTH, EAST, WEST, SOUTH;

    public static Direction turnLeft(final Direction direction) {
        switch (direction) {
            case NORTH:
                return WEST;
            case EAST:
                return NORTH;
            case SOUTH:
                return EAST;
            case WEST:
                return SOUTH;
        }
        return direction;
    }

    public static Direction turnRight(final Direction direction) {
        switch (direction) {
            case NORTH:
                return EAST;
            case EAST:
                return SOUTH;
            case SOUTH:
                return WEST;
            case WEST:
                return NORTH;
        }
        return direction;
    }
}