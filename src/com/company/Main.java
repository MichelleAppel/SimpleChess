package com.company;

import java.util.ArrayList;

public class Main {

    // chess board (always 8x8 fields)
    private static int BOARD_SIZE = 8;

    // simple chess has 22 pieces: 8x2 pawns, 2x2 rooks, 1x2 kings
    private static final int AMOUNT_OF_PIECES = 22;

    // declare new object from class Board
    private static Board board = new Board(BOARD_SIZE, AMOUNT_OF_PIECES);

    public static void main(String[] args) {
        board.addPieces();

        //delay(2000);
        wipeScreen();
        board.printBoard();

        board.generateAllChildren();

        //board.moveRight(0, 0);

        //delay(2000);
        //wipeScreen();
        //board.printBoard();

        //Piece test = board.checkPiece(0, 0);
        //System.out.println(test);

        //board.checkMovesForRook(0,0);
        //board.returnMoves();
    }

    // wipe the screen
    private static void wipeScreen() {
        for(int i = 0; i < 10; i++) {
            System.out.println();
        }
    }

    // delay (useful to see if move methods actually work)
    private static void delay(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}
