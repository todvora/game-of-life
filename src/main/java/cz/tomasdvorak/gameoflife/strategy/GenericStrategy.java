package cz.tomasdvorak.gameoflife.strategy;

import cz.tomasdvorak.gameoflife.cells.AgingCell;
import cz.tomasdvorak.gameoflife.cells.Cell;
import cz.tomasdvorak.gameoflife.cells.CellType;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GenericStrategy implements GameStrategy {

    private final Set<Integer> born;
    private final Set<Integer> survive;

    public GenericStrategy(final String rule) {
        final String[] strings = rule.split("/");
        this.survive = getSet(strings[0]);
        this.born = getSet(strings[1]);
    }

    private Set<Integer> getSet(final String string) {
        if(string.trim().isEmpty()) {
            return Collections.emptySet();
        }
        return Stream.of(string.split("")).map(Integer::parseInt).collect(Collectors.toSet());
    }

    @Override
    public Cell nextGen(final Cell current, final int surroundingCount) {
        if(current.isAlive()) {
            if(survive.contains(surroundingCount)) {
                final int oldAge = current.getAge() + 1;
                return new AgingCell(CellType.ALIVE, oldAge);
            } else {
                return CellType.DEAD;
            }
        } else {
            if(born.contains(surroundingCount)) {
                return new AgingCell(CellType.ALIVE, 0);
            } else {
                return CellType.DEAD;
            }
        }
    }
}
