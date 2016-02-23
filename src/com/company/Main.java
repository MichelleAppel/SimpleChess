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
            chess_board[1][i] = "1P" + i + "  ";
            chess_board[BOARD_HEIGHT - 2][i] = "2P" + i + "  ";
        }

        chess_board[0][0] = "1R1  ";
        chess_board[0][7] = "1R2  ";
        chess_board[7][0] = "2R1  ";
        chess_board[7][7] = "2R2  ";

        chess_board[0][1] = "1Kn1 ";
        chess_board[0][6] = "1Kn2 ";
        chess_board[7][1] = "2Kn1 ";
        chess_board[7][6] = "2Kn2 ";

        chess_board[0][2] = "1B1  ";
        chess_board[0][5] = "1B2  ";
        chess_board[7][2] = "2B1  ";
        chess_board[7][5] = "2B2  ";

        chess_board[0][2] = "1B1  ";
        chess_board[0][5] = "1B2  ";
        chess_board[7][2] = "2B1  ";
        chess_board[7][5] = "2B2  ";

        chess_board[0][3] = "1Q   ";
        chess_board[0][4] = "1K   ";
        chess_board[7][3] = "2Q   ";
        chess_board[7][4] = "2K   ";
    }

    // prints out array which contains the chess board
    private static void print_chess_board() {
        for(int i = 0; i < BOARD_HEIGHT; i ++) {
            for(int j = 0; j < BOARD_WIDTH; j++) {
                if(j == BOARD_WIDTH - 1) {
                    if (chess_board[i][j] == null) {
                        System.out.println(".    ");
                    } else {
                        System.out.println(chess_board[i][j]);
                    }
                } else {
                    if (chess_board[i][j] == null) {
                        System.out.print(".    ");
                    } else {
                        System.out.print(chess_board[i][j]);
                    }
                }
            }
        }
    }


}
