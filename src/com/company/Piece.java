package com.company;

class Piece {

    // true = white
    // false = black
    private final boolean color;
    private final int value;
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

    public String toString() {
        return "" + this.value;
    }
}
