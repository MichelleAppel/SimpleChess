package com.company;

import java.util.ArrayList;

        /* Table of Contents
         * 1. Declaring private instance variables
         * 2. Making constructor(s)
         * 3. Methods for Adding Pieces
         * 4. Methods for Moving Pieces
         * 5. Methods for Counting Score
         * 6. Methods for determining Winner/Loser
         * 7. Methods for visualisation
         */

public class Board {
    /* *****************************************
     * 1. Declaring private instance variables *
     ***************************************** */

    private int amount_of_pieces;
    private int size;
    private Piece[][] board;

    private static int pawnValue = 1;
    private static int rookValue = 5;
    private static int kingValue = 9;


    /* **************************
     * 2. Making constructor(s) *
     ************************** */

    // constructor
    public Board(int size, int amount_of_pieces) {
        this.size = size;
        this.board = new Piece[size][size];
        this.amount_of_pieces = amount_of_pieces;
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
    }


    /* ******************************
     * 3. Methods for Adding Pieces *
     ****************************** */

    // adds all pieces (objects) to board the board (matrix)
    public void addPieces() {
        addPawns();
        addRooks();
        addKings();
    }

    // adds black and white pawns to board
    public void addPawns() {
        for (int i = 0; i < 8; i++) {
            board[i][6] = new Piece(true, pawnValue, i, 6);     // white pawn x8
            board[i][1] = new Piece(false, pawnValue, i, 1);    // black pawn x8
        }
    }

    // adds black and white rooks to board
    public void addRooks() {
        board[0][7] = new Piece(true, rookValue, 0, 7);         // white rook 1
        board[7][7] = new Piece(true, rookValue, 7, 7);         // white rook 2
        board[0][0] = new Piece(false, rookValue, 0, 0);        // black rook 1
        board[7][0] = new Piece(false, rookValue, 7, 0);        // black rook 2
    }

    // adds black and white kings to board
    public void addKings() {
        board[4][7] = new Piece(true, kingValue, 4, 7);         // white king
        board[4][0] = new Piece(false, kingValue, 4, 0);        // black king
    }


    /* ******************************
     * 4. Methods for Moving Pieces *
     ****************************** */

    // checks if a given move is valid by comparing it to the checkMovesFor a certain piece
    public boolean isMoveValid(int x1, int y1, int x2, int y2, Board input_board) {
        ArrayList<Board> minilist =  new ArrayList<>();
        if(x1 >= 0 && x1 < 8 && y1 >= 0 && y1 < 8 && x2 >= 0 && x2 < 8 && y2 >= 0 && y2 < 8) {
            Piece piece = board[x1][y1];

            if (piece != null) {
                int value = piece.getValue();
                boolean color = piece.getColor();

                // if pawn
                if (value == pawnValue) {
                    minilist = checkMovesForPawn(color, y1, x1, input_board);
                    // if rook
                } else if (value == rookValue) {
                    minilist = checkMovesForRook(color, y1, x1, input_board);
                    // if king
                } else if (value == kingValue) {
                    minilist = checkMovesForKing(color, y1, x1, input_board);
                }

                Board board1 = pieceMoves(color, y1, x1, y2, x2, value, input_board);
                if (input_board.board[x2][y2] == null || input_board.board[x2][y2].getColor() != color) {
                    for (Board board2 : minilist) {
                        Piece piece1 = board1.board[x2][y2];
                        Piece piece2 = board2.board[x2][y2];

                        if (piece1 != null && piece2 != null && piece1.getValue() == piece2.getValue()
                                && piece1.getColor() == piece2.getColor()) return true;
                    }
                }
            }
        }
        return false;
    }


    public void addPiece(Piece piece, int x, int y) {
        board[x][y] = piece;
    }

    public void removePiece(int x, int y) {
        board[x][y] = null;
    }

    // moves a piece from and to a given position
    public Board pieceMoves(boolean color, int x, int y, int x1, int y1, int value, Board input_board) {
        Board board1 = new Board(input_board);

        if (board[y1][x1] != null) board1.removePiece(x1, y1);

        Piece piece = new Piece(color, value, y1, x1);
        board1.addPiece(piece, y1, x1);
        board1.removePiece(y,x);
        return board1;
    }

    public ArrayList checkMovesForPawn(boolean color, int x, int y, Board input_board) {
        ArrayList<Board> list = new ArrayList<>();

        if (color) {
            // for white

            if (x - 1 >= 0 && board[y][x - 1] == null) {
                list.add(pieceMoves(color, x, y, x - 1, y, pawnValue, input_board));
            }

            if (x == 6 && board[y][x - 1] == null && board[y][x - 2] == null) {
                list.add(pieceMoves(color, x, y, x - 2, y, pawnValue, input_board));
            }

            if (x - 1 >= 0 && y - 1 >= 0 && board[y - 1][x - 1] != null && !board[y - 1][x - 1].getColor()) {
                list.add(pieceMoves(color, x, y, x - 1, y - 1, pawnValue, input_board));
            }

            if (x - 1 >= 0 && y + 1 < 8 && board[y + 1][x - 1] != null && !board[y + 1][x - 1].getColor()) {
                list.add(pieceMoves(color, x, y, x - 1, y + 1, pawnValue, input_board));
            }

        } else {
            //for black

            if (x + 1 < 8 && board[y][x + 1] == null) {
                list.add(pieceMoves(color, x, y, x + 1, y, pawnValue, input_board));
            }

            if (x == 1 && board[y][x + 1] == null && board[y][x + 2] == null) {
                list.add(pieceMoves(color, x, y, x + 2, y, pawnValue, input_board));
            }

            if (x + 1 < 8 && y - 1 >= 0 && board[y - 1][x + 1] != null
                    && board[y - 1][x + 1].getColor()) {
                list.add(pieceMoves(color, x, y, x + 1, y - 1, pawnValue, input_board));
            }

            if (x + 1 < 8 && y + 1 < 8 && board[y + 1][x + 1] != null
                    && board[y + 1][x + 1].getColor()) {
                list.add(pieceMoves(color, x, y, x + 1, y + 1, pawnValue, input_board));
            }
        }
        return list;
    }

    public ArrayList checkMovesForRook(boolean color, int x, int y, Board input_board) {
        ArrayList<Board> list = new ArrayList<>();

        int x1 = x+1;
        int y1 = y+1;

        // move naar rechts
        while (x1 < 8 && board[y][x1] == null) {
            list.add(pieceMoves(color, x, y, x1, y, rookValue, input_board));
            x1++;
        }
        if (x1 < 8 && board[y][x1].getColor() != color) {
            list.add(pieceMoves(color, x, y, x1, y, rookValue, input_board));
        }

        // move naar beneden
        while (y1 < 8 && board[y1][x] == null) {
            list.add(pieceMoves(color, x, y, x, y1, rookValue, input_board));
            y1++;
        }
        if (y1 < 8 && board[y1][x].getColor() != color) {
            list.add(pieceMoves(color, x, y, x, y1, rookValue, input_board));
        }

        x1 = x-1;
        y1 = y-1;

        // move naar links
        while (x1 >= 0 && board[y][x1] == null) {
            list.add(pieceMoves(color, x, y, x1, y, rookValue, input_board));
            x1--;
        }
        if (x1 >= 0 && board[y][x1].getColor() != color) {
            list.add(pieceMoves(color, x, y, x1, y, rookValue, input_board));
        }

        // move naar boven
        while (y1 >= 0 && board[y1][x] == null) {
            list.add(pieceMoves(color, x, y, x, y1, rookValue, input_board));
            y1--;
        }
        if (y1 >= 0 && board[y1][x].getColor() != color) {
            list.add(pieceMoves(color, x, y, x, y1, rookValue, input_board));
        }

        return list;
    }

    public ArrayList checkMovesForKing(boolean color, int x, int y, Board input_board) {
        ArrayList<Board> list = new ArrayList<>();

        int x1;
        int y1;

        if (x+1 < 8) {
            x1 = x+1;
            y1 = y;

            if (board[y1][x1] == null || board[y1][x1].getColor() != color) {
                list.add(pieceMoves(color, x, y, x1, y1, kingValue, input_board));
            } else if (board[y1][x1] != null && board[y1][x1].getColor() != color) {
                list.add(pieceMoves(color, x, y, x1, y1, kingValue, input_board));
            }
        }
        if (x-1 >= 0 ) {
            x1 = x-1;
            y1 = y;

            if (board[y1][x1] == null || board[y1][x1].getColor() != color) {
                list.add(pieceMoves(color, x, y, x1, y1, kingValue, input_board));
            } else if (board[y1][x1] != null && board[y1][x1].getColor() != color) {
                list.add(pieceMoves(color, x, y, x1, y1, kingValue, input_board));
            }
        }
        if (y+1 < 8) {
            x1 = x;
            y1 = y+1;

            if (board[y1][x1] == null || board[y1][x1].getColor() != color) {
                list.add(pieceMoves(color, x, y, x1, y1, kingValue, input_board));
            } else if (board[y1][x1] != null && board[y1][x1].getColor() != color) {
                list.add(pieceMoves(color, x, y, x1, y1, kingValue, input_board));
            }
        }
        if (y-1 >= 0) {
            x1 = x;
            y1 = y-1;

            if (board[y1][x1] == null || board[y1][x1].getColor() != color) {
                list.add(pieceMoves(color, x, y, x1, y1, kingValue, input_board));
            } else if (board[y1][x1] != null && board[y1][x1].getColor() != color) {
                list.add(pieceMoves(color, x, y, x1, y1, kingValue, input_board));
            }
        }
        if (y+1 < 8 && x+1 < 8) {
            x1 = x+1;
            y1 = y+1;

            if (board[y1][x1] == null || board[y1][x1].getColor() != color) {
                list.add(pieceMoves(color, x, y, x1, y1, kingValue, input_board));
            } else if (board[y1][x1] != null && board[y1][x1].getColor() != color) {
                list.add(pieceMoves(color, x, y, x1, y1, kingValue, input_board));
            }
        }
        if (y-1 >= 0 && x+1 < 8) {
            x1 = x+1;
            y1 = y-1;

            if (board[y1][x1] == null || board[y1][x1].getColor() != color) {
                list.add(pieceMoves(color, x, y, x1, y1, kingValue, input_board));
            } else if (board[y1][x1] != null && board[y1][x1].getColor() != color) {
                list.add(pieceMoves(color, x, y, x1, y1, kingValue, input_board));
            }
        }
        if (y+1 < 8 && x-1 >= 0) {
            x1 = x-1;
            y1 = y+1;

            if (board[y1][x1] == null || board[y1][x1].getColor() != color) {
                list.add(pieceMoves(color, x, y, x1, y1, kingValue, input_board));
            } else if (board[y1][x1] != null && board[y1][x1].getColor() != color) {
                list.add(pieceMoves(color, x, y, x1, y1, kingValue, input_board));
            }
        }
        if (y-1 >= 0 && x-1 >= 0 && board[y-1][x-1] == null) {
            x1 = x-1;
            y1 = y-1;

            if (board[y1][x1] == null || board[y1][x1].getColor() != color) {
                list.add(pieceMoves(color, x, y, x1, y1, kingValue, input_board));
            } else if (board[y1][x1] != null && board[y1][x1].getColor() != color) {
                list.add(pieceMoves(color, x, y, x1, y1, kingValue, input_board));
            }
        }

        return list;
    }

    public ArrayList checkMovesForAll(boolean color, Board input_board) {
        ArrayList<Board> list =  new ArrayList<Board>();
        for(int y = 0; y < 8; y++) {
            for(int x = 0; x < 8; x++) {

                Piece piece = board[y][x];
                if(piece != null) {
                    ArrayList<Board> minilist =  new ArrayList<Board>();

                    int getValue = piece.getValue();
                    boolean getColor = piece.getColor();

                    if (color == getColor) {
                        if (getValue == pawnValue) {
                            // pawns
                            minilist = checkMovesForPawn(color, x, y, input_board);
                        } else if (getValue == rookValue) {
                            // rooks
                            minilist = checkMovesForRook(color, x, y, input_board);
                        } else if (getValue == kingValue) {
                            // king
                            minilist = checkMovesForKing(color, x, y, input_board);
                        }
                    }
                    for (Board aMinilist : minilist) {
                        list.add(aMinilist);
                    }
                }
            }
        }
        return list;
    }

    /* *******************************
     * 5. Methods for Counting Score *
     ******************************* */

    public int getValue(int x, int y){
        Piece piece = board[y][x];
        if(piece != null) {
            return piece.getValue();
        }
        return 0;
    }

    public boolean getColor(int x, int y){
        Piece piece = board[y][x];
        if(piece != null) {
            return piece.getColor();
        }
        return false;
    }

    public int calculateScoreForOnePlayer(int pieceAmount, boolean color, Board input_board) {
        int score = 0;
        int pawns_factor_weight;
        int rooks_factor_weight;
        int kings_factor_weight;
        int pawns_score;
        int rooks_score;
        int kings_score;

        if(pieceAmount >= 16) {
            // set values for begingame
            pawns_factor_weight = 2;
            rooks_factor_weight = 2;
            kings_factor_weight = 1;

        } else if(pieceAmount >= 8 && pieceAmount < 16) {
            // set values for midgame
            pawns_factor_weight = 2;
            rooks_factor_weight = 3;
            kings_factor_weight = 2;

        } else {
            // set values for endgame
            pawns_factor_weight = 1;
            rooks_factor_weight = 3;
            kings_factor_weight = 3;
        }

        // loop through the matrix to find all piece positions
        for(int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                // calculate pawn score based on amount of pawns and strength of their positions
                pawns_score = calulatePawnScores(pawns_factor_weight, color, input_board, y, x);
                score += pawns_score;

                // calculate rook score based on amount of rooks and strength of their positions
                rooks_score = calculateRookScores(rooks_factor_weight, color, input_board, y, x);
                score += rooks_score;

                // calculate king score based on strength of it's position
                kings_score = calculateKingScores(kings_factor_weight, color, input_board, y, x);
                score += kings_score;
            }
        }
        return score;
    }

    public int calulatePawnScores(int pawn_factor_weight, boolean color, Board input_board, int y, int x) {
        int score = 0;

        if (board[y][x] != null && board[y][x].getValue() == pawnValue
                && board[y][x].getColor() == color) {

            // bonus for each pawn
            score += 5;

            // bonus for two or three adjacent pawns
            if (y + 1 < 8) {
                score += adjacentOrDiagonalPawnsBonus(color, pawn_factor_weight, y + 1, x);
            }
            if (y + 2 < 8) {
                score += adjacentOrDiagonalPawnsBonus(color, pawn_factor_weight, y + 2, x);
            }

            // bonus for pawns with diagonal cover
            if (y + 1 < 8 && x + 1 < 8) {
                score += adjacentOrDiagonalPawnsBonus(color, 2*pawn_factor_weight, y + 1, x + 1);
            }

            if (y - 1 >= 0 && x + 1 < 8) {
                score += adjacentOrDiagonalPawnsBonus(color, 2*pawn_factor_weight, y - 1, x + 1);
            }

            if (y + 1 < 8 && x - 1 >= 0) {
                score += adjacentOrDiagonalPawnsBonus(color, 2*pawn_factor_weight, y + 1, x - 1);
            }

            if (y - 1 >= 0 && x - 1 >= 0) {
                score += adjacentOrDiagonalPawnsBonus(color, 2*pawn_factor_weight, y - 1 , x - 1);
            }
        }
        return score;
    }

    public int adjacentOrDiagonalPawnsBonus(boolean color, int pawn_factor_weight, int y, int x) {
        int score = 0;
        if (board[y][x] != null && board[y][x].getValue() == pawnValue
                && board[y][x].getColor() == color) {
            score += pawn_factor_weight;
        }
        return score;
    }

    public int calculateRookScores(int rook_factor_weight, boolean color, Board input_board, int y, int x) {
        // 2 of your own rooks on one line gives you a bonus
        // the opponent's king in the move-line of your rook gives you a bonus
        // the (amount of) rooks itself give you a bonus
        // the move space of the rook gives you a bonus (greater range = greater bonus)

        int score = 0;
        Piece piece = board[y][x];

        if(piece != null && piece.getValue() == rookValue && piece.getColor() == color) {
            // bonus for each rook
            score += 20;

            int x1 = x+1;
            int y1 = y+1;
            int rookMoveSpace = 0;

            // check fields to the right until you find a piece or hit the edge of the board
            while(x1 < 8 && board[y][x1] == null) {
                x1++;
                rookMoveSpace++;
            }

            // if that piece is the opponent's king, you get a bonus
            if(x1 < 8 && board[y][x1].getColor() != color && board[y][x1].getValue() == 9) {
                score += 5 + rook_factor_weight;
            }

            // if that piece is your own (other) rook, you get a bonus (twice of course, so 3 = 6)
            if(x1 < 8 && board[y][x1].getColor() == color && board[y][x1].getValue() == rookValue) {
                score += 3;
            }


            // check fields downwards until you find a piece or hit the edge of the board
            while(y1 < 8 && board[y1][x] == null) {
                y1++;
                rookMoveSpace++;
            }

            // if that piece is the opponent's king, you get a bonus
            if(y1 < 8 && board[y1][x].getColor() != color && board[y1][x].getValue() == 9) {
                score += 5 + rook_factor_weight;
            }

            // if that piece is your own (other) rook, you get a bonus (twice of course, so 3 = 6)
            if(y1 < 8 && board[y1][x].getColor() == color && board[y1][x].getValue() == rookValue) {
                score += 3;
            }

            x1 = x-1;
            y1 = y-1;

            // check fields to the left until you find a piece or hit the edge of the board
            while(x1 >= 0 && board[y][x1] == null) {
                x1--;
                rookMoveSpace++;
            }

            // if that piece is the opponent's king, you get a bonus
            if(x1 >= 0 && board[y][x1].getColor() != color && board[y][x1].getValue() == 9) {
                score += 5 + rook_factor_weight;
            }

            // if that piece is your own (other) rook, you get a bonus (twice of course, so 3 = 6)
            if(x1 >= 0 && board[y][x1].getColor() == color && board[y][x1].getValue() == rookValue) {
                score += 3;
            }

            // check fields upwards until you find a piece or hit the edge of the board
            while(y1 >= 0 && board[y1][x] == null) {
                y1--;
                rookMoveSpace++;
            }

            // if that piece is the opponent's king, you get a bonus
            if(y1 >= 0 && board[y1][x].getColor() != color && board[y1][x].getValue() == 9) {
                score += 5 + rook_factor_weight;
            }

            // if that piece is your own (other) rook, you get a bonus (twice of course, so 3 = 6)
            if(y1 >= 0 && board[y1][x].getColor() == color && board[y1][x].getValue() == rookValue) {
                score += 3;
            }

            score += rookMoveSpace;
        }
        return score;
    }


    public int calculateKingScores(int king_factor_weight, boolean color, Board input_board, int y, int x) {
        int score = 0;
        Piece piece = board[y][x];

        if(piece != null && piece.getValue() == 9 && piece.getColor() == color) {
            // bonus for having a king
            score += 200;

            // surrounding pieces from yourself: bonus; from the opponent: malus
            if (y + 1 < 8 && x + 1 < 8) {
                score += kingSurroundingsBonus(color, king_factor_weight, y + 1, x + 1);
            }

            if (y + 1 < 8) {
                score += kingSurroundingsBonus(color, king_factor_weight, y + 1, x);
            }

            if (y + 1 < 8 && x - 1 >= 0) {
                score += kingSurroundingsBonus(color, king_factor_weight, y + 1, x - 1);
            }

            if (x + 1 < 8) {
                score += kingSurroundingsBonus(color, king_factor_weight, y, x + 1);
            }

            if (x - 1 >= 0) {
                score += kingSurroundingsBonus(color, king_factor_weight, y, x - 1);
            }

            if (y - 1 >= 0 && x + 1 < 8) {
                score += kingSurroundingsBonus(color, king_factor_weight, y - 1, x + 1);
            }

            if (y - 1 >= 0) {
                score += kingSurroundingsBonus(color, king_factor_weight, y - 1, x);
            }

            if (y - 1 >= 0 && x - 1 >= 0) {
                score += kingSurroundingsBonus(color, king_factor_weight, y - 1, x - 1);
            }
        }
        return score;
    }

    public int kingSurroundingsBonus(boolean color, int king_factor_weight, int y, int x) {
        int score = 0;
        Piece somePiece = board[y][x];
        if (somePiece != null) {
            if (somePiece.getColor() == color) {
                score += king_factor_weight;
            } else {
                score -= king_factor_weight;
            }
        }
        return score;
    }


    /* *****************************************
     * 6. Methods for determining Winner/Loser *
     ***************************************** */

    public boolean gameIsNotFinished() {
        boolean whiteKingIsAlive = false;
        boolean blackKingIsAlive = false;

        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if(board[j][i] != null && board[j][i].getValue() == kingValue) {
                    if(board[j][i].getColor()) {
                        whiteKingIsAlive = true;
                    } else if(!board[j][i].getColor()) {
                        blackKingIsAlive = true;
                    }
                }
            }
        }

        if(whiteKingIsAlive && blackKingIsAlive) {
            return true;
        }

        return false;
    }

    public String getWinner() {
        boolean whiteKingIsAlive = false;
        boolean blackKingIsAlive = false;

        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if(board[j][i] != null && board[j][i].getValue() == kingValue) {
                    if(board[j][i].getColor()) {
                        whiteKingIsAlive = true;
                    }
                    if(!board[j][i].getColor()) {
                        blackKingIsAlive = true;
                    }
                }
            }
        }

        if(!whiteKingIsAlive) {
            return "Unfortunately the computer beat you. Better luck next time!";
        } else if(!blackKingIsAlive) {
            return "Congratulations human, you have won the game!";
        }

        return "I don't know who the winner is.";
    }

    /* ******************************
     * 7. Methods for visualisation *
     ****************************** */

    // prints the board (the matrix, not the object Board itself)
    public void printBoard() {
        for (int i = 0; i < size; i++){
            System.out.print((8-i) + "  ");
            for (int j = 0; j < size; j++){
                if(board[j][i] != null && board[j][i].getColor()) {
                    System.out.print(" W" + board[j][i].getValue());
                } else if(board[j][i] != null && !board[j][i].getColor()) {
                    System.out.print(" B" + board[j][i].getValue());
                } else {
                    System.out.print(" . ");
                }
            }
            System.out.println();
        }
        System.out.println("    a  b  c  d  e  f  g  h");
        System.out.println("");
    }

}