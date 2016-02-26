package com.company;

public class Board {
    private int size;
    private Piece[][] board;
    private Piece[] pieces;

    private int piece_number = 1;
    private int amount_of_pieces;

    // constructor
    public Board(int size, int amount_of_pieces) {
        this.size = size;
        this.board = new Piece[size][size];
        this.amount_of_pieces = amount_of_pieces;
        this.pieces = new Piece[amount_of_pieces + 1];
    }

    // copy constructor
    public Board(Board previous) {
        this.size = previous.size;
        this.amount_of_pieces = previous.amount_of_pieces;

        this.board = new Piece[size][size];
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

    
    public void checkMovesForPawn() {
        // insert code here
    }

    public void checkMovesForRook() {
        // insert code here
    }

    public void checkMovesForKing() {
        // insert code here
    }

    // adds all pieces (objects) to board the board (matrix)
    public void addPieces() {
        addPawns();
        addRooks();
        addKings();
    }

    // adds black and white pawns to board
    public void addPawns() {
        for(int i = 0; i < 8; i++) {
            int value = 1;
            board[i][6] = new Piece(true, value, i, 6);     // white pawn
            board[i][1] = new Piece(false, value, i, 1);    // black pawn
            piece_number += 2;
        }
    }

    // adds black and white rooks to board
    public void addRooks() {
        int value = 5;
        board[0][7] = new Piece(true, value, 0, 7);         // white rook 1
        board[7][7] = new Piece(true, value, 7, 7);         // white rook 2
        board[0][0] = new Piece(false, value, 0, 0);        // black rook 1
        board[7][0] = new Piece(false, value, 7, 0);        // black rook 2
        piece_number += 4;
    }

    // adds black and white kings to board
    public void addKings() {
        int value = 9;
        board[4][7] = new Piece(true, value, 4, 7);         // white king
        board[4][0] = new Piece(false, value, 4, 0);        // black king
        piece_number += 2;
    }

    // prints the board (the matrix, not the object itself)
    public void printBoard() {
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                System.out.print((board[j][i] == null? ".": board[j][i]) + " ");
            }
            System.out.println();
        }
    }
}
