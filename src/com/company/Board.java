package com.company;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Board {

    // om te testen (hoe we moves het best op kunnen slaan)
    private final List<Point2D> possibleMoves = new ArrayList<Point2D>();

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

    // moves a piece to the right (can currently replace other pieces)
    public void moveRight(int x, int y) {
        board[x+1][y] = board[x][y];
        board[x][y] = null;
    }

    // returns Piece value
    public Piece checkPiece(int x, int y) {
        return board[x][y];
    }

    // returns the moves in as Point2D.Double (GWN EEN TEST, NOG NIET PERFECT)
    public void returnMoves() {
        for (int i = 0; i < possibleMoves.size(); i++) {
            System.out.println(possibleMoves.get(i));
        }
    }

    // WERKT NOG NIET
    public void checkMovesForPawn(int x, int y) {
        int initX = x;
        int initY = y;
        boolean fieldIsEmpty = true;

        // pawns still on the pawn start line
        if(y == 1) {
            // move 1: 1 y further
            if(board[x][y+1] == null) {
                // do something
            }

            // move 2: 2 y further
            if(board[x][y+2] == null) {
                // do something
            }

            // move 3: strike another pawn (that's diagonal from current pawn & opposite color)

        }

        // pawns still on the pawn start line
        if(y == 6) {
            // do something
        }
    }


    // checks all possible moves for a rook (input parameters are it's x and y coordinate)
    public void checkMovesForRook(int x, int y) {
        int initX = x;
        int initY = y;
        boolean fieldIsEmpty = true;

        // move(s) left
        while(x > 0 && fieldIsEmpty) {
            if(board[x-1][y] == null) {
                System.out.println(x + "/" + y + "is a valid move.");
                Point2D p = new Point2D.Double(x,y);
                possibleMoves.add(p);
            } else {
                System.out.println(x + "/" + y + "is not valid move.");
                fieldIsEmpty = false;
            }
            x -= 1;
        }

        // move(s) right
        x = initX;
        y = initY;
        fieldIsEmpty = true;
        while(x < 8 && fieldIsEmpty) {
            if(board[x+1][y] == null) {
                System.out.println(x + "/" + y + "is a valid move.");
                Point2D p = new Point2D.Double(x,y);
                possibleMoves.add(p);
            } else {
                System.out.println(x + "/" + y + "is not valid move.");
                fieldIsEmpty = false;
            }
            x += 1;
        }

        // move(s) down
        x = initX;
        y = initY;
        fieldIsEmpty = true;
        while(y < 8 && fieldIsEmpty) {
            if(board[x][y+1] == null) {
                System.out.println(x + "/" + y + "is a valid move.");
                Point2D p = new Point2D.Double(x,y);
                possibleMoves.add(p);
            } else {
                System.out.println(x + "/" + y + "is not valid move.");
                fieldIsEmpty = false;
            }
            y -= 1;
        }

        // move(s) up
        x = initX;
        y = initY;
        fieldIsEmpty = true;
        while(y > 0 && fieldIsEmpty) {
            if(board[x][y-1] == null) {
                System.out.println(x + "/" + y + "is a valid move.");
                Point2D p = new Point2D.Double(x,y);
                possibleMoves.add(p);
            } else {
                System.out.println(x + "/" + y + "is not valid move.");
                fieldIsEmpty = false;
            }
            y += 1;
        }

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
