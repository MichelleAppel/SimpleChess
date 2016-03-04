package com.company;
import java.util.ArrayList;

public class Rook extends Piece {

    public Rook(boolean color, int x, int y) {
        super(color, 1, x ,y);
    }

    @Override
    public ArrayList<Board> getMoves() {
        ArrayList<Move> temp = new ArrayList<Move>();

        boolean color = this.color;
        int x = this.x;
        int y = this.y;



        return temp;
    }
}
