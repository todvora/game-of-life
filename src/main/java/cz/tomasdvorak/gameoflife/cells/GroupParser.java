package cz.tomasdvorak.gameoflife.cells;

import java.util.stream.IntStream;

public class GroupParser {

    public static CellGroup parse(final LifeMap map, final int pivotX, final int pivotY) {
        int count = 0;
        // matrix index mask
        // (-1,-1) (-1,0) (-1,1)
        // ( 0,-1)   X    ( 0,1)
        // ( 1,-1) ( 1,0) ( 1,1)
        for (int rowIndex = pivotX - 1; rowIndex <= pivotX + 1; rowIndex++) {
            for (int colIndex = pivotY - 1; colIndex <= pivotY + 1; colIndex++) {
                if(map.getCell(rowIndex, colIndex).isAlive() && !(rowIndex == pivotX && colIndex == pivotY)) {
                    count++;
                }
            }
        }
        return new CellGroup(count, map.getCell(pivotX, pivotY));
    }
}
