package cz.tomasdvorak.gameoflife.strategy;

import cz.tomasdvorak.gameoflife.cells.Cell;

public interface GameStrategy {
    public Cell nextGen(Cell current, int surroundingCount);
}
