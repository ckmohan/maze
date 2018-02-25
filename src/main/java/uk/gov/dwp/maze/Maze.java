package uk.gov.dwp.maze;


import static java.util.Arrays.stream;
import static uk.gov.dwp.constants.Block.EMPTY;
import static uk.gov.dwp.constants.Block.WALL;

import uk.gov.dwp.constants.Block;

import java.util.Arrays;

public class Maze {

    private final Block[][] blocks;

    Maze(final Block[][] blocks) {
        this.blocks = blocks;
    }

    public long getWallsCount() {
        return countBlockType(WALL);
    }

    public long getEmptySpacesCount() {
        return countBlockType(EMPTY);
    }

    public Block getBlock(final int row, final int col) {
        return blocks[col][row];
    }

    private long countBlockType(final Block type) {
        return stream(blocks)
                .flatMap(Arrays::stream)
                .filter(e -> e.equals(type))
                .count();
    }
}
