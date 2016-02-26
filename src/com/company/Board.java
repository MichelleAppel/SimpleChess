package com.company;

public class Board {
    private int size;
    private int[][] board;
    private Piece[] pieces;

    private int piece_number = 1;
    private int amount_of_pieces;

    // constructor
    public Board(int size, int amount_of_pieces) {
        this.size = size;
        this.board = new int[size][size];
        this.amount_of_pieces = amount_of_pieces;
        this.pieces = new Piece[amount_of_pieces + 1];
    }

    public Board(Board previous) {
        this.size = previous.size;
        this.amount_of_pieces = previous.amount_of_pieces;

        this.board = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[j][i] = previous.board[j][i];
            }
        }

        this.pieces = new Piece[amount_of_pieces + 1];
        for (int i = 1; i <= amount_of_pieces; i++) {
            pieces[i] = previous.pieces[i];
        }
    }

    // adds pieces to board
    public void addPieces() {
        addPawns();
        addRooks();
        addKings();
    }

    public void addPawns() {
        for(int i = 0; i < 8; i++) {
            int value = 1;
            board[i][6] = value; // white pawn
            pieces[piece_number] = new Piece(true, value, i, 6);

            board[i][1] = value; // black pawn
            pieces[piece_number] = new Piece(false, value, i, 1);

            piece_number++;
        }
    }

    public void addRooks() {
        int value = 5;
        board[0][7] = value; // white rook 1
        pieces[piece_number] = new Piece(true, value, 0, 7);

        board[7][7] = value; // white rook 2
        pieces[piece_number] = new Piece(true, value, 7, 7);

        board[0][0] = value; // black rook 1
        pieces[piece_number] = new Piece(false, value, 0, 0);

        board[7][0] = value; // black rook 2
        pieces[piece_number] = new Piece(false, value, 7, 0);

        piece_number++;
    }

    public void addKings() {
        int value = 9;
        board[4][7] = value; // white king
        pieces[piece_number] = new Piece(true, value, 4, 7);

        board[4][0] = value; // black king
        pieces[piece_number] = new Piece(false, value, 4, 0);

        piece_number++;
    }

    public void printBoard() {
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                System.out.print(board[j][i] + " ");
            }
            System.out.println();
        }
    }

}
