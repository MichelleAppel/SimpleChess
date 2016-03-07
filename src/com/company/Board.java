package com.company;

import java.util.ArrayList;

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




    public void addPiece(Piece piece, int x, int y) {
        board[x][y] = piece;
    }

    public void removePiece(int x, int y) {
        board[x][y] = null;
    }




    public ArrayList checkMovesForPawn(boolean color, int x, int y, Board input_board) {
        ArrayList<Board> list = new ArrayList<>();
        int value = 1;

        if(color) {
            if(board[y][x-1] == null) {
                Board board1 = new Board(input_board);
                Piece piece = new Piece(color, value, y, x-1);
                board1.addPiece(piece, y, x-1);
                board1.removePiece(y,x);
                list.add(board1);
            }
            if(x == 6 && board[y][x-2] == null && board[y][x-1] == null) {
                Board board2 = new Board(input_board);
                Piece piece = board[y][x];
                board2.addPiece(piece, y, x-2);
                board2.removePiece(y,x);
                list.add(board2);
            }
        } else {
            if (board[y][x+1] == null) {
                Board board1 = new Board(input_board);
                Piece piece = new Piece(color, value, y, x+1);
                board1.addPiece(piece, y, x+1);
                board1.removePiece(y, x);
                list.add(board1);
            }
            if (x == 1 && board[y][x+2] == null && board[y][x+1] == null) {
                Board board2 = new Board(input_board);
                Piece piece = board[y][x];
                board2.addPiece(piece, y, x + 2);
                board2.removePiece(y, x);
                list.add(board2);
            }
        }
        return list;
    }


    public ArrayList checkMovesForRook(boolean color, int x, int y, Board input_board) {
        ArrayList<Board> list = new ArrayList<>();
        int value = 5;
        int x1 = x+1;
        int y1 = y+1;

        while(x1 < 8 && board[y][x1] == null) {
            Board board1 = new Board(input_board);
            Piece piece = new Piece(color, value, y, x1);
            board1.addPiece(piece, y, x1);
            board1.removePiece(y,x);
            list.add(board1);
            x1++;
        }
        while(y1 < 8 && board[y1][x] == null) {
            Board board1 = new Board(input_board);
            Piece piece = new Piece(color, value, y1, x);
            board1.addPiece(piece, y1, x);
            board1.removePiece(y,x);
            list.add(board1);
            y1++;
        }
        x1 = x-1;
        y1 = y-1;
        while(x1 > 0 && board[y][x1] == null) {
            Board board1 = new Board(input_board);
            Piece piece = new Piece(color, value, y, x1);
            board1.addPiece(piece, y, x1);
            board1.removePiece(y,x);
            list.add(board1);
            x--;
        }
        while(y1 > 0 && board[y1][x] == null) {
            Board board1 = new Board(input_board);
            Piece piece = new Piece(color, value, y1, x);
            board1.addPiece(piece, y1, x);
            board1.removePiece(y,x);
            list.add(board1);
            y1--;
        }
        return list;
    }


    public ArrayList checkMovesForKing(boolean color, int x, int y, Board input_board) {
        ArrayList<Board> list = new ArrayList<>();
        int value = 9;

        if(x+1 < 8 && board[y][x+1] == null) {
            Board board1 = new Board(input_board);
            Piece piece = new Piece(color, value, y, x+1);
            board1.addPiece(piece, y, x+1);
            board1.removePiece(y,x);
            list.add(board1);
        }
        if(x-1 > 0 && board[y][x-1] == null) {
            Board board1 = new Board(input_board);
            Piece piece = new Piece(color, value, y, x-1);
            board1.addPiece(piece, y, x-1);
            board1.removePiece(y,x);
            list.add(board1);
        }
        if(y+1 < 8 && board[y+1][x] == null) {
            Board board1 = new Board(input_board);
            Piece piece = new Piece(color, value, y+1, x);
            board1.addPiece(piece, y+1, x);
            board1.removePiece(y,x);
            list.add(board1);
        }
        if(y-1 > 0 && board[y-1][x] == null) {
            Board board1 = new Board(input_board);
            Piece piece = new Piece(color, value, y-1, x);
            board1.addPiece(piece, y-1, x);
            board1.removePiece(y,x);
            list.add(board1);
        }
        if(y+1 < 8 && x+1 < 8 && board[y+1][x+1] == null) {
            Board board1 = new Board(input_board);
            Piece piece = new Piece(color, value, y+1, x+1);
            board1.addPiece(piece, y+1, x+1);
            board1.removePiece(y,x);
            list.add(board1);
        }
        if(y-1 > 0 && x+1 < 8 && board[y-1][x+1] == null) {
            Board board1 = new Board(input_board);
            Piece piece = new Piece(color, value, y-1, x+1);
            board1.addPiece(piece, y-1, x+1);
            board1.removePiece(y,x);
            list.add(board1);
        }
        if(y+1 < 8 && x-1 > 0 && board[y+1][x-1] == null) {
            Board board1 = new Board(input_board);
            Piece piece = new Piece(color, value, y+1, x-1);
            board1.addPiece(piece, y+1, x-1);
            board1.removePiece(y,x);
            list.add(board1);
        }
        if(y-1 > 0 && x-1 > 0 && board[y-1][x-1] == null) {
            Board board1 = new Board(input_board);
            Piece piece = new Piece(color, value, y-1, x-1);
            board1.addPiece(piece, y-1, x-1);
            board1.removePiece(y,x);
            list.add(board1);
        }
        return list;
    }


    public ArrayList checkMovesForAll(boolean color, Board input_board) {
        ArrayList list =  new ArrayList<>();
        for(int y = 0; y < 8; y++) {
            for(int x = 0; x < 8; x++) {

                Piece piece = board[y][x];
                if(piece != null) {
                    ArrayList minilist =  new ArrayList<>();

                    int getValue = piece.getValue();
                    boolean getColor = piece.getColor();

                    if (color == getColor) {
                        // pawns
                        if (getValue == 1) {
                            minilist = checkMovesForPawn(color, x, y, input_board);
                            // rooks
                        } else if (getValue == 5) {
                            minilist = checkMovesForRook(color, x, y, input_board);
                            // king
                        } else if (getValue == 9) {
                            minilist = checkMovesForKing(color, x, y, input_board);
                        }
                    }
                    for(int i = 0; i < minilist.size(); i++) {
                        list.add(minilist.get(i));
                    }
                }
            }
        }
        return list;
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
        System.out.println("");
    }
}
