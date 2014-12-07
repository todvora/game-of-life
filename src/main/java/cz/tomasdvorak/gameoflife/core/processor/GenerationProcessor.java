package cz.tomasdvorak.gameoflife.core.processor;

import cz.tomasdvorak.gameoflife.cells.LifeMap;
import cz.tomasdvorak.gameoflife.strategy.Strategy;

public interface GenerationProcessor {
    public LifeMap nextGen(LifeMap oldGen, Strategy strategy);
}
