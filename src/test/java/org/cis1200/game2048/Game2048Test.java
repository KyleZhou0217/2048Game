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


}
