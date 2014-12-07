package cz.tomasdvorak.gameoflife.cells;

public class CellGroup {

    private final int sum;
    private final Cell center;

    public CellGroup(final int sum, final Cell center) {
        this.sum = sum;
        this.center = center;
    }

    public int getSum() {
        return sum;
    }

    public Cell getCenter() {
        return center;
    }
}
