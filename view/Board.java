package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.PriorityQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.CanvasListener;
import model.Component;
import model.Node;
import model.QueuePod;

public class Board implements Runnable {

    // PRIVATE
    // ---------------------------------------------------------------------------------//
    private JFrame window;
    private CanvasListener canvasListener;
    private Node[][] grid;
    private Thread thread;
    private Canvas canvas;
    private final int FPS = 60;
    private final int TIME = 1000 / FPS;
    private PriorityQueue<QueuePod> queue;

    // PUBLIC
    // ---------------------------------------------------------------------------------//
    public static final int BOARD_WIDTH = 800;
    public static final int BOARD_HEIGHT = 800;
    public static final int GRID_DIV = 50;

    static public boolean algorithmRunning;
    static public ArrayList<Component> components;

    public Board(JFrame window) {
        this.window = window;
        components = new ArrayList<>();
        window.setFocusable(true);
        window.requestFocus();
    }

    public void init() {
        Container cp = window.getContentPane();
        JPanel centerPanel = new JPanel();
        centerPanel.setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));

        canvas = new Canvas(this);
        canvasListener = new CanvasListener(this);

        window.addKeyListener(canvasListener);
        canvas.addMouseListener(canvasListener);

        cp.add(BorderLayout.CENTER, centerPanel);
        centerPanel.add(BorderLayout.CENTER, canvas);

        grid = new Node[GRID_DIV][GRID_DIV];
        start();
    }

    private void start() {
        algorithmRunning = false;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while (true) {
            // To aim for consistent FPS
            long nano = System.nanoTime();
            tick();
            canvas.repaint();
            long updated = System.nanoTime() - nano;
            long buffer = TIME - updated / 1_000_000;
            if (buffer <= 0) {
                buffer = 5;
            }
            try {
                Thread.sleep(buffer);
            } catch (Exception e) {
                System.out.println("Caught Exception - Thread.sleep(buffer)");
                System.exit(1);
            }
        }
    }

    public void tick() {
        ArrayList<Component> addNeighbors = new ArrayList<>();
        for (var e : components) {
            if (e instanceof Node) {
                Node n = (Node) e;
                if (!n.isWallNode())
                    addNeighbors = n.updateNeighbors(grid);
            }
        }
        components.addAll(addNeighbors);
        if (algorithmRunning)
            algorithm();
    }

    private void algorithm() {

        Node startNode = new Node(0, 0, 0, 0, Color.WHITE);
        Node endNode = new Node(0, 0, 0, 0, Color.WHITE);
        for (var e : components) {
            if (e instanceof Node) {
                Node n = (Node) e;
                if (n.isStartNode())
                    startNode = n;
                else if (n.isEndNode())
                    endNode = n;
            }
        }
        int insertionOrderValue = 0;
        queue = new PriorityQueue<>();
        queue.add(new QueuePod(startNode, 0, insertionOrderValue));
        Hashtable<Node, Node> cameFrom = new Hashtable<>();
        int[][] gScore = new int[GRID_DIV][GRID_DIV];
        for (int i = 0; i < GRID_DIV; i++) {
            for (int j = 0; j < GRID_DIV; j++) {
                gScore[i][j] = Integer.MAX_VALUE - 1;
            }
        }
        var startNodeXY = startNode.getXY();
        var index = getGridIndex(startNodeXY[0], startNodeXY[1]);
        gScore[index[0]][index[1]] = 0;
        int[][] fScore = new int[GRID_DIV][GRID_DIV];
        for (int i = 0; i < GRID_DIV; i++) {
            for (int j = 0; j < GRID_DIV; j++) {
                fScore[i][j] = Integer.MAX_VALUE - 1;
            }
        }
        fScore[index[0]][index[1]] = h(startNode, endNode);

        ArrayList<Node> queueSet = new ArrayList<>();
        queueSet.add(startNode);

        while (!queue.isEmpty() && algorithmRunning) {
            long nano = System.nanoTime();

            var current = queue.poll().getNode();
            if (current.isEndNode()) {
                // Make path
            }

            for (var neighbor : current.getNeighbors()) {
                var currentXY = current.getXY();
                var currentIndex = getGridIndex(currentXY[0], currentXY[1]);
                var tempG = gScore[currentIndex[0]][currentIndex[1]] + 1;

                var neighborXY = neighbor.getXY();
                var neighborIndex = getGridIndex(neighborXY[0], neighborXY[1]);
                var neighborG = gScore[neighborIndex[0]][neighborIndex[1]];

                if (tempG < neighborG) {
                    cameFrom.put(neighbor, current);
                    int f;
                    gScore[neighborIndex[0]][neighborIndex[1]] = tempG;
                    fScore[neighborIndex[0]][neighborIndex[1]] = f = tempG + h(neighbor, endNode);

                    if (!queueSet.contains(neighbor)) {
                        insertionOrderValue++;
                        queue.add(new QueuePod(neighbor, f, insertionOrderValue));
                        queueSet.add(neighbor);
                        neighbor.setClosed(false);
                    }
                }
            }

            canvas.repaint();
            System.out.println("yep");
            long updated = System.nanoTime() - nano;
            long buffer = TIME - updated / 1_000_000;
            if (buffer <= 0) {
                buffer = 5;
            }
            try {
                Thread.sleep(buffer);
            } catch (Exception e) {
                System.out.println("Caught Exception - Thread.sleep(buffer)");
                System.exit(1);
            }

            if (current != startNode) {
                current.setClosed(true);
            }
        }
    }

    public int[] getGridIndex(int x, int y) {
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
        int[] result = { i, j };
        return result;
    }

    public int h(Node node, Node endNode) {
        int[] nXY = node.getXY();
        int[] eXY = endNode.getXY();
        return Math.abs(nXY[0] - eXY[0]) + Math.abs(nXY[1] - eXY[1]);
    }

    public ArrayList<Component> getComponents() {
        return components;
    }

    public void addNodeToGrid(Node node, int x, int y) {
        grid[y][x] = node;
    }

    public Canvas getCanvas() {
        return canvas;
    }

}
