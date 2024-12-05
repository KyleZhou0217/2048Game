package org.cis1200.game2048;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;


public class GameBoard extends JPanel {

    private Game2048 tfe; // model for the game
    private JLabel status; // current status text

    // Game constants
    public static final int SPACE = 15;
    public static final int BOARD_WIDTH = 4 * Square.getWidth() + SPACE * 5;
    public static final int BOARD_HEIGHT = 4 * Square.getHeight() + SPACE * 5;



    /**
     * Initializes the game board.
     */
    public GameBoard() {
        // creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Enable keyboard focus on the court area. When this component has the
        // keyboard focus, key events are handled by its key listener.
        setFocusable(true);

        tfe = new Game2048(); // initializes model for the game
//        boardImg = new BufferedImage(BOARD_WIDTH, BOARD_HEIGHT, BufferedImage.TYPE_INT_RGB);
//        gameBoard = new Square[4][4];

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    tfe.getBoardVersions().addFirst(deepCopy(tfe.getGameBoard()));
                    tfe.changeBoard(Direction.LEFT);
                    endOfRoundUpdate();
                    repaint();
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    tfe.getBoardVersions().addFirst(deepCopy(tfe.getGameBoard()));
                    tfe.changeBoard(Direction.RIGHT);
                    endOfRoundUpdate();
                    repaint();
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    tfe.getBoardVersions().addFirst(deepCopy(tfe.getGameBoard()));
                    tfe.changeBoard(Direction.DOWN);
                    endOfRoundUpdate();
                    repaint();
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    tfe.getBoardVersions().addFirst(deepCopy(tfe.getGameBoard()));
                    tfe.changeBoard(Direction.UP);
                    endOfRoundUpdate();
                    repaint();
                } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    undo();
                }
            }
        });
    }

    public void setStatus(JLabel status) {
        this.status = status;
    }
    /**
     * (Re-)sets the game to its initial state.
     */
    public void reset() {
        tfe.reset();
        status.setText("Game in Progress");
        repaint();

        // Makes sure this component has keyboard/mouse focus
        requestFocusInWindow();
    }

    public void undo() {
        tfe.undo();
        repaint();
        requestFocusInWindow();
    }

    public void endOfRoundUpdate() {
        if (tfe.checkWin()) {
            status.setText("WIN!");
        } else if (tfe.checkLoss()) {
            status.setText("LOSS!");
        } else {
            if (!tfe.checkBoardFull()) {
                tfe.spawnSquare();
                if (tfe.checkLoss()) {
                    status.setText("LOSS!");
                }
            }
        }
    }

    public void save(int number) {
        GameSaverLoader saver = new GameSaverLoader();
        if (number == 1) {
            saver.saveGame("save1.txt", tfe.getGameBoard());
        } else if (number == 2) {
            saver.saveGame("save2.txt", tfe.getGameBoard());
        } else if (number == 3) {
            saver.saveGame("save3.txt", tfe.getGameBoard());
        }
        requestFocusInWindow();
    }

    public void load(int number) {
        GameSaverLoader loader = new GameSaverLoader();
        if (number == 1) {
            tfe.setGameBoard(loader.loadGame("save1.txt"));
        } else if (number == 2) {
            tfe.setGameBoard(loader.loadGame("save2.txt"));
        } else if (number == 3) {
            tfe.setGameBoard(loader.loadGame("save3.txt"));
        }
        requestFocusInWindow();
    }

//
//    /**
//     * Updates the JLabel to reflect the current state of the game.
//     */
//    private void updateStatus() {
//        if (ttt.getCurrentPlayer()) {
//            status.setText("Player 1's Turn");
//        } else {
//            status.setText("Player 2's Turn");
//        }
//
//        int winner = ttt.checkWinner();
//        if (winner == 1) {
//            status.setText("Player 1 wins!!!");
//        } else if (winner == 2) {
//            status.setText("Player 2 wins!!!");
//        } else if (winner == 3) {
//            status.setText("It's a tie.");
//        }
//    }

    /**
     * Draws the game board.
     * 
     * There are many ways to draw a game board. This approach
     * will not be sufficient for most games, because it is not
     * modular. All of the logic for drawing the game board is
     * in this method, and it does not take advantage of helper
     * methods. Consider breaking up your paintComponent logic
     * into multiple methods or classes, like Mushroom of Doom.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(new Color(184, 173, 158));
        g.fillRect(0, 0, BOARD_WIDTH, BOARD_HEIGHT);
        g.setColor(new Color(205, 192, 176));
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                g.fillRect(Square.getWidth() * j + SPACE * (j + 1),
                        Square.getHeight() * i + SPACE * (i + 1),
                        Square.getWidth(), Square.getHeight());
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (tfe.getGameBoard()[i][j] != null){
                    tfe.getGameBoard()[i][j].draw(g);
                }
            }
        }
    }


    private static Square[][] deepCopy(Square[][] board) {
        Square[][] copiedBoard = new Square[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] != null) {
                    copiedBoard[i][j] = new Square(board[i][j].getX(), board[i][j].getY(), board[i][j].getNumber());
                } else {
                    copiedBoard[i][j] = null;
                }
            }
        }
        return copiedBoard;
    }
    /**
     * Returns the size of the game board.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
    }
}
