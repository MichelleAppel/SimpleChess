package com.company;

public class Node {
    private Node parent;
    private Board leafBoard;
    private Integer score;
    private boolean color;

    public Node(Node parent, Board leafBoard, Integer score, boolean color) {
        this.parent = parent;
        this.leafBoard = leafBoard;
        this.score = score;
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
