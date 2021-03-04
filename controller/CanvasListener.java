package controller;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.event.MouseInputListener;

import model.Component;
import model.Node;
import view.Board;

public class CanvasListener implements MouseInputListener, KeyListener {

    private Board board;
    private boolean startNode;
    private boolean endNode;
    private boolean wall;
    static public boolean startAdded;
    static public boolean endAdded;

    public CanvasListener(Board board) {
        this.board = board;
        startNode = endNode = wall = startAdded = endAdded = false;
    }

    // MOUSE EVENTS
    // =================================================================================//
    // =================================================================================//
    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        int xj = 0, yj = 0;
        int w = Board.BOARD_WIDTH / Board.GRID_DIV;

        // ADJUST OUR X TO MATCH THE GRID OF THE BOARD
        for (int i = 0; i < Board.BOARD_WIDTH; i += w, xj++) {
            if (x >= i && x < i + w) {
                x = i;
                break;
            }
        }
        // ADJUST OUR Y TO MATCH THE GRID OF THE BOARD
        for (int i = 0; i < Board.BOARD_HEIGHT; i += w, yj++) {
            if (y >= i && y < i + w) {
                y = i;
                break;
            }
        }

        // CHECK IF THE CLICK ALREADY CONTAINS A START OR END NODE
        startAdded = endAdded = false;
        for (var c : board.getComponents()) {
            if (c instanceof Node) {
                Node n = (Node) c;
                if (n.isStartNode()) {
                    startAdded = true;
                } else if (n.isEndNode()) {
                    endAdded = true;
                }
            }
        }

        if (wall) {
            // SUMBIT OUR SELECTION FOR RENDERING AND GRID
            Node node = new Node(x, y, w, w, Color.BLACK);
            node.setWallNode(true);
            board.addNodeToGrid(node, xj, yj);
            board.getComponents().add(node);

        } else if (startNode) {

            ArrayList<Component> discardedComponents = new ArrayList<>();
            if (startAdded) {

                for (var c : board.getComponents()) {
                    if (c instanceof Node) {
                        Node n = (Node) c;
                        if (n.isStartNode()) {
                            discardedComponents.add(c);
                            int i = 0, j = 0;
                            for (int k = 0; k < Board.BOARD_WIDTH; k += w, i++) {
                                if (k == n.getXY()[0])
                                    break;
                            }
                            for (int k = 0; k < Board.BOARD_HEIGHT; k += w, j++) {
                                if (k == n.getXY()[1])
                                    break;
                            }
                            board.addNodeToGrid(null, i, j);
                        }
                    }
                }
            }

            board.getComponents().removeAll(discardedComponents);

            // SUMBIT OUR SELECTION FOR RENDERING AND GRID
            Node node = new Node(x, y, w, w, Color.GREEN);
            node.setStartNode(true);
            board.addNodeToGrid(node, xj, yj);
            board.getComponents().add(node);

        } else if (endNode) {

            ArrayList<Component> discardedComponents = new ArrayList<>();
            if (endAdded) {
                for (var c : board.getComponents()) {
                    if (c instanceof Node) {
                        Node n = (Node) c;
                        if (n.isEndNode()) {
                            discardedComponents.add(c);
                            int i = 0, j = 0;
                            for (int k = 0; k < Board.BOARD_WIDTH; k += w, i++) {
                                if (k == n.getXY()[0])
                                    break;
                            }
                            for (int k = 0; k < Board.BOARD_HEIGHT; k += w, j++) {
                                if (k == n.getXY()[1])
                                    break;
                            }
                            board.addNodeToGrid(null, i, j);
                        }
                    }
                }
            }

            board.getComponents().removeAll(discardedComponents);

            // SUMBIT OUR SELECTION FOR RENDERING AND GRID
            Node node = new Node(x, y, w, w, Color.RED);
            node.setEndNode(true);
            board.addNodeToGrid(node, xj, yj);
            board.getComponents().add(node);

        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    // KEY EVENTS
    // =================================================================================//
    // =================================================================================//
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                wall = true;
                break;
            case KeyEvent.VK_S:
                startNode = true;
                break;
            case KeyEvent.VK_E:
                endNode = true;
                break;
            case KeyEvent.VK_ENTER:
                Board.algorithmRunning = true;
                break;
            default:
                return;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                wall = false;
                break;
            case KeyEvent.VK_S:
                startNode = false;
                break;
            case KeyEvent.VK_E:
                endNode = false;
                break;
            default:
                return;
        }
    }

    // UNIMPLEMENTED METHODS
    // =================================================================================//
    // =================================================================================//
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

}
