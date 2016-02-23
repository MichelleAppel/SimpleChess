package com.company;

public class Main {

    private static int BOARD_WIDTH = 8;
    private static int BOARD_HEIGHT = 8;

    private static String[][] chess_board = new String[BOARD_HEIGHT][BOARD_WIDTH];


    public static void main(String[] args) {
        print_chess_board(chess_board);
    }

    private static void print_chess_board(String[][] chess_board) {
        for(int i = 0; i < BOARD_HEIGHT; i ++) {
            for(int j = 0; j < BOARD_WIDTH; j++) {
                if(j == BOARD_WIDTH - 1) {
                    System.out.println(chess_board[i][j]);
                } else {
                    System.out.print(chess_board[i][j]);
                }

            }
        }
    }
}
