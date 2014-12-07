package cz.tomasdvorak.gameoflife.core;

import cz.tomasdvorak.gameoflife.cells.LifeMap;
import cz.tomasdvorak.gameoflife.strategy.Strategy;


import java.awt.Point;
import java.util.concurrent.ArrayBlockingQueue;

public class Game extends Thread {

    private Strategy strategy;
    private final int mapDimension;
    private DataProducer dataProducer;

    public Game(final Strategy strategy, final LifeMap initialMap) {
        this.strategy = strategy;
        this.mapDimension = initialMap.getDimension();
        dataProducer = createDataProducer(strategy, initialMap);
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(final Strategy strategy) {
        this.strategy = strategy;
    }

    public void restart(final LifeMap initialMap) {
        dataProducer.shutdown();
        dataProducer = createDataProducer(strategy, initialMap);
        dataProducer.start();
    }

    private DataProducer createDataProducer(final Strategy strategy, final LifeMap initialMap) {
        return new DataProducer(initialMap, strategy);
    }

    @Override
    public synchronized void start() {
        super.start();
        dataProducer.start();
    }

    public LifeMap getNextFrame() throws InterruptedException {
        return dataProducer.getNextMap();
    }

    public int getMapDimension() {
        return mapDimension;
    }

    public void feedProducer(final Point point) {
        dataProducer.addPoint(point);
    }
}
