package cz.tomasdvorak.gameoflife.cells;

public enum CellType implements Cell {
    ALIVE,
    DEAD;

    public boolean isAlive() {
        return ALIVE == this;
    }

    @Override
    public int getAge() {
        return 1;
    }


    @Override
    public String toString() {
        return this == ALIVE ? "X" : "_";
    }
}
