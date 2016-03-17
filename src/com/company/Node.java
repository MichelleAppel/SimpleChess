package com.company;

/**
 * Created by Michelle on 17-3-2016.
 */
public class Node {
    private static Board rootBoard;
    private static Board leafBoard;

    public Node(Board rootBoard, Board leafBoard) {
        this.rootBoard = rootBoard;
        this.leafBoard = leafBoard;
    }

    public static Board getRootBoard() {
        return rootBoard;
    }

    public static Board getLeafBoard() {
        return leafBoard;
    }


}
