package uk.gov.dwp.explorer;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static uk.gov.dwp.common.MazeFileReader.getLinesAsListFromMazeFile;
import static uk.gov.dwp.constants.Block.EMPTY;
import static uk.gov.dwp.constants.Direction.EAST;
import static uk.gov.dwp.constants.Direction.NORTH;
import static uk.gov.dwp.constants.Direction.SOUTH;
import static uk.gov.dwp.constants.Direction.WEST;
import static uk.gov.dwp.maze.Mazes.newMaze;

import uk.gov.dwp.constants.Block;
import uk.gov.dwp.constants.Direction;
import uk.gov.dwp.explorer.exception.InvalidMovementException;
import uk.gov.dwp.maze.Maze;
import uk.gov.dwp.maze.exception.InvalidMazeException;
import uk.gov.dwp.vo.Coordinate;
import uk.gov.dwp.vo.Record;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Set;

import com.google.common.collect.ImmutableSet;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ExplorerTest {

    private static final Coordinate startCoordinate = new Coordinate(4, 3);

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    private Explorer explorer;

    @Before
    public void setUp() throws IOException, URISyntaxException, InvalidMazeException {
        final Maze maze = newMaze(getLinesAsListFromMazeFile("Maze_Valid.txt"));
        explorer = Explorers.newExplorer(maze, startCoordinate);
    }

    @Test
    public void shouldMoveForward() throws InvalidMovementException {
        explorer.turnRight();
        explorer.moveForward();

        final Block fontBlock = explorer.getFacingBlock();
        final Set<Direction> movementOptions = explorer.getAvailableMovementOptions();

        assertThat(fontBlock, is(EMPTY));
        assertThat(movementOptions, is(ImmutableSet.of(NORTH, EAST, SOUTH)));
    }

    @Test
    public void shouldThrowExceptionOnInvalidMoveForward() throws InvalidMovementException {

        expectedException.expect(InvalidMovementException.class);
        expectedException.expectMessage("Facing wall, hence can't move forward");

        explorer.moveForward();
    }


    @Test
    public void shouldReturnWallWhenTurnLeft() {

        explorer.turnLeft();

        final Block frontBlock = explorer.getFacingBlock();
        assertThat(frontBlock, is(EMPTY));
    }

    @Test
    public void shouldReturnEmptySpaceWhenTurnRight() {

        explorer.turnRight();

        final Block frontBlock = explorer.getFacingBlock();
        assertThat(frontBlock, is(EMPTY));
    }

    @Test
    public void shouldReturn2AvailableMovementOptions() {

        final Set<Direction> movementOptions = explorer.getAvailableMovementOptions();

        assertThat(movementOptions, is(ImmutableSet.of(EAST, WEST)));
    }

    @Test
    public void movingForwardShouldUpdateLocationHistory() throws Exception {
        explorer.turnRight();
        explorer.moveForward();

        final List<Record> reports = explorer.getMovementsRecord();

        assertThat(reports.size(), CoreMatchers.is(2));

        final Record record_0 = reports.get(0);
        assertThat(record_0.getDirection(), CoreMatchers.is(NORTH));
        final Coordinate coordinate_0 = record_0.getCoordinate();
        assertThat(coordinate_0.getX(), CoreMatchers.is(4));
        assertThat(coordinate_0.getY(), CoreMatchers.is(3));


        final Record record_1 = reports.get(1);
        assertThat(record_1.getDirection(), CoreMatchers.is(EAST));
        final Coordinate coordinate_1 = record_1.getCoordinate();
        assertThat(coordinate_1.getX(), CoreMatchers.is(5));
        assertThat(coordinate_1.getY(), CoreMatchers.is(3));
    }
}