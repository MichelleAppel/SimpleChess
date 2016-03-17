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

    private int size;
    private Piece[][] board;
    private Piece[] pieces;

    private int piece_number = 1;
    private int amount_of_pieces;


    /* **************************
     * 2. Making constructor(s) *
     ************************** */

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
        System.arraycopy(previous.pieces, 1, pieces, 1, amount_of_pieces);
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

    // adds a test piece configuration to the board (matrix)
    public void addTestBoard() {
        // white pawns
        board[0][6] = new Piece(true, 1, 1, 6);
        board[1][5] = new Piece(true, 1, 1, 6);
        board[2][4] = new Piece(true, 1, 1, 6);
        board[3][5] = new Piece(true, 1, 1, 6);
        board[4][6] = new Piece(true, 1, 1, 6);
        board[5][6] = new Piece(true, 1, 1, 6);
        board[6][6] = new Piece(true, 1, 1, 6);
        board[7][5] = new Piece(true, 1, 1, 6);

        // black pawns
        board[0][1] = new Piece(false, 1, 0, 1);
        board[1][1] = new Piece(false, 1, 1, 1);
        board[2][1] = new Piece(false, 1, 2, 1);
        board[3][1] = new Piece(false, 1, 3, 1);
        board[4][1] = new Piece(false, 1, 4, 1);
        board[5][1] = new Piece(false, 1, 5, 1);
        board[6][1] = new Piece(false, 1, 6, 1);
        board[7][1] = new Piece(false, 1, 7, 1);

        // the rooks
        board[0][7] = new Piece(true, 5, 0, 7);         // white rook 1
        board[7][7] = new Piece(true, 5, 7, 7);         // white rook 2
        board[0][0] = new Piece(false, 5, 0, 0);        // black rook 1
        board[7][0] = new Piece(false, 5, 7, 0);        // black rook 2

        // the kings
        board[4][7] = new Piece(true, 9, 4, 7);         // white king
        board[4][0] = new Piece(false, 9, 4, 0);        // black king
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


    /* ******************************
     * 4. Methods for Moving Pieces *
     ****************************** */

    // checks if a given move is valid by comparing it to the checkMovesFor a certain piece
    public boolean isMoveValid(int x1, int y1, int x2, int y2, Board input_board) {
        ArrayList<Board> minilist =  new ArrayList<Board>();
        if(x1 >= 0 && x1 < 8 && y1 >= 0 && y1 < 8 && x2 >= 0 && x2 < 8 && y2 >= 0 && y2 < 8) {
            Piece piece = board[x1][y1];

            if (piece != null) {
                int value = piece.getValue();
                boolean color = piece.getColor();

                // if pawn
                if (value == 1) {
                    minilist = checkMovesForPawn(color, y1, x1, input_board);
                    // if rook
                } else if (value == 5) {
                    minilist = checkMovesForRook(color, y1, x1, input_board);
                    // if king
                } else if (value == 9) {
                    minilist = checkMovesForKing(color, y1, x1, input_board);
                }

                Board board1 = pieceMoves(color, y1, x1, y2, x2, value, input_board);
                if(input_board.board[x2][y2] == null || input_board.board[x2][y2].getColor() != color) {
                    for (int i = 0; i < minilist.size(); i++) {
                        Board board2 = minilist.get(i);

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

        if(board[y1][x1] != null) board1.removePiece(x1, y1);

        Piece piece = new Piece(color, value, y1, x1);
        board1.addPiece(piece, y1, x1);
        board1.removePiece(y,x);
        return board1;
    }

    public ArrayList checkMovesForPawn(boolean color, int x, int y, Board input_board) {
        ArrayList<Board> list = new ArrayList<>();
        int value = 1;

        int x1;
        int y1;

        // for white
        if(color) {

            x1 = x-1;
            y1 = y;
            if (x1 >= 0 && board[y1][x1] == null) {
                list.add(pieceMoves(color, x, y, x1, y1, value, input_board));
            }

            x1 = x-2;
            if(x == 6 && board[y1][x-1] == null && board[y][x1] == null) {
                list.add(pieceMoves(color, x, y, x1, y1, value, input_board));
            }

            x1 = x-1;
            y1 = y-1;
            if(x1 >= 0 && y1 >= 0 && board[y1][x1] != null && !board[y1][x1].getColor()) {
                list.add(pieceMoves(color, x, y, x1, y1, value, input_board));
            }

            y1 = y+1;
            if(x1 >= 0 && y1 >= 0 && board[y1][x1] != null && !board[y1][x1].getColor()) {
                list.add(pieceMoves(color, x, y, x1, y1, value, input_board));
            }
            //for black
        } else {
            x1 = x+1;
            y1 = y;
            if (x1 >= 0 && board[y1][x1] == null) {
                list.add(pieceMoves(color, x, y, x1, y1, value, input_board));
            }

            x1 = x+2;
            if(x == 1 && board[y1][x+1] == null && board[y][x1] == null) {
                list.add(pieceMoves(color, x, y, x1, y1, value, input_board));
            }

            x1 = x+1;
            y1 = y-1;
            if(x1 >= 0 && y1 >= 0 && board[y1][x1] != null && board[y1][x1].getColor()) {
                list.add(pieceMoves(color, x, y, x1, y1, value, input_board));
            }

            y1 = y+1;
            if(x1 >= 0 && x1 < 8 && y1 < 8 && board[y1][x1] != null && board[y1][x1].getColor()) {
                list.add(pieceMoves(color, x, y, x1, y1, value, input_board));
            }
        }
        return list;
    }

    public ArrayList checkMovesForRook(boolean color, int x, int y, Board input_board) {
        ArrayList<Board> list = new ArrayList<>();

        int value = 5;
        int x1 = x+1;
        int y1 = y+1;

        // move naar rechts
        while(x1 < 8 && board[y][x1] == null) {
            list.add(pieceMoves(color, x, y, x1, y, value, input_board));
            x1++;
        }
        if(x1 < 8 && board[y][x1].getColor() != color) {
            list.add(pieceMoves(color, x, y, x1, y, value, input_board));
        }

        // move naar beneden
        while(y1 < 8 && board[y1][x] == null) {
            list.add(pieceMoves(color, x, y, x, y1, value, input_board));
            y1++;
        }
        if(y1 < 8 && board[y1][x].getColor() != color) {
            list.add(pieceMoves(color, x, y, x, y1, value, input_board));
        }

        x1 = x-1;
        y1 = y-1;

        // move naar links
        while(x1 >= 0 && board[y][x1] == null) {
            list.add(pieceMoves(color, x, y, x1, y, value, input_board));
            x1--;
        }
        if(x1 >= 0 && board[y][x1].getColor() != color) {
            list.add(pieceMoves(color, x, y, x1, y, value, input_board));
        }

        // move naar boven
        while(y1 >= 0 && board[y1][x] == null) {
            list.add(pieceMoves(color, x, y, x, y1, value, input_board));
            y1--;
        }
        if(y1 >= 0 && board[y1][x].getColor() != color) {
            list.add(pieceMoves(color, x, y, x, y1, value, input_board));
        }

        return list;
    }

    public ArrayList checkMovesForKing(boolean color, int x, int y, Board input_board) {
        ArrayList<Board> list = new ArrayList<>();


        int x1;
        int y1;

        int value = 9;

        if(x+1 < 8) {
            x1 = x+1;
            y1 = y;

            if (board[y1][x1] == null) {
                list.add(pieceMoves(color, x, y, x1, y1, value, input_board));
            } else if (board[y1][x1] != null && board[y1][x1].getColor() != color) {
                list.add(pieceMoves(color, x, y, x1, y1, value, input_board));
            }
        }
        if(x-1 >= 0 ) {
            x1 = x-1;
            y1 = y;

            if (board[y1][x1] == null) {
                list.add(pieceMoves(color, x, y, x1, y1, value, input_board));
            } else if (board[y1][x1] != null && board[y1][x1].getColor() != color) {
                list.add(pieceMoves(color, x, y, x1, y1, value, input_board));
            }
        }
        if(y+1 < 8) {
            x1 = x;
            y1 = y+1;

            if (board[y1][x1] == null) {
                list.add(pieceMoves(color, x, y, x1, y1, value, input_board));
            } else if (board[y1][x1] != null && board[y1][x1].getColor() != color) {
                list.add(pieceMoves(color, x, y, x1, y1, value, input_board));
            }
        }
        if(y-1 >= 0) {
            x1 = x;
            y1 = y-1;

            if (board[y1][x1] == null) {
                list.add(pieceMoves(color, x, y, x1, y1, value, input_board));
            } else if (board[y1][x1] != null && board[y1][x1].getColor() != color) {
                list.add(pieceMoves(color, x, y, x1, y1, value, input_board));
            }
        }
        if(y+1 < 8 && x+1 < 8) {
            x1 = x+1;
            y1 = y+1;

            if (board[y1][x1] == null) {
                list.add(pieceMoves(color, x, y, x1, y1, value, input_board));
            } else if (board[y1][x1] != null && board[y1][x1].getColor() != color) {
                list.add(pieceMoves(color, x, y, x1, y1, value, input_board));
            }
        }
        if(y-1 >= 0 && x+1 < 8) {
            x1 = x+1;
            y1 = y-1;

            if (board[y1][x1] == null) {
                list.add(pieceMoves(color, x, y, x1, y1, value, input_board));
            } else if (board[y1][x1] != null && board[y1][x1].getColor() != color) {
                list.add(pieceMoves(color, x, y, x1, y1, value, input_board));
            }
        }
        if(y+1 < 8 && x-1 >= 0) {
            x1 = x-1;
            y1 = y+1;

            if (board[y1][x1] == null) {
                list.add(pieceMoves(color, x, y, x1, y1, value, input_board));
            } else if (board[y1][x1] != null && board[y1][x1].getColor() != color) {
                list.add(pieceMoves(color, x, y, x1, y1, value, input_board));
            }
        }
        if(y-1 >= 0 && x-1 >= 0 && board[y-1][x-1] == null) {
            x1 = x-1;
            y1 = y-1;

            if (board[y1][x1] == null) {
                list.add(pieceMoves(color, x, y, x1, y1, value, input_board));
            } else if (board[y1][x1] != null && board[y1][x1].getColor() != color) {
                list.add(pieceMoves(color, x, y, x1, y1, value, input_board));
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
                        if (getValue == 1) {
                            // pawns
                            minilist = checkMovesForPawn(color, x, y, input_board);
                        } else if (getValue == 5) {
                            // rooks
                            minilist = checkMovesForRook(color, x, y, input_board);
                        } else if (getValue == 9) {
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
        // 2 eigen pionnen naast elkaar: bonus
        // 3+ eigen pionnen naast elkaar: dubbele bonus
        // 2 eigen pionnen die elkaar schuin dekken: bonus
        // 3 eigen pionnen die elkaar schuin dekken: dubbele bonus
        // punten voor aantal pionnen

        int score = 0;
        // calculate score

        if (board[y][x] != null && board[y][x].getValue() == 1
                && board[y][x].getColor() == color) {
            score += 5;

            // patroon 1: 2 of 3 pionnen naast elkaar
            if (y + 1 < 8) {
                if (board[y + 1][x] != null && board[y + 1][x].getValue() == 1
                        && board[y + 1][x].getColor() == color) {
                    score += 1 * pawn_factor_weight;
                }
            }

            if (y + 2 < 8) {
                if (board[y + 2][x] != null && board[y + 2][x].getValue() == 1
                        && board[y + 2][x].getColor() == color) {
                    score += 2 * pawn_factor_weight;
                }
            }

            // patroon 2: 2 of 3 pionnen met schuine dekking
            if (y + 1 < 8 && x + 1 < 8) {
                if (board[y + 1][x + 1] != null && board[y + 1][x + 1].getValue() == 1
                        && board[y + 1][x + 1].getColor() == color) {
                    score += 3 * pawn_factor_weight;
                }
            }

            if (y - 1 >= 0 && x + 1 < 8) {
                if (board[y - 1][x + 1] != null && board[y - 1][x + 1].getValue() == 1
                        && board[y - 1][x + 1].getColor() == color) {
                    score += 3 * pawn_factor_weight;
                }
            }

            if (y + 1 < 8 && x - 1 >= 0) {
                if (board[y + 1][x - 1] != null && board[y + 1][x - 1].getValue() == 1
                        && board[y + 1][x - 1].getColor() == color) {
                        score += 3 * pawn_factor_weight;
                }
            }

            if (y - 1 >= 0 && x - 1 >= 0) {
                if (board[y - 1][x - 1] != null && board[y - 1][x - 1].getValue() == 1
                        && board[y - 1][x - 1].getColor() == color) {
                        score += 3 * pawn_factor_weight;
                }
            }
        }
        return score;
    }

    public int calculateRookScores(int rook_factor_weight, boolean color, Board input_board, int y, int x) {
        // 2 of your own rooks on one line gives you a bonus
        // the opponent's king in the move-line of your rook gives you a bonus
        // the (amount of) rooks itself give you a bonus
        // the move space of the rook gives you a bonus (greater range = greater bonus)

        int score = 0;
        int value = 5;  // value for rooks
        Piece piece = board[y][x];

        if(piece != null && piece.getValue() == value && piece.getColor() == color) {
            // points for having a rook
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
            if(x1 < 8 && board[y][x1].getColor() == color && board[y][x1].getValue() == 5) {
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
            if(y1 < 8 && board[y1][x].getColor() == color && board[y1][x].getValue() == 5) {
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
            if(x1 >= 0 && board[y][x1].getColor() == color && board[y][x1].getValue() == 5) {
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
            if(y1 >= 0 && board[y1][x].getColor() == color && board[y1][x].getValue() == 5) {
                score += 3;
            }

            score += rookMoveSpace;
        }
        return score;
    }

    public int calculateKingScores(int king_factor_weight, boolean color, Board input_board, int y, int x) {
        // aantal eigen omringende stukken: bonus
        // aantal omringende stukken van de tegenstander: malus

        int score = 0;
        Piece piece = board[y][x];
        if(piece != null && piece.getValue() == 9 && piece.getColor() == color) {
            score += 41;

            if (y + 1 < 8 && x + 1 < 8) {
                Piece otherPiece = board[y + 1][x + 1];
                if (otherPiece != null) {
                    if (piece.getColor() == color) {
                        score += 1*king_factor_weight;
                    } else {
                        score -= 1*king_factor_weight;
                    }
                }
            }

            if (y + 1 < 8) {
                Piece otherPiece = board[y + 1][x];
                if (otherPiece != null) {
                    if (piece.getColor() == color) {
                        score += 1*king_factor_weight;
                    } else {
                        score -= 1*king_factor_weight;
                    }
                }
            }

            if (y + 1 < 8 && x - 1 >= 0) {
                Piece otherPiece = board[y + 1][x - 1];
                if (otherPiece != null) {
                    if (piece.getColor() == color) {
                        score += 1*king_factor_weight;
                    } else {
                        score -= 1*king_factor_weight;
                    }
                }
            }

            if (x + 1 < 8) {
                Piece otherPiece = board[y][x + 1];
                if (otherPiece != null) {
                    if (piece.getColor() == color) {
                        score += 1*king_factor_weight;
                    } else {
                        score -= 1*king_factor_weight;
                    }
                }
            }


            if (x - 1 >= 0) {
                Piece otherPiece = board[y][x - 1];
                if (otherPiece != null) {
                    if (piece.getColor() == color) {
                        score += 1*king_factor_weight;
                    } else {
                        score -= 1*king_factor_weight;
                    }
                }
            }

            if (y - 1 >= 0 && x + 1 < 8) {
                Piece otherPiece = board[y - 1][x + 1];
                if (otherPiece != null) {
                    if (piece.getColor() == color) {
                        score += 1*king_factor_weight;
                    } else {
                        score -= 1*king_factor_weight;
                    }
                }
            }

            if (y - 1 >= 0) {
                Piece otherPiece = board[y - 1][x];
                if (otherPiece != null) {
                    if (piece.getColor() == color) {
                        score += 1*king_factor_weight;
                    } else {
                        score -= 1*king_factor_weight;
                    }
                }
            }

            if (y - 1 >= 0 && x - 1 >= 0) {
                Piece otherPiece = board[y - 1][x - 1];
                if (otherPiece != null) {
                    if (piece.getColor() == color) {
                        score += 1*king_factor_weight;
                    } else {
                        score -= 1*king_factor_weight;
                    }
                }
            }
        }
        return score;
    }


    /* *****************************************
     * 6. Methods for determining Winner/Loser *
     ***************************************** */

    // not made yet


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