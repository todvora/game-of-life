package cz.tomasdvorak.gameoflife.core;

import cz.tomasdvorak.gameoflife.cells.LifeMap;
import cz.tomasdvorak.gameoflife.core.processor.GenerationProcessor;
import cz.tomasdvorak.gameoflife.core.processor.ParallelProcessor;
import cz.tomasdvorak.gameoflife.strategy.Strategy;

import java.awt.Point;
import java.util.concurrent.ArrayBlockingQueue;

class DataProducer extends Thread {
    private static final GenerationProcessor PROCESSOR = new ParallelProcessor();
    private boolean shouldRun = true;

    private LifeMap currentMap;
    private final Strategy strategy;

    private final ArrayBlockingQueue<LifeMap> queue = new ArrayBlockingQueue<>(10, true);

    public DataProducer(final LifeMap initial, final Strategy strategy) {
        this.currentMap = initial;
        this.strategy = strategy;
    }

    @Override
    public void run() {
        while (!interrupted() && shouldRun) {
            final LifeMap nextGen = PROCESSOR.nextGen(currentMap, strategy);
            currentMap = nextGen;
            try {
               queue.put(nextGen);
            } catch (final InterruptedException e) {
                interrupt();
            }
        }
    }

    public void shutdown() {
        this.shouldRun = false;
    }

    public void addPoint(final Point point) {
        currentMap.resuscitateCell((int)point.getX(),(int)point.getY());
    }

    public LifeMap getNextMap() throws InterruptedException {
        return queue.take();
    }
}
