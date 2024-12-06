package org.cis1200.game2048;

import java.io.*;

public class GameSaverLoader {

    public void saveGame(String fileName, Square[][] board) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] != null) {
                        writer.write(board[i][j].getNumber() + " ");
                    } else {
                        writer.write(0 + " ");
                    }
                }
                if (board[i][3] != null) {
                    writer.write(board[i][3].getNumber() + "");
                } else {
                    writer.write(0 + "");
                }
                writer.newLine();
            }
            writer.flush();
        } catch (IOException e) {
            throw new IllegalArgumentException("File does not exist");
        }
    }

    public Square[][] loadGame(String fileName) {
        Square[][] board = new Square[4][4];
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            for (int i = 0; i < 4; i++) {
                String line = reader.readLine();
                String[] values = line.split(" ");
                for (int j = 0; j < 4; j++) {
                    if (Integer.parseInt(values[j]) == 0) {
                        board[i][j] = null;
                    } else {
                        board[i][j] = new Square(0, 0, Integer.parseInt(values[j]));
                        board[i][j].setX(j);
                        board[i][j].setY(i);
                    }
                }
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("File does not exist");
        }
        return board;
    }

}
