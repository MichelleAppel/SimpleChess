package com.company;

public class Piece {

    // true = white
    // false = black
    private boolean color;
    private int value;
    private int x;
    private int y;

    // constructor
    public Piece(boolean color, int value, int x, int y) {
        this.color = color;
        this.value = value;
        this.x = x;
        this.y = y;
    }

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
