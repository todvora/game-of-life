package cz.tomasdvorak.gameoflife.gui;

import cz.tomasdvorak.gameoflife.cells.MapFactory;
import cz.tomasdvorak.gameoflife.core.Game;
import cz.tomasdvorak.gameoflife.strategy.Strategy;

import javax.swing.*;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.util.*;

class GameGui {

    private final JFrame jFrame;

    public GameGui(final Game game) throws HeadlessException {
        jFrame = new JFrame("Game of life");
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        final Container container = jFrame.getContentPane();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        final JPanel controls = new JPanel();
        controls.setLayout(new FlowLayout());



        final JComboBox<Strategy> combo = new JComboBox<>(Strategy.values());
        combo.setSelectedIndex(game.getStrategy().ordinal());

        controls.add(combo);
        final JButton clearButton = new JButton("Clear map");
        clearButton.addActionListener(e -> game.restart(MapFactory.empty(game.getMapDimension())));
        controls.add(clearButton);

        final JButton newRandomButton = new JButton("New random map");
        newRandomButton.addActionListener(e -> game.restart(MapFactory.random(game.getMapDimension())));
        controls.add(newRandomButton);

        final JTextArea label = createTextArea(container, game.getStrategy().getDescription());

        final GameMapPanel map = new GameMapPanel(game);
        container.add(controls);

        container.add(map);
        container.add(label);

        combo.addActionListener(e -> {
            final Strategy selectedItem = (Strategy) combo.getSelectedItem();
            game.setStrategy(selectedItem);
            game.restart(MapFactory.random(game.getMapDimension()));
            label.setText(selectedItem.getDescription());
        });

        map.addMouseMotionListener(new MouseListener(game));
    }

    private JTextArea createTextArea(final Container cp, final String initialContentText) {
        final JTextArea label = new JTextArea(initialContentText);
        label.setEditable(false);
        label.setLineWrap(true);
        label.setWrapStyleWord(true);
        label.setBackground(cp.getBackground());
        label.setMargin(new Insets(10, 10, 10, 10));
        return label;
    }

    public void start() {
        jFrame.pack();
        jFrame.setVisible(true);
    }

}
