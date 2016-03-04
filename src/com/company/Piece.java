package com.company;

public abstract class Piece {

    // true = white
    // false = black
    protected boolean color;
    protected int value;
    protected int x;
    protected int y;

    // constructor
    public Piece(boolean color, int value, int x, int y) {
        this.color = color;
        this.value = value;
        this.x = x;
        this.y = y;
    }

    public abstract Move[] getMoves();

    // returns color (true = white and false = black)
    public boolean getColor() {
        return color;
    }

    // returns value
    public int getValue() {
        return value;
    }

    // adds x position
    public void addX(int x) {
        this.x = x;
    }

    // adds y position
    public void addY(int y) {
        this.y = y;
    }

    // returns x position
    public int getX() {
        return x;
    }

    // returns y position
    public int getY() {
        return y;
    }

    public String toString() {
        return "" + this.value;
    }
}
