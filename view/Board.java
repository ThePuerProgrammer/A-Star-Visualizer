package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.CanvasListener;
import model.Component;
import model.Node;

public class Board {

    // PRIVATE
    // ---------------------------------------------------------------------------------//
    private JFrame window;
    private ArrayList<Component> components;
    private CanvasListener canvasListener;

    // PUBLIC
    // ---------------------------------------------------------------------------------//
    public static final int BOARD_WIDTH = 800;
    public static final int BOARD_HEIGHT = 800;

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

        Canvas canvas = new Canvas(this);
        canvasListener = new CanvasListener(this);

        window.addKeyListener(canvasListener);
        canvas.addMouseListener(canvasListener);

        cp.add(BorderLayout.CENTER, centerPanel);
        centerPanel.add(BorderLayout.CENTER, canvas);
    }

    public void runAlogrithm() {

    }

    public int h(Node node, Node endNode) {
        var nXY = node.getXY();
        var eXY = endNode.getXY();
        return Math.abs(nXY[0] - eXY[0]) + Math.abs(nXY[1] - eXY[1]);
    }

    public ArrayList<Component> getComponents() {
        return components;
    }
}
