package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import view.Board;

public class Node extends Component {

    private ArrayList<Node> neighbors;
    private boolean closed;
    private boolean startNode;
    private boolean endNode;
    private boolean wallNode;

    public Node(int x, int y, int width, int height, Color c) {
        super(x, y, width, height, c);
        closed = startNode = endNode = wallNode = false;
        neighbors = new ArrayList<>();
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public boolean isStartNode() {
        return startNode;
    }

    public void setStartNode(boolean startNode) {
        this.startNode = startNode;
    }

    public boolean isEndNode() {
        return endNode;
    }

    public boolean isWallNode() {
        return wallNode;
    }

    public void setWallNode(boolean wallNode) {
        this.wallNode = wallNode;
    }

    public void setEndNode(boolean endNode) {
        this.endNode = endNode;
    }

    public ArrayList<Node> getNeighbors() {
        return neighbors;
    }

    public void addNeighbor(Node node) {
        neighbors.add(node);
    }

    @Override
    public void render(Graphics2D g2) {
        g2.setColor(c);
        g2.fillRect(x, y, width, height);
    }

    @Override
    public void animate() {
    }

    public ArrayList<Component> updateNeighbors(Node[][] grid) {
        ArrayList<Component> components = new ArrayList<>();
        neighbors.removeAll(neighbors);
        int i = 0, j = 0;
        int w = Board.BOARD_WIDTH / Board.GRID_DIV;
        for (int k = 0; k < Board.BOARD_WIDTH; k += w, j++) {
            if (k == x)
                break;
        }
        for (int k = 0; k < Board.BOARD_HEIGHT; k += w, i++) {
            if (k == y)
                break;
        }

        if (i > 0 && (grid[i - 1][j] == null || !grid[i - 1][j].isWallNode())) {
            if (grid[i - 1][j] == null)
                grid[i - 1][j] = new Node(x - w, y, width, height, Color.ORANGE);
            neighbors.add(grid[i - 1][j]);
            components.add(grid[i - 1][j]);
        }
        if (j > 0 && (grid[i][j - 1] == null || !grid[i][j - 1].isWallNode())) {
            if (grid[i][j - 1] == null)
                grid[i][j - 1] = new Node(x, y - w, width, height, Color.ORANGE);
            neighbors.add(grid[i][j - 1]);
            components.add(grid[i][j - 1]);
        }
        if (j < Board.GRID_DIV - 1 && (grid[i][j + 1] == null || !grid[i][j + 1].isWallNode())) {
            if (grid[i][j + 1] == null)
                grid[i][j + 1] = new Node(x, y + w, width, height, Color.ORANGE);
            neighbors.add(grid[i][j + 1]);
            components.add(grid[i][j + 1]);
        }
        if (i < Board.GRID_DIV - 1 && (grid[i + 1][j] == null || !grid[i + 1][j].isWallNode())) {
            if (grid[i + 1][j] == null)
                grid[i + 1][j] = new Node(x + w, y, width, height, Color.ORANGE);
            neighbors.add(grid[i + 1][j]);
            components.add(grid[i + 1][j]);
        }
        return components;
    }
}
