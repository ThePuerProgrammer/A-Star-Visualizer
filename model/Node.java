package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Node extends Component {

    private ArrayList<Node> neighbors;
    private boolean closed;

    public Node(int x, int y, int width, int height, Color c) {
        this(x, y, width, height, c, false);
    }

    public Node(int x, int y, int width, int height, Color c, boolean closed) {
        super(x, y, width, height, c);
        this.closed = closed;
        neighbors = new ArrayList<>();
    }

    public boolean isClosed() {
        return closed;
    }

    public ArrayList<Node> getNeighbors() {
        return neighbors;
    }

    @Override
    public void render(Graphics2D g2) {
        g2.setColor(c);
        g2.fillRect(x, y, width, height);
    }

    @Override
    public void animate() {
    }
}
