package com.company;
import java.util.ArrayList;

public class Pawn extends Piece {

    public Pawn(boolean color, int x, int y) {
        super(color, 1, x ,y);
    }

    @Override
    public ArrayList<BoardSituations> getMoves(Board board) {
        ArrayList<BoardSituations> possible_moves = new ArrayList<BoardSituations>();
this.board = board
        boolean color = this.color;
        int x = this.x;
        int y = this.y;


        if(color) {
            // color = true (white)
            if(y == 6) {
                if(board[x][y-1] == null) {
                    Board new_board = new Board(board);
                    new_board.moveRight(x, y);
                    possible_moves.add(new_board);
                }
                if(board[x][y-2] == null) {

                }
            }

        } else {
            // color = false (black)


        }

        return possible_moves;
    }
}
