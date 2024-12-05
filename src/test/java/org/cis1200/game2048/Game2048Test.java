package org.cis1200.game2048;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class Game2048Test {

    @Test
    public void testGetBoardIntArray() {
        int[][] initBoard = new int[][]{
                {0, 0, 2, 0},
                {0, 0, 0, 0},
                {2, 0, 0, 0},
                {0, 0, 0, 0}
        };
        Game2048 tfe = new Game2048(initBoard);
        assertArrayEquals(initBoard, tfe.getBoardIntArray());
    }

    @Test
    public void testCheckWinTrue() {
        int[][] initBoard = new int[][]{
                {0, 0, 2, 0},
                {0, 0, 0, 0},
                {2, 0, 0, 0},
                {0, 0, 2048, 0}
        };
        Game2048 tfe = new Game2048(initBoard);
        assertTrue(tfe.checkWin());
    }

    @Test
    public void testCheckWinFalse() {
        int[][] initBoard = new int[][]{
                {2, 0, 2, 0},
                {0, 16, 0, 0},
                {2, 0, 0, 0},
                {0, 0, 1024, 0}
        };
        Game2048 tfe = new Game2048(initBoard);
        assertFalse(tfe.checkWin());
    }

    @Test
    public void testCheckLossWithEmptySpace() {
        int[][] initBoard = new int[][]{
                {0, 0, 2, 0},
                {0, 0, 0, 0},
                {2, 0, 0, 0},
                {0, 0, 2, 0}
        };
        Game2048 tfe = new Game2048(initBoard);
        assertFalse(tfe.checkLoss());
    }

    @Test
    public void testCheckLossFalseNoEmptySpace() {
        int[][] initBoard = new int[][]{
                {16, 4, 8, 2},
                {8, 2, 4, 16},
                {2, 128, 64, 2},
                {8, 2, 4, 2}
        };
        Game2048 tfe = new Game2048(initBoard);
        assertFalse(tfe.checkLoss());
    }

    @Test
    public void testCheckLossTrue() {
        int[][] initBoard = new int[][]{
                {16, 4, 8, 2},
                {8, 2, 4, 16},
                {2, 128, 64, 32},
                {8, 2, 4, 2}
        };
        Game2048 tfe = new Game2048(initBoard);
        assertTrue(tfe.checkLoss());
    }

    @Test
    public void testBoardFullFalse() {
        int[][] initBoard = new int[][]{
                {16, 4, 8, 2},
                {8, 0, 4, 16},
                {2, 128, 64, 32},
                {8, 2, 4, 2}
        };
        Game2048 tfe = new Game2048(initBoard);
        assertFalse(tfe.checkBoardFull());
    }

    @Test
    public void testBoardFullTrue() {
        int[][] initBoard = new int[][]{
                {16, 4, 8, 2},
                {8, 2, 4, 16},
                {2, 128, 64, 32},
                {8, 2, 4, 2}
        };
        Game2048 tfe = new Game2048(initBoard);
        assertTrue(tfe.checkBoardFull());
    }

    @Test
    public void testReset() {
        Game2048 tfe = new Game2048();
        tfe.reset();
        boolean containsTwoFour = false;
        boolean containsOtherValues = false;
        int numValues = 0;
        int[][] board = tfe.getBoardIntArray();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == 2 || board[i][j] == 4) {
                    containsTwoFour = true;
                    numValues++;
                } else if (board[i][j] != 0) {
                    containsOtherValues = true;
                    numValues++;
                }
            }
        }
        assertEquals(2, numValues);
        assertTrue(containsTwoFour);
        assertFalse(containsOtherValues);
    }

    @Test
    public void testSpawnSquare() {
        Game2048 tfe = new Game2048();
        tfe.reset();
        tfe.spawnSquare();
        boolean containsTwoFour = false;
        boolean containsOtherValues = false;
        int numValues = 0;
        int[][] board = tfe.getBoardIntArray();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == 2 || board[i][j] == 4) {
                    containsTwoFour = true;
                    numValues++;
                } else if (board[i][j] != 0) {
                    containsOtherValues = true;
                    numValues++;
                }
            }
        }
        assertEquals(3, numValues);
        assertTrue(containsTwoFour);
        assertFalse(containsOtherValues);
    }

    @Test
    public void testSpawnSquareMulti() {
        Game2048 tfe = new Game2048();
        tfe.reset();
        tfe.spawnSquare();
        tfe.spawnSquare();
        tfe.spawnSquare();
        boolean containsTwoFour = false;
        boolean containsOtherValues = false;
        int numValues = 0;
        int[][] board = tfe.getBoardIntArray();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == 2 || board[i][j] == 4) {
                    containsTwoFour = true;
                    numValues++;
                } else if (board[i][j] != 0) {
                    containsOtherValues = true;
                    numValues++;
                }
            }
        }
        assertEquals(5, numValues);
        assertTrue(containsTwoFour);
        assertFalse(containsOtherValues);
    }

    @Test
    public void testUndoUPOnce() {
        int[][] initBoard = new int[][]{
                {2, 0, 2, 0},
                {0, 16, 0, 0},
                {2, 0, 0, 0},
                {0, 0, 1024, 0}
        };
        Game2048 tfe = new Game2048(initBoard);
        tfe.changeBoard(Direction.UP);
        tfe.undo();
        assertArrayEquals(initBoard, tfe.getBoardIntArray());
    }

    @Test
    public void testUndoDOWNOnce() {
        int[][] initBoard = new int[][]{
                {2, 0, 2, 0},
                {0, 16, 0, 0},
                {2, 0, 0, 0},
                {0, 0, 1024, 0}
        };
        Game2048 tfe = new Game2048(initBoard);
        tfe.changeBoard(Direction.DOWN);
        tfe.undo();
        assertArrayEquals(initBoard, tfe.getBoardIntArray());
    }

    @Test
    public void testUndoLEFTOnce() {
        int[][] initBoard = new int[][]{
                {2, 0, 2, 0},
                {0, 16, 0, 0},
                {2, 0, 0, 0},
                {0, 0, 1024, 0}
        };
        Game2048 tfe = new Game2048(initBoard);
        tfe.changeBoard(Direction.LEFT);
        tfe.undo();
        assertArrayEquals(initBoard, tfe.getBoardIntArray());
    }

    @Test
    public void testUndoRIGHTOnce() {
        int[][] initBoard = new int[][]{
                {2, 0, 2, 0},
                {0, 16, 0, 0},
                {2, 0, 0, 0},
                {0, 0, 1024, 0}
        };
        Game2048 tfe = new Game2048(initBoard);
        tfe.changeBoard(Direction.RIGHT);
        tfe.undo();
        assertArrayEquals(initBoard, tfe.getBoardIntArray());
    }

    @Test
    public void testUndoMulti() {
        int[][] initBoard = new int[][]{
                {2, 0, 2, 0},
                {0, 16, 0, 0},
                {2, 0, 0, 0},
                {0, 0, 1024, 0}
        };
        Game2048 tfe = new Game2048(initBoard);
        tfe.changeBoard(Direction.UP);
        tfe.changeBoard(Direction.DOWN);
        tfe.changeBoard(Direction.LEFT);
        tfe.changeBoard(Direction.RIGHT);
        tfe.changeBoard(Direction.UP);
        for (int i = 0; i < 5; i++) {
            tfe.undo();
        }
        assertArrayEquals(initBoard, tfe.getBoardIntArray());
    }

    @Test
    public void testUndoNoPrevAction() {
        int[][] initBoard = new int[][]{
                {2, 0, 2, 0},
                {0, 16, 0, 0},
                {2, 0, 0, 0},
                {0, 0, 1024, 0}
        };
        Game2048 tfe = new Game2048(initBoard);
        tfe.undo();
        assertArrayEquals(initBoard, tfe.getBoardIntArray());
    }


}
