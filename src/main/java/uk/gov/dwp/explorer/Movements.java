package uk.gov.dwp.explorer;

import static java.util.Collections.unmodifiableSet;

import uk.gov.dwp.constants.Block;
import uk.gov.dwp.constants.Direction;
import uk.gov.dwp.maze.Maze;
import uk.gov.dwp.vo.Coordinate;

import java.util.HashSet;
import java.util.Set;

public class Movements {

    private final Set<Direction> availableMovements = new HashSet<>();

    public Set<Direction> getAvailableMovementOptions() {
        return unmodifiableSet(availableMovements);
    }

    public void setPossibleMovements(final Maze maze, final Coordinate coordinate) {
        availableMovements.clear();
        updateMovements(Direction.NORTH, maze.getBlock(coordinate.getX(), coordinate.getY() - 1));
        updateMovements(Direction.EAST, maze.getBlock(coordinate.getX() + 1, coordinate.getY()));
        updateMovements(Direction.SOUTH, maze.getBlock(coordinate.getX(), coordinate.getY() + 1));
        updateMovements(Direction.WEST, maze.getBlock(coordinate.getX() - 1, coordinate.getY()));
    }

    private void updateMovements(Direction direction, Block block) {
        if (Block.EMPTY.equals(block)) {
            availableMovements.add(direction);
        }
    }

}
