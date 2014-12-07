package cz.tomasdvorak.gameoflife.core.processor;

import cz.tomasdvorak.gameoflife.cells.Cell;
import cz.tomasdvorak.gameoflife.cells.CellGroup;
import cz.tomasdvorak.gameoflife.cells.GroupParser;
import cz.tomasdvorak.gameoflife.cells.LifeMap;
import cz.tomasdvorak.gameoflife.cells.MapFactory;
import cz.tomasdvorak.gameoflife.strategy.Strategy;

public class ParallelProcessor implements GenerationProcessor {

    @Override
    public LifeMap nextGen(final LifeMap oldGen, final Strategy strategy) {
        // build runs in parallel, due to Arrays.parallelSetAll
        return MapFactory.build(oldGen.getDimension(), (x, y) -> getNextGenCell(oldGen, x, y, strategy));
    }

    private Cell getNextGenCell(final LifeMap map, final int x, final int y, final Strategy strategy) {
        final CellGroup group = getGroup(map, x, y);
        return strategy.getImplementation().nextGen(group.getCenter(), group.getSum());
    }

    private CellGroup getGroup(final LifeMap map, final int x, final int y) {
        return GroupParser.parse(map, x, y);
    }
}
