package model;

import java.awt.Color;
import java.awt.Graphics2D;

public abstract class Component {

    protected int x;
    protected int y;
    protected int z;
    protected Color c;
    protected int width;
    protected int height;

    public Component() {
        this(0, 0);
    }

    public Component(int x, int y) {
        this(x, y, 0, 0, Color.BLACK);
    }

    public Component(int x, int y, int width, int height, Color c) {
        this(x, y, 0, width, height, c);
    }

    public Component(int x, int y, int z, int width, int height, Color c) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.width = width;
        this.height = height;
        this.c = c;
    }

    public int[] getXY() {
        int[] xy = { x, y };
        return xy;
    }

    public abstract void render(Graphics2D g2);

    public abstract void animate();
}
