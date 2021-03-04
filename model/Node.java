package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Node extends Component {

    private ArrayList<Node> neighbors;
    private boolean closed;
    private boolean startNode;
    private boolean endNode;

    public Node(int x, int y, int width, int height, Color c) {
        super(x, y, width, height, c);
        closed = startNode = endNode = false;
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

    public void setEndNode(boolean endNode) {
        this.endNode = endNode;
    }

    public ArrayList<Node> getNeighbors() {
        return neighbors;
    }

    public void addNeighbor(Node node) {
        neighbors.add(node);
    }

    public void updateNeighbors() {

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
