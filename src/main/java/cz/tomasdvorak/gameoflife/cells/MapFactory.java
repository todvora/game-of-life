package cz.tomasdvorak.gameoflife.cells;

import java.util.Arrays;

public class MapFactory {

    public static LifeMap random(final int dimension) {
        return build(dimension, (x, y) -> ((Math.random() > 0.5 && isInTheMiddle(dimension, x, y)) ? CellType.ALIVE : CellType.DEAD));
    }

    private static boolean isInTheMiddle(final int dimension, final int x, final int y) {
        return x > dimension / 3 && x < (dimension / 3) * 2 && y > dimension / 3 && y < (dimension / 3) * 2;
    }

    public static LifeMap empty(final int dimension) {
        return build(dimension, (x, y) -> CellType.DEAD);
    }

    public static LifeMap build(final int dimension, final CellGenerator generator) {
        final Cell[][] cells = new Cell[dimension][];
        Arrays.parallelSetAll(cells, rowIndex -> {
            final Cell[] row = new Cell[dimension];
            Arrays.setAll(row, cellIndex -> generator.apply(rowIndex, cellIndex));
            return row;
        });
        return new MatrixMap(cells);
    }

    @FunctionalInterface
    public interface CellGenerator {
        Cell apply(int x, int y);
    }
}
