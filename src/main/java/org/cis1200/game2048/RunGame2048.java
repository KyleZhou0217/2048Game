package org.cis1200.game2048;

import java.awt.*;
import javax.swing.*;

/**
 * This class sets up the top-level frame and widgets for the GUI.
 */
public class RunGame2048 implements Runnable {
    public void run() {
        final JFrame frame = new JFrame("Game 2048 Main Menu");
        frame.setLocation(300, 300);

        final JPanel status_panel = new JPanel();
        frame.add(status_panel, BorderLayout.SOUTH);
        final JLabel status = new JLabel(
                "<html>Instructions: <br><br>" +
                        "Objective: Combine blocks of same value through using" +
                        " arrow keys and try to get a 2048 block. "
                        +
                        "<br><br>"
                        +
                        "Arrow Keys: Tilt/slides the board in direction of arrow key. <br><br>" +
                        "Space Bar: Reverts board to state before previous action. <br><br>" +
                        "Click the save buttons in the main menu to load " +
                        "that save, and click the corresponding " +
                        "save in game <br>"
                        +
                        "to save the current board to that save slot. <br><br>" +
                        "Reset button in game used for restarting the " +
                        "game, undo button has same feature as space " +
                        "bar.</html>"
        );
        status_panel.add(status);

        final GameBoard board = new GameBoard();

        final JPanel control_panel = new JPanel();
        frame.add(control_panel, BorderLayout.NORTH);
        final JButton newGame = new JButton("New Game");
        newGame.addActionListener(e -> {
            run1(board);
            board.reset();
            frame.dispose();
        });
        final JButton save1 = new JButton("Save 1");
        save1.addActionListener(e -> {
            run1(board);
            board.load(1);
            frame.dispose();
        });
        final JButton save2 = new JButton("Save 2");
        save2.addActionListener(e -> {
            run1(board);
            board.load(2);
            frame.dispose();
        });
        final JButton save3 = new JButton("Save 3");
        save3.addActionListener(e -> {
            run1(board);
            board.load(3);
            frame.dispose();
        });
        control_panel.add(newGame);
        control_panel.add(save1);
        control_panel.add(save2);
        control_panel.add(save3);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void run1(GameBoard board) {
        // NOTE: the 'final' keyword denotes immutability even for local variables.

        // Top-level frame in which game components live
        final JFrame frame = new JFrame("2048");
        frame.setLocation(300, 300);

        // status
        final JPanel status_panel = new JPanel();
        frame.add(status_panel, BorderLayout.SOUTH);
        final JLabel status = new JLabel("Game in Progress");
        status_panel.add(status);
        board.setStatus(status);
        // Game board
        frame.add(board, BorderLayout.CENTER);

        // Reset button
        final JPanel control_panel = new JPanel();
        frame.add(control_panel, BorderLayout.NORTH);

        // Note here that when we add an action listener to the reset button, we
        // define it as an anonymous inner class that is an instance of
        // ActionListener with its actionPerformed() method overridden. When the
        // button is pressed, actionPerformed() will be called.
        final JButton reset = new JButton("Reset");
        reset.addActionListener(e -> board.reset());

        final JButton undo = new JButton("Undo");
        undo.addActionListener(e -> board.undo());
        final JButton mainMenu = new JButton("Main Menu");
        mainMenu.addActionListener(e -> {
            run();
            frame.dispose();
        });
        final JButton save1 = new JButton("Save 1");
        save1.addActionListener(e -> {
            board.save(1);
            run();
            frame.dispose();
        });
        final JButton save2 = new JButton("Save 2");
        save2.addActionListener(e -> {
            board.save(2);
            run();
            frame.dispose();
        });
        final JButton save3 = new JButton("Save 3");
        save3.addActionListener(e -> {
            board.save(3);
            run();
            frame.dispose();
        });
        control_panel.add(save1);
        control_panel.add(save2);
        control_panel.add(save3);
        control_panel.add(reset);
        control_panel.add(undo);
        control_panel.add(mainMenu);
        // Put the frame on the screen
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}