package cz.tomasdvorak.gameoflife.gui;

import cz.tomasdvorak.gameoflife.core.Game;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Random;
import java.util.stream.IntStream;

class MouseListener extends MouseMotionAdapter {

    private final Random r = new Random();
    private final Game game;

    public MouseListener(final Game game) {
        this.game = game;
    }

    @Override
    public void mouseDragged(final MouseEvent e) {
        final Point point = e.getPoint();
        IntStream.range(0, 10).forEach(v -> {
            point.translate(nextRandomNear(), nextRandomNear());
            game.feedProducer(point);
        });
    }

    /**
     * @return Random int in interval <-2, +2>
     */
    private int nextRandomNear() {
        return r.nextInt(4) - 2;
    }
}
