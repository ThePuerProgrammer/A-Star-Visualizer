import javax.swing.JFrame;

import view.Board;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setTitle("A* Visualizer");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        var board = new Board(window);
        board.init();
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}