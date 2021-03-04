package view;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Canvas extends JPanel {
    private static final long serialVersionUID = 1L;
    private Board board;

    public Canvas(Board board) {
        this.board = board;
        setPreferredSize(new Dimension(Board.BOARD_WIDTH, Board.BOARD_HEIGHT));
        setBackground(Color.WHITE);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        for (var c : board.getComponents()) {
            c.render(g2);
        }

        // DRAW GRID LINES
        g2.setColor(Color.BLACK);
        for (int i = 0; i < Board.BOARD_WIDTH; i++) {
            int x = (Board.BOARD_WIDTH / Board.GRID_DIV) * i;
            g2.drawLine(x, 0, x, Board.BOARD_HEIGHT);
            g2.drawLine(0, x, Board.BOARD_WIDTH, x);
        }
    }

}
