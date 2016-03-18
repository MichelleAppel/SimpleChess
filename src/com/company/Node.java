package com.company;

public class Node {
    private final Node parent;
    private final Board leafBoard;
    private Integer score;
    private final boolean color;

    public Node(Node parent, Board leafBoard, boolean color) {
        this.parent = parent;
        this.leafBoard = leafBoard;
        this.score = null;
        this.color = color;
    }

    // returns parentnode
    public Node getParent() {
        return parent;
    }

    // returns leafboard
    public Board getLeafBoard() {
        return leafBoard;
    }

    // sets score on a given node
    public void setScore(int score) {
        this.score = score;
    }

    // returns color
    public boolean getColor() { return color;}

    // returns score
    public Integer getScore() { return score; }

}
