package org.cis1200.game2048;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Map;

public class Square {
    private static final int HEIGHT = 120;
    private static final int WIDTH = 120;
    private static final int MOVE_SPD = 25;
    private int x;
    private int y;
    private int number;
    private static BufferedImage img;

    private static final Map<Integer, Tuple> TILE_PEN_COLORS = Map.ofEntries(
            Map.entry(2, new Tuple(0x000000, 0xeee4da)),
            Map.entry(4, new Tuple(0x000000, 0xede0c8)),
            Map.entry(8, new Tuple(0xffffff, 0xf2b179)),
            Map.entry(16, new Tuple(0xffffff, 0xf59563)),
            Map.entry(32, new Tuple(0xffffff, 0xf67c5f)),
            Map.entry(64, new Tuple(0xffffff, 0xf65e3b)),
            Map.entry(128, new Tuple(0xffffff, 0xedcf72)),
            Map.entry(256, new Tuple(0xffffff, 0xedcc61)),
            Map.entry(512, new Tuple(0xffffff, 0xedc850)),
            Map.entry(1024, new Tuple(0xffffff, 0xedc53f)),
            Map.entry(2048, new Tuple(0xffffff, 0xedc22e))
    );


    public Square(int x, int y, int number) {
        this.x = x;
        this.y = y;
        this.number = number;
        img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    }

    public static int getHeight() {
        return HEIGHT;
    }

    public static int getWidth() {
        return WIDTH;
    }

    public static int getMoveSpeed() {
        return MOVE_SPD;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int col) {
        x = Square.getWidth() * col + GameBoard.SPACE * (col + 1);
    }

    public void setY(int row) {
        y = Square.getHeight() * row + GameBoard.SPACE * (row + 1);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    public void draw(Graphics g) {
        g.setColor(new Color((int) TILE_PEN_COLORS.get(number).getB()));
        g.fillRect(x, y, WIDTH, HEIGHT);
        g.setColor(new Color((int) TILE_PEN_COLORS.get(number).getA()));
        g.setFont(new Font("Clear Sans", Font.BOLD, 40));
        Rectangle2D border = g.getFontMetrics().getStringBounds(String.valueOf(number), g);
        g.drawString("" + number, x + (WIDTH - (int) border.getWidth())/ 2,
                y + HEIGHT / 2 + 12);

    }
}
