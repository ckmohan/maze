package uk.gov.dwp.explorer;

import static java.util.Collections.unmodifiableList;
import static uk.gov.dwp.constants.Direction.EAST;
import static uk.gov.dwp.constants.Direction.NORTH;
import static uk.gov.dwp.constants.Direction.SOUTH;

import uk.gov.dwp.constants.Block;
import uk.gov.dwp.constants.Direction;
import uk.gov.dwp.explorer.exception.InvalidMovementException;
import uk.gov.dwp.maze.Maze;
import uk.gov.dwp.vo.Coordinate;
import uk.gov.dwp.vo.Record;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Explorer {

    private final Maze maze;
    private final Movements movements;
    private final List<Record> records = new LinkedList<>();

    private Coordinate coordinate;
    private Direction direction;

    Explorer(final Maze maze, final Coordinate coordinate,
             final Direction direction, final Movements movements) {

        this.maze = maze;
        this.movements = movements;

        this.coordinate = coordinate;
        this.direction = direction;
    }

    public void moveForward() throws InvalidMovementException {

        if (Block.WALL.equals(getFacingBlock())) {
            throw new InvalidMovementException("Facing wall, hence can't move forward");
        }
        final Coordinate aCoordinates = findCoordinate();
        movements.setPossibleMovements(maze, aCoordinates);
        coordinate = aCoordinates;
        recordMovement();
    }


    public void turnLeft() {
        direction = Direction.turnLeft(direction);
    }

    public void turnRight() {
        direction = Direction.turnRight(direction);
    }

    public Block getFacingBlock() {
        final Coordinate aCoordinate = findCoordinate();
        return maze.getBlock(aCoordinate.getX(), aCoordinate.getY());
    }

    public Set<Direction> getAvailableMovementOptions() {
        return movements.getAvailableMovementOptions();
    }

    public List<Record> getMovementsRecord() {
        return unmodifiableList(records);
    }


    void recordMovement() {
        records.add(new Record(this.coordinate, this.direction));
    }

    private Coordinate findCoordinate() {
        if (NORTH.equals(direction)) {
            return new Coordinate(coordinate.getX(), coordinate.getY() - 1);
        } else if (EAST.equals(direction)) {
            return new Coordinate(coordinate.getX() + 1, coordinate.getY());
        } else if (SOUTH.equals(direction)) {
            return new Coordinate(coordinate.getX(), coordinate.getY() + 1);
        } else {
            return new Coordinate(coordinate.getX() - 1, coordinate.getY());
        }
    }
}
