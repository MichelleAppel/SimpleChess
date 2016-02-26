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



    // adds initial pieces to the board with first number corresponding to the player number and the last to the piece#
    private static void add_initial_pieces() {
        // adds the pawns
        for(int i = 0; i < BOARD_SIZE; i ++) {
            chess_board[1][i] = "1P" + (i + 1);
            chess_board[BOARD_SIZE - 2][i] = "2P" + (i + 1);
        }

        // rooks
        chess_board[0][0] = "1R1";
        chess_board[0][7] = "1R2";
        chess_board[7][0] = "2R1";
        chess_board[7][7] = "2R2";

        // knights
        chess_board[0][1] = "1Kn1";
        chess_board[0][6] = "1Kn2";
        chess_board[7][1] = "2Kn1";
        chess_board[7][6] = "2Kn2";

        // bishops
        chess_board[0][2] = "1B1";
        chess_board[0][5] = "1B2";
        chess_board[7][2] = "2B1";
        chess_board[7][5] = "2B2";

        // queens and kings
        chess_board[0][3] = "1Q";
        chess_board[0][4] = "1K";
        chess_board[7][3] = "2Q";
        chess_board[7][4] = "2K";
    }



    // prints out array which contains the chess board
    private static void print_chess_board() {
        for(int i = 0; i < BOARD_SIZE; i ++) {
            for(int j = 0; j < BOARD_SIZE; j++) {
                if(j == BOARD_SIZE - 1) {
                    if (chess_board[i][j] == null) {
                        System.out.println(".    ");
                    } else {
                        System.out.println(chess_board[i][j] + "  ");
                    }
                } else {
                    if (chess_board[i][j] == null) {
                        System.out.print(".    ");
                    } else {
                        System.out.print(chess_board[i][j] + "  ");
                    }
                }
            }
        }
    }


    // animate the moves
    private static void animate() {
        wipeScreen();
        board.printBoard();
    }

    // wipe the screen
    private static void wipeScreen() {
        for(int i = 0; i < 10; i++) {
            System.out.println();
        }
    }
}
