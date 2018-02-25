package uk.gov.dwp.maze;

import static java.util.stream.IntStream.range;
import static uk.gov.dwp.constants.Block.FINISH;
import static uk.gov.dwp.constants.Block.START;
import static uk.gov.dwp.constants.Block.fromString;

import uk.gov.dwp.constants.Block;
import uk.gov.dwp.maze.exception.InvalidMazeException;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public final class Mazes {

    private Mazes() {
    }

    public static Maze newMaze(final List<String> lines) throws InvalidMazeException {

        final Block[][] blocks = lines.stream()
                .map(line -> line.chars().mapToObj(letter -> fromString((char) letter))
                        .toArray(Block[]::new)).toArray(Block[][]::new);

        shouldBeOnlyOneBlockTypeOf(START, blocks);
        shouldBeOnlyOneBlockTypeOf(FINISH, blocks);

        return new Maze(blocks);
    }

    private static void shouldBeOnlyOneBlockTypeOf(final Block block, final Block[][] blocks) throws InvalidMazeException {

        final AtomicInteger count = new AtomicInteger();
        range(0, blocks.length)
                .forEach(i -> range(0, blocks.length)
                        .forEach(j -> {
                            if (blocks[i][j].equals(block)) {
                                count.getAndIncrement();
                            }
                        }));
        if (count.get() != 1) {
            throw new InvalidMazeException(String.format("Multiple %s type, expected only One ", block));
        }
    }
}
