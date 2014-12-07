package cz.tomasdvorak.gameoflife.utils;

import cz.tomasdvorak.gameoflife.cells.Cell;

@FunctionalInterface
public interface MapConsumer {
    void accept(int x, int y, Cell cell);
}
