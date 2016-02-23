package com.company;

public class Main {

    private static int BOARD_WIDTH = 8;
    private static int BOARD_HEIGHT = 8;

    private static String[][] chess_board = new String[BOARD_HEIGHT][BOARD_WIDTH];


    public static void main(String[] args) {
        add_initial_pieces();
        print_chess_board();
    }

    private static void add_initial_pieces() {
        for(int i = 0; i < BOARD_WIDTH; i ++) {
            chess_board[1][i] = "1P" + i + " ";
            chess_board[BOARD_HEIGHT - 2][i] = "2P" + i + " ";
        }
    }

    // prints out array which contains the chess board
    private static void print_chess_board() {
        for(int i = 0; i < BOARD_HEIGHT; i ++) {
            for(int j = 0; j < BOARD_WIDTH; j++) {
                if(j == BOARD_WIDTH - 1) {
                    if (chess_board[i][j] == null) {
                        System.out.println(".   ");
                    } else {
                        System.out.println(chess_board[i][j]);
                    }
                } else {
                    if (chess_board[i][j] == null) {
                        System.out.print(".   ");
                    } else {
                        System.out.print(chess_board[i][j]);
                    }
                }
            }
        }
    }


}
