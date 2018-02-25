package uk.gov.dwp.explorer;

import uk.gov.dwp.constants.Direction;
import uk.gov.dwp.maze.Maze;
import uk.gov.dwp.vo.Coordinate;

public final class Explorers {

    private Explorers() {
    }

    public static Explorer newExplorer(final Maze maze, final Coordinate startCoordinate) {

        final Movements movements = new Movements();
        movements.setPossibleMovements(maze, startCoordinate);

        final Explorer explorer = new Explorer(maze, startCoordinate, Direction.NORTH, movements);
        explorer.recordMovement();

        return explorer;
    }
}
