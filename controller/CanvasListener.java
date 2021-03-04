package controller;

import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.event.MouseInputListener;

import view.Board;

public class CanvasListener implements MouseInputListener, KeyListener {

    private Board board;

    public CanvasListener(Board board) {
        this.board = board;
    }

    // MOUSE EVENTS
    // =================================================================================//
    // =================================================================================//
    @Override
    public void mousePressed(MouseEvent e) {

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
            case KeyEvent.VK_ENTER:
                board.runAlogrithm();
                break;
            default:
                return;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

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
