package org.cis1200.game2048;

/*
 * CIS 120 HW09 - TicTacToe Demo
 * (c) University of Pennsylvania
 * Created by Bayley Tuch, Sabrina Green, and Nicolas Corona in Fall 2020.
 */

import java.awt.*;
import javax.swing.*;

/**
 * This class sets up the top-level frame and widgets for the GUI.
 * 
 * This game adheres to a Model-View-Controller design framework. This
 * framework is very effective for turn-based games. We STRONGLY
 * recommend you review these lecture slides, starting at slide 8,
 * for more details on Model-View-Controller:
 * https://www.seas.upenn.edu/~cis120/current/files/slides/lec37.pdf
 * 
 * In a Model-View-Controller framework, Game initializes the view,
 * implements a bit of controller functionality through the reset
 * button, and then instantiates a GameBoard. The GameBoard will
 * handle the rest of the game's view and controller functionality, and
 * it will instantiate a TicTacToe object to serve as the game's model.
 */
public class RunGame2048 implements Runnable {
    public void run() {
        final JFrame frame = new JFrame("Game 2048 Main Menu");
        frame.setLocation(300,300);
        final JPanel control_panel = new JPanel();
        frame.add(control_panel, BorderLayout.NORTH);
        final JButton newGame = new JButton("New Game");
        newGame.addActionListener(e -> {run1(); frame.dispose();});
        final JButton save1 = new JButton("Save 1");
        final JButton save2 = new JButton("Save 2");
        final JButton save3 = new JButton("Save 3");
        control_panel.add(newGame);
        control_panel.add(save1);
        control_panel.add(save2);
        control_panel.add(save3);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    public void run1() {
        // NOTE: the 'final' keyword denotes immutability even for local variables.

        // Top-level frame in which game components live
        final JFrame frame = new JFrame("2048");
        frame.setLocation(300, 300);

        // Status panel
        final JPanel status_panel = new JPanel();
        frame.add(status_panel, BorderLayout.SOUTH);
        final JLabel status = new JLabel("Setting up...");
        status_panel.add(status);

        // Game board
        final GameBoard board = new GameBoard(status);
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
        mainMenu.addActionListener(e -> {run(); frame.dispose();});
        control_panel.add(reset);
        control_panel.add(undo);
        control_panel.add(mainMenu);
        // Put the frame on the screen
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Start the game
        board.reset();
    }
}