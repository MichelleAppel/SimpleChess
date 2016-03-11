package com.company;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    // chess board is always 8x8 fields
    private static int BOARD_SIZE = 8;

    // simple chess has 32 pieces (8x2 pawns, 2x2 rooks, 1x2 kings)
    private static final int AMOUNT_OF_PIECES = 22;

    // declare new object from class Board
    private static Board board = new Board(BOARD_SIZE, AMOUNT_OF_PIECES);

    // linked list
    private static LinkedList<Board> queue = new LinkedList<>();

    public static void main(String[] args) {
        board.addPieces();
        //wipeScreen();
        board.printBoard();
        //delay(2000);

        Scanner input = new Scanner(System.in);

        System.out.println("Please enter the start and end position of the piece you want to move: ");
        System.out.println("For example: K1 K2");

        String s = input.next(); // getting a String value
        System.out.println(s);


        // add first grid to queue
        queue.add(board);

        for(int i = 0; i < 5; i++) {
            System.out.println("i is" + i);

            // retrieve first element and generate all children
            Board first_board = queue.getFirst();
            ArrayList<Board> new_children = first_board.checkMovesForAll(false, first_board);
            for (int j = 0; j < new_children.size(); j++) {
                Board child = new_children.get(j);
                child.printBoard();
                queue.add(child);
            }

            // remove first element from grid
            queue.removeFirst();
        }

        queue.getLast().printBoard();


        /*
        ArrayList<Board> list = board.checkMovesForPawn(true, 6, 0, board);

        for(int i = 0; i < list.size(); i++) {
            list.get(i).printBoard();
        }

        ArrayList<Board> list2 = board.checkMovesForRook(false, 0,0, board);

        for(int i = 0; i < list2.size(); i++) {
            list2.get(i).printBoard();
        }

        ArrayList<Board> list3 = board.checkMovesForKing(true, 7,4, board);

        for(int i = 0; i < list3.size(); i++) {
            list3.get(i).printBoard();
        }
        */

        /*
        ArrayList<Board> list4 = board.checkMovesForAll(false, board);

        for (Board aList4 : list4) {
            aList4.printBoard();
        }


        Board board2 = new Board(list4.get(4)) ;
        ArrayList<Board> list5 = board2.checkMovesForAll(true, board2);

        for (Board aList5 : list5) {
            aList5.printBoard();
        }
        */
    }

    // wipe the screen
    private static void wipeScreen() {
        for(int i = 0; i < 10; i++) {
            System.out.println();
        }
    }

    // delay (useful to see if move methods actually work)
    private static void delay(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}