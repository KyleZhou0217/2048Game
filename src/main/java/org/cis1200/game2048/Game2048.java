package org.cis1200.game2048;

import java.util.LinkedList;
import java.util.Random;

public class Game2048 {

    private Square[][] gameBoard;
    private LinkedList<Square[][]> boardVersions;

    /**
     * Constructor sets up game state.
     */
    public Game2048() {
        reset();
    }

    // for JUnit testing
    public Game2048(int[][] board) {
        gameBoard = new Square[4][4];
        boardVersions = new LinkedList<Square[][]>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == 0) {
                    gameBoard[i][j] = null;
                } else {
                    gameBoard[i][j] = new Square(0, 0, board[i][j]);
                    gameBoard[i][j].setX(j);
                    gameBoard[i][j].setY(i);
                }
            }
        }
    }

    public Square[][] getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(Square[][] gameBoard) {
        this.gameBoard = gameBoard;
    }

    public LinkedList<Square[][]> getBoardVersions() {
        return boardVersions;
    }

    public boolean checkWin() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (gameBoard[i][j] != null) {
                    if (gameBoard[i][j].getNumber() == 2048) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean checkLoss() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (gameBoard[i][j] == null) {
                    return false;
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                if (gameBoard[j][i].getNumber() == gameBoard[j][i + 1].getNumber()) {
                    return false;
                }
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                if (gameBoard[j][i].getNumber() == gameBoard[j + 1][i].getNumber()) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkBoardFull() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (gameBoard[i][j] == null) {
                    return false;
                }
            }
        }
        return true;
    }

    public void changeBoard(Direction d) {
        if (d == Direction.UP) {
            boardVersions.addFirst(deepCopy(gameBoard));
            for (int j = 0; j < 4; j++) {
                for (int i = 0; i < 3; i++) {
                    if (gameBoard[i][j] == null) {
                        for (int k = i + 1; k < 4; k++) {
                            if (gameBoard[k][j] != null) {
                                gameBoard[i][j] = gameBoard[k][j];
                                gameBoard[i][j].setX(j);
                                gameBoard[i][j].setY(i);
                                gameBoard[k][j] = null;
                                break;
                            }
                        }
                    }
                }
            }
            for (int j = 0; j < 4; j++) {
                for (int i = 0; i < 3; i++) {
                    if (gameBoard[i][j] != null && gameBoard[i + 1][j] != null) {
                        if (gameBoard[i][j].getNumber() == gameBoard[i + 1][j].getNumber()) {
                            gameBoard[i][j].setNumber(gameBoard[i][j].getNumber() * 2);
                            gameBoard[i + 1][j] = null;
                        }
                    }
                }
            }
            for (int j = 0; j < 4; j++) {
                for (int i = 0; i < 3; i++) {
                    if (gameBoard[i][j] == null) {
                        for (int k = i + 1; k < 4; k++) {
                            if (gameBoard[k][j] != null) {
                                gameBoard[i][j] = gameBoard[k][j];
                                gameBoard[i][j].setX(j);
                                gameBoard[i][j].setY(i);
                                gameBoard[k][j] = null;
                                break;
                            }
                        }
                    }
                }
            }
        } else if (d == Direction.DOWN) {
            boardVersions.addFirst(deepCopy(gameBoard));
            for (int j = 0; j < 4; j++) {
                for (int i = 3; i > 0; i--) {
                    if (gameBoard[i][j] == null) {
                        for (int k = i - 1; k >= 0; k--) {
                            if (gameBoard[k][j] != null) {
                                gameBoard[i][j] = gameBoard[k][j];
                                gameBoard[i][j].setX(j);
                                gameBoard[i][j].setY(i);
                                gameBoard[k][j] = null;
                                break;
                            }
                        }
                    }
                }
            }
            for (int j = 0; j < 4; j++) {
                for (int i = 3; i > 0; i--) {
                    if (gameBoard[i][j] != null && gameBoard[i - 1][j] != null) {
                        if (gameBoard[i][j].getNumber() == gameBoard[i - 1][j].getNumber()) {
                            gameBoard[i][j].setNumber(gameBoard[i][j].getNumber() * 2);
                            gameBoard[i - 1][j] = null;
                        }
                    }
                }
            }
            for (int j = 0; j < 4; j++) {
                for (int i = 3; i > 0; i--) {
                    if (gameBoard[i][j] == null) {
                        for (int k = i - 1; k >= 0; k--) {
                            if (gameBoard[k][j] != null) {
                                gameBoard[i][j] = gameBoard[k][j];
                                gameBoard[i][j].setX(j);
                                gameBoard[i][j].setY(i);
                                gameBoard[k][j] = null;
                                break;
                            }
                        }
                    }
                }
            }
        } else if (d == Direction.LEFT) {
            boardVersions.addFirst(deepCopy(gameBoard));
            for (int j = 0; j < 4; j++) {
                for (int i = 0; i < 3; i++) {
                    if (gameBoard[j][i] == null) {
                        for (int k = i + 1; k < 4; k++) {
                            if (gameBoard[j][k] != null) {
                                gameBoard[j][i] = gameBoard[j][k];
                                gameBoard[j][i].setX(i);
                                gameBoard[j][i].setY(j);
                                gameBoard[j][k] = null;
                                break;
                            }
                        }
                    }
                }
            }
            for (int j = 0; j < 4; j++) {
                for (int i = 0; i < 3; i++) {
                    if (gameBoard[j][i] != null && gameBoard[j][i + 1] != null) {
                        if (gameBoard[j][i].getNumber() == gameBoard[j][i + 1].getNumber()) {
                            gameBoard[j][i].setNumber(gameBoard[j][i].getNumber() * 2);
                            gameBoard[j][i + 1] = null;
                        }
                    }
                }
            }
            for (int j = 0; j < 4; j++) {
                for (int i = 0; i < 3; i++) {
                    if (gameBoard[j][i] == null) {
                        for (int k = i + 1; k < 4; k++) {
                            if (gameBoard[j][k] != null) {
                                gameBoard[j][i] = gameBoard[j][k];
                                gameBoard[j][i].setX(i);
                                gameBoard[j][i].setY(j);
                                gameBoard[j][k] = null;
                                break;
                            }
                        }
                    }
                }
            }
        } else if (d == Direction.RIGHT) {
            boardVersions.addFirst(deepCopy(gameBoard));
            for (int j = 0; j < 4; j++) {
                for (int i = 3; i > 0; i--) {
                    if (gameBoard[j][i] == null) {
                        for (int k = i - 1; k >= 0; k--) {
                            if (gameBoard[j][k] != null) {
                                gameBoard[j][i] = gameBoard[j][k];
                                gameBoard[j][i].setX(i);
                                gameBoard[j][i].setY(j);
                                gameBoard[j][k] = null;
                                break;
                            }
                        }
                    }
                }
            }
            for (int j = 0; j < 4; j++) {
                for (int i = 3; i > 0; i--) {
                    if (gameBoard[j][i] != null && gameBoard[j][i - 1] != null) {
                        if (gameBoard[j][i].getNumber() == gameBoard[j][i - 1].getNumber()) {
                            gameBoard[j][i].setNumber(gameBoard[j][i].getNumber() * 2);
                            gameBoard[j][i - 1] = null;
                        }
                    }
                }
            }
            for (int j = 0; j < 4; j++) {
                for (int i = 3; i > 0; i--) {
                    if (gameBoard[j][i] == null) {
                        for (int k = i - 1; k >= 0; k--) {
                            if (gameBoard[j][k] != null) {
                                gameBoard[j][i] = gameBoard[j][k];
                                gameBoard[j][i].setX(i);
                                gameBoard[j][i].setY(j);
                                gameBoard[j][k] = null;
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    public void reset() {
        gameBoard = new Square[4][4];
        boardVersions = new LinkedList<Square[][]>();
        for (int i = 0; i < 2; i++) {
            boolean done = false;
            while (!done) {
                Random r = new Random();
                int row = r.nextInt(4);
                int col = r.nextInt(4);
                int value;
                if (r.nextInt(9) != 8) {
                    value = 2;
                } else {
                    value = 4;
                }
                if (gameBoard[row][col] == null) {
                    gameBoard[row][col] = new Square(
                            Square.getWidth() * col + GameBoard.SPACE * (col + 1),
                            Square.getHeight() * row + GameBoard.SPACE * (row + 1), value
                    );
                    done = true;
                }
            }
        }
    }

    public void spawnSquare() {
        boolean done = false;
        while (!done) {
            Random r = new Random();
            int row = r.nextInt(4);
            int col = r.nextInt(4);
            int value;
            if (r.nextInt(9) != 8) {
                value = 2;
            } else {
                value = 4;
            }
            if (gameBoard[row][col] == null) {
                gameBoard[row][col] = new Square(
                        Square.getWidth() * col + GameBoard.SPACE * (col + 1),
                        Square.getHeight() * row + GameBoard.SPACE * (row + 1), value
                );
                done = true;
            }
        }
    }

    public void undo() {
        if (!boardVersions.isEmpty()) {
            gameBoard = boardVersions.removeFirst();
        }
    }

    private static Square[][] deepCopy(Square[][] board) {
        Square[][] copiedBoard = new Square[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] != null) {
                    copiedBoard[i][j] = new Square(
                            board[i][j].getX(), board[i][j].getY(), board[i][j].getNumber()
                    );
                } else {
                    copiedBoard[i][j] = null;
                }
            }
        }
        return copiedBoard;
    }

    // for JUnit testing
    public int[][] getBoardIntArray() {
        int[][] board = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (gameBoard[i][j] != null) {
                    board[i][j] = gameBoard[i][j].getNumber();
                }
            }
        }
        return board;
    }

    public static void main(String[] args) {

    }
}
