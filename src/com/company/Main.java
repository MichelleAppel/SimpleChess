package com.company;

public class Main {

    private static int BOARD_SIZE = 8;
    private static final int AMOUNT_OF_PIECES = 32;

    private static String[][] chess_board = new String[BOARD_SIZE][BOARD_SIZE];

    private static Board board = new Board(BOARD_SIZE, AMOUNT_OF_PIECES);


    public static void main(String[] args) {
        //add_initial_pieces();
        //print_chess_board();

        board.addPieces();
        board.printBoard();

    }

    
    // wipe the screen
    private static void wipeScreen() {
        for(int i = 0; i < 10; i++) {
            System.out.println();
        }
    }
}
