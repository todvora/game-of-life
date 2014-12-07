package cz.tomasdvorak.gameoflife.cells;

public class AgingCell implements Cell {

    private final CellType type;
    private final int age;

    public AgingCell(final CellType type, final int age) {
        this.type = type;
        this.age = age;
    }

    @Override
    public boolean isAlive() {
        return type.isAlive();
    }

    @Override
    public int getAge() {
        return age;
    }
}
