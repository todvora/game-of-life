package cz.tomasdvorak.gameoflife.gui;

import cz.tomasdvorak.gameoflife.cells.LifeMap;
import cz.tomasdvorak.gameoflife.core.Game;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Image;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;


class GameMapPanel extends JPanel {

    public static final Color OLDGEN_COLOR = Color.red.darker();
    public static final Color DEFAULT_COLOR = Color.gray;

    private Image offscreen;
    private final Game game;

    private AtomicInteger fps = new AtomicInteger();

    public GameMapPanel(final Game game) throws HeadlessException {
        this.game = game;
        this.setPreferredSize(new Dimension(game.getMapDimension(), game.getMapDimension()));

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println("FPS is:" + fps.getAndSet(0) / 5);
            }
        }, 5000,5000);
    }

    @Override
    public void paint(final Graphics g) {
        if (offscreen == null) {
            offscreen = createImage(getWidth(), getHeight());
        }
        try {
            final LifeMap nextFrame = game.getNextFrame();
            render(offscreen, nextFrame);
            g.drawImage(offscreen, 0, 0, this);
            // TODO: is repaint() ok here?
            repaint();
        } catch (final InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void render(final Image image, final LifeMap map) {
        final Graphics2D g2 = (Graphics2D)image.getGraphics();
        g2.clearRect(0, 0, map.getDimension(), map.getDimension());
        map.foreachCell((x, y, cell) -> {
            if (cell.isAlive()) {
                if(cell.getAge() >= 10) {
                    g2.setPaint(OLDGEN_COLOR);
                } else {
                    g2.setPaint(DEFAULT_COLOR);
                }
                g2.drawLine(x, y, x, y);
            }
        });
        fps.incrementAndGet();
    }
}
