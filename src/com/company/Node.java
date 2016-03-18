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

    public Node getParent() {
        return parent;
    }

    public Board getLeafBoard() {
        return leafBoard;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean getColor() { return color;}

    public int getScore() {
        if(score != null) {
            return score;
        } else {
            return 0;
        }
    }

}
