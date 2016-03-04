package com.company;
import java.util.ArrayList;
import java.util.List;

public class Board {

    private int size;
    private Piece[][] board;
    private Piece[] pieces;

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

    

    public ArrayList<Board> generateAllChildren() {
        ArrayList<Board> array_list = new ArrayList<>();

        for(int i = 0; i < pieces.length; i++) {
            int x = pieces[i].getX();
            int y = pieces[i].getY();
            boolean color = pieces[i].getColor();
            int value = pieces[i].getValue();

            if(value == 1) {
                // generate all moves for pawn
                //generateMovesForPawn(x, y, color);
                if(board[x][y-1] == null) {
                    Board new_board = new Board(this);
                    new_board.moveRight(x, y);
                    array_list.add(new_board);
                }
            }

            if(value == 5) {
                // generate all moves for rook
            }

            if(value == 9) {
                // generate all moves for king
            }



        }

    public void generateMovesForPawn(int x, int y, boolean color) {
        ArrayList<Board> array_list = new ArrayList<>();

        if() {
            Board new_board = new Board(this);
            new_board.moveRight(x, y);
            array_list.add(new_board);
        }
    }


        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                if (grid[x][y] != null) {

                    boolean direction = grid[x][y].getDirection();
                    // only if object is the most left or top of the entire vehicle
                    if ((direction && (x == 0 || (x > 0 && grid[x][y] != grid[x - 1][y]))) ||
                            (!direction && (y == 0 || (y > 0 && grid[x][y] != grid[x][y - 1])))) {

                        if (moveRightIsPossible(x, y) && moveLeftIsPossible(x, y)) {
                            Grid new_grid = new Grid(this);
                            new_grid.moveRight(x, y);
                            array_list.add(new_grid);

                            Grid new_grid2 = new Grid(this);
                            new_grid2.moveLeft(x, y);
                            array_list.add(new_grid2);
                        } else if (moveRightIsPossible(x, y)) {
                            Grid new_grid = new Grid(this);
                            new_grid.moveRight(x, y);
                            array_list.add(new_grid);
                        } else if (moveLeftIsPossible(x, y)) {
                            Grid new_grid = new Grid(this);
                            new_grid.moveLeft(x, y);
                            array_list.add(new_grid);
                        } else if (moveDownIsPossible(x, y) && moveUpIsPossible(x, y)) {
                            Grid new_grid = new Grid(this);
                            new_grid.moveDown(x, y);
                            array_list.add(new_grid);

                            Grid new_grid2 = new Grid(this);
                            new_grid2.moveUp(x, y);
                            array_list.add(new_grid2);
                        } else if (moveDownIsPossible(x, y)) {
                            Grid new_grid = new Grid(this);
                            new_grid.moveDown(x, y);
                            array_list.add(new_grid);
                        } else if (moveUpIsPossible(x, y)) {
                            Grid new_grid = new Grid(this);
                            new_grid.moveUp(x, y);
                            array_list.add(new_grid);
                        }
                    }
                }
            }
        }
        return array_list;
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

    public void getPawnMoves(Board board) {
        Piece pawn = new Pawn(false, 1, 1);
        pawn.getMoves();
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
            } else {
                System.out.println(x + "/" + y + "is not valid move.");
                fieldIsEmpty = false;
            }
            x -= 1;

            Piece p1 = new Pawn(false,1,1);

            BoardSituations[] m = p1.getMoves(this);
        }

        // move(s) right
        x = initX;
        y = initY;
        fieldIsEmpty = true;
        while(x < 8 && fieldIsEmpty) {
            if(board[x+1][y] == null) {
                System.out.println(x + "/" + y + "is a valid move.");
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
            int value = 9;
            board[i][6] = new Pawn(true, value, i, 6);     // white pawn
            board[i][1] = new Pawn(false, value, i, 1);    // black pawn
        }
    }

    // adds black and white rooks to board
    public void addRooks() {
        int value = 5;
        board[0][7] = new Rook(true, value, 0, 7);         // white rook 1
        board[7][7] = new Rook(true, value, 7, 7);         // white rook 2
        board[0][0] = new Rook(false, value, 0, 0);        // black rook 1
        board[7][0] = new Rook(false, value, 7, 0);        // black rook 2
    }

    // adds black and white kings to board
    public void addKings() {
        int value = 9;
        board[4][7] = new King(true, value, 4, 7);         // white king
        board[4][0] = new King(false, value, 4, 0);        // black king
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