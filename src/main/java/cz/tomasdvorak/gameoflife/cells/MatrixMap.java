package cz.tomasdvorak.gameoflife.cells;

import cz.tomasdvorak.gameoflife.utils.MapConsumer;

public class MatrixMap implements LifeMap {

    private final Cell[][] cells;

    public MatrixMap(final Cell[][] cells) {
        this.cells = cells;
    }

    public int getDimension() {
        return cells.length;
    }

    public void foreachCell(final MapConsumer consumer) {
        for (int i = 0; i < getDimension(); i++) {
            for (int j = 0; j < getDimension(); j++) {
                consumer.accept(i, j, cells[i][j]);
            }
        }
    }

    public Cell getCell(final int rowIndex, final int colIndex) {
        if (isInRange(rowIndex, colIndex)) {
            return cells[rowIndex][colIndex];
        }
        return CellType.DEAD;
    }

    private boolean isInRange(final int rowIndex, final int colIndex) {
        return rowIndex >= 0 && rowIndex < getDimension() && colIndex >= 0 && colIndex < getDimension();
    }


    // TODO: map shouldn't be modifiable
    public void resuscitateCell(final int x, final int y) {
        if(isInRange(x,y)) {
            cells[x][y] = CellType.ALIVE;
        }
    }
}
