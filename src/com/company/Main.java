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
        /*
        System.out.println("The TEST board below is to test the score calculation:");
        board.addTestBoard();   // test board
        */
        //wipeScreen();
        board.printBoard();
        //delay(2000);


        // add first grid to queue
        queue.add(board);

        while(true) {
            board = userMove();
            board.printBoard();

            // add cpu moves here
        }






/*
        int pieceAmount = AMOUNT_OF_PIECES; // used to know whether status is begingame, midgame or endgame

        // CALCULATING SCORE AI ALGORITHM: (parameters are pieceAmount, player color and board)
        int whiteScore = board.calculateScoreForOnePlayer(pieceAmount, true, board);
        int blackScore = board.calculateScoreForOnePlayer(pieceAmount, false, board);

        System.out.println("The score for black/up (false) is: " + blackScore);
        System.out.println("The score for white/down (true) is: " + whiteScore);
*/







/*
        for(int i = 0; i < 3; i++) {
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
*/


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

    public static Board userMove() {
        int[] coordinates = getUserInput();

        int startX = coordinates[0];
        int startY = coordinates[1];
        int endX = coordinates[2];
        int endY = coordinates[3];

        while (!board.isMoveValid(startX, startY, endX, endY, board) || !board.getColor(startY, startX)) {
            System.out.println("That move is invalid, try again");
            coordinates = getUserInput();

            startX = coordinates[0];
            startY = coordinates[1];
            endX = coordinates[2];
            endY = coordinates[3];
        }

        return board.pieceMoves(true, startY, startX, endY, endX, board.getValue(startY, startX), board);
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

    private static int[] getUserInput() {
        Scanner input = new Scanner(System.in);

        System.out.println("Please enter the start and end position of the piece you want to move.");
        System.out.println("Please use capital letters and no spaces, like this: D2D4");
        String input_string = input.next(); // getting a String value
        //System.out.println(input_string);

        int startX = -1;
        int startY = -1;
        int endX = -1;
        int endY = -1;

        if(input_string.length() == 4) {

            String startXString = input_string.substring(0, 1);
            String startYString = input_string.substring(1, 2);
            String endXString = input_string.substring(2, 3);
            String endYString = input_string.substring(3, 4);

            // validate start X (is the char in the correct range?)
            char startXChar = startXString.charAt(0);
            if (startXChar == 'A' || startXChar == 'a') {
                startX = 0;
            } else if (startXChar == 'B' || startXChar == 'b') {
                startX = 1;
            } else if (startXChar == 'C' || startXChar == 'c') {
                startX = 2;
            } else if (startXChar == 'D' || startXChar == 'd') {
                startX = 3;
            } else if (startXChar == 'E' || startXChar == 'e') {
                startX = 4;
            } else if (startXChar == 'F' || startXChar == 'f') {
                startX = 5;
            } else if (startXChar == 'G' || startXChar == 'g') {
                startX = 6;
            } else if (startXChar == 'H' || startXChar == 'h') {
                startX = 7;
            }

            // validate start Y (is the int in the correct range?)
            int startYInt = Integer.parseInt(startYString);
            if (startYInt >= 1 && startYInt <= 8) {
                startY = 8 - (startYInt);
            } else {
                // error
            }

            // validate end X (is the char in the correct range?)
            char endXChar = endXString.charAt(0);
            if (endXChar == 'A' || endXChar == 'a') {
                endX = 0;
            } else if (endXChar == 'B' || endXChar == 'b') {
                endX = 1;
            } else if (endXChar == 'C' || endXChar == 'c') {
                endX = 2;
            } else if (endXChar == 'D' || endXChar == 'd') {
                endX = 3;
            } else if (endXChar == 'E' || endXChar == 'e') {
                endX = 4;
            } else if (endXChar == 'F' || endXChar == 'f') {
                endX = 5;
            } else if (endXChar == 'G' || endXChar == 'g') {
                endX = 6;
            } else if (endXChar == 'H' || endXChar == 'h') {
                endX = 7;
            }

            // validate end Y (is the int in the correct range?)
            int endYInt = Integer.parseInt(endYString);
            if (endYInt >= 1 && endYInt <= 8) {
                endY = 8 - (endYInt);
            } else {
                // error
            }

        }
        int[] coordinates = {startX, startY, endX, endY};
        return coordinates;
    }
}