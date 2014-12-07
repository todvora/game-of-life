package cz.tomasdvorak.gameoflife.cells;

import cz.tomasdvorak.gameoflife.utils.MapConsumer;

public interface LifeMap {

    int getDimension();

    void foreachCell(final MapConsumer consumer);

    Cell getCell(final int rowIndex, final int colIndex);

    public void resuscitateCell(final int x, final int y);
}
