package uk.gov.dwp.maze;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static uk.gov.dwp.common.MazeFileReader.getLinesAsListFromMazeFile;
import static uk.gov.dwp.maze.Mazes.newMaze;

import uk.gov.dwp.constants.Block;
import uk.gov.dwp.maze.exception.InvalidMazeException;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class MazeTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();


    @Test
    public void shouldContainWallsEmptySpaceOneStartAndOneFinish() throws Exception {

        final List<String> lines = getLinesAsListFromMazeFile("Maze_Valid.txt");

        final Maze maze = newMaze(lines);

        assertThat(maze.getWallsCount(), is(106L));
        assertThat(maze.getEmptySpacesCount(), is(57L));
        assertThat(maze.getBlock(4, 3), is(Block.START));
        assertThat(maze.getBlock(1, 10), is(Block.FINISH));
    }

    @Test
    public void shouldFailConstructingMaze() throws Exception {

        final List<String> lines = getLinesAsListFromMazeFile("Maze_Invalid.txt");
        expectedException.expect(InvalidMazeException.class);
        expectedException.expectMessage("Multiple START type, expected only One");
        newMaze(lines);
    }


    @Test
    public void shouldFindStartBlockWithCordinateXas4AndYas3() throws Exception {

        final List<String> lines = getLinesAsListFromMazeFile("Maze_Valid.txt");

        final Maze maze = newMaze(lines);
        final Block block = maze.getBlock(4, 3);

        assertThat(block, is(Block.START));
    }

}