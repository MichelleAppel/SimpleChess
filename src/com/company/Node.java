package com.company;

public class Node {
    private Node parent;
    private Board leafBoard;
    private Integer score;

    public Node(Node parent, Board leafBoard, Integer score) {
        this.parent = parent;
        this.leafBoard = leafBoard;
        this.score = score;
    }

    public Node getParent() {
        return parent;
    }

    public Board getLeafBoard() {
        return leafBoard;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

}
