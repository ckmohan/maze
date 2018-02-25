package uk.gov.dwp.vo;

import uk.gov.dwp.constants.Direction;

public class Record {

    private Coordinate coordinate;
    private Direction direction;

    public Record(final Coordinate coordinate, final Direction direction) {
        this.coordinate = coordinate;
        this.direction = direction;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public String toString() {
        return "Moved toward " +
                direction + " at " +
                "coordinate : " + coordinate;
    }
}
