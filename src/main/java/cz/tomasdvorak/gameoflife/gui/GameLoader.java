package cz.tomasdvorak.gameoflife.gui;

import cz.tomasdvorak.gameoflife.cells.MapFactory;
import cz.tomasdvorak.gameoflife.core.Game;
import cz.tomasdvorak.gameoflife.strategy.Strategy;

public class GameLoader {

    private static final int SIZE = 800;

    public static void main(final String[] args) {
        final Game game = new Game(Strategy.DotLife, MapFactory.random(SIZE));
        game.start();
        final GameGui gameGui = new GameGui(game);
        gameGui.start();
    }
}
