package com.company;

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Main {

    private static int BOARD_SIZE = 8;                  // size of a chess board
    private static final int AMOUNT_OF_PIECES = 22;     // 8x2 pawns, 2x2 rooks, 1x2 kings

    // declare new object from the Board class
    private static Board board = new Board(BOARD_SIZE, AMOUNT_OF_PIECES);


    public static void main(String[] args) {
        board.addPieces();          // adds the pieces to the board
        board.printBoard();       // prints the board

        // pieceAmount is used to know whether the game status is begingame, midgame or endgame
        int pieceAmount = AMOUNT_OF_PIECES;

        //welcomeScreen();

        //main game play loop
        while (board.gameIsNotFinished()) {
            board = userMove();
            delay(1000);
            wipeScreen();
            board.printBoard();

            //computerMove();
            //wipeScreen();
            //board.printBoard();

            ArrayList<Node> nodes = new ArrayList<>();
            nodes.add(new Node(null, board, null, true));

            // add cpu moves here
            ArrayList<Board> new_children = board.checkMovesForAll(false, board);

            nodes = generateNextLayer(nodes, false);
            nodes = generateNextLayer(nodes, true);
            nodes = generateNextLayer(nodes, false);

            for (Node node : nodes) {
                Board board = node.getLeafBoard();
                int whiteScore = board.calculateScoreForOnePlayer(pieceAmount, true, board);
                int blackScore = board.calculateScoreForOnePlayer(pieceAmount, false, board);

                int scoreDifference = whiteScore - blackScore;
                node.setScore(scoreDifference);
                //System.out.println(node.getScore());
            }

            HashSet<Node> parentNodes = new HashSet<>();

            for (Node node : nodes) {
                Integer childScore = node.getScore();
                Integer parentScore = node.getParent().getScore();

                if (parentScore == null) {
                    node.getParent().setScore(childScore);

                } else if (!node.getColor() && childScore < parentScore) {
                    //blackScore < parent Node score -> vervangen;
                    node.getParent().setScore(childScore);

                } else if (node.getColor() && childScore > parentScore) {
                    // whiteScore > parent Node score -> vervangen;
                    node.getParent().setScore(childScore);
                }

                //System.out.println("child" + node.getScore());
                //System.out.println("parent" + node.getParent().getScore());
                parentNodes.add(node.getParent());

            }

            HashSet<Node> grandparentNodes = new HashSet<>();

            for (Node node : parentNodes) {
                Integer childScore = node.getScore();
                Integer parentScore = node.getParent().getScore();

                if (parentScore == null) {
                    node.getParent().setScore(childScore);

                } else if (!node.getColor() && childScore < parentScore) {
                    //blackScore < parent Node score -> vervangen;
                    node.getParent().setScore(childScore);

                } else if (node.getColor() && childScore > parentScore) {
                    // whiteScore > parent Node score -> vervangen;
                    node.getParent().setScore(childScore);
                }

                //System.out.println("child" + node.getScore());
                //System.out.println("parent" + node.getParent().getScore());
                grandparentNodes.add(node.getParent());

            }
            /*
            for (Node node : parentNodes) {
                Integer parentScore = node.getScore();
                Integer grandparentScore = node.getParent().getScore();
                System.out.println("GPS" + grandparentScore);
                System.out.println("PS" + parentScore);


                if (grandparentScore == null) {
                    node.getParent().setScore(parentScore);

                } else if (!node.getColor() && parentScore < grandparentScore) {
                    //blackScore < parent Node score -> vervangen;
                    node.getParent().setScore(parentScore);

                } else if (node.getColor() && parentScore > grandparentScore) {
                    // whiteScore > parent Node score -> vervangen;
                    node.getParent().setScore(parentScore);
                }
                grandparentNodes.add(node.getParent());
                System.out.println("parent" + node.getScore());
                System.out.println("grandparent" + node.getParent().getScore());
            }
*/

            System.out.println(parentNodes.size());
            System.out.println(grandparentNodes.size());

            Integer bestScore = null;
            Board bestBoard = null;
            for (Node node: grandparentNodes) {
                System.out.println(bestScore);

                if(bestScore == null) {
                    bestScore = node.getScore();
                } else if (!node.getColor() && node.getScore() < bestScore) {

                    bestScore = node.getScore();
                    bestBoard = node.getLeafBoard();
                } else if (node.getColor() && node.getScore() > bestScore) {

                    bestScore = node.getScore();
                    bestBoard = node.getLeafBoard();
                }
            }

            if(bestBoard != null) {
                bestBoard.printBoard();
            }
            System.out.println("yo" + bestScore);

        }

            ArrayList<Board> new_children = board.checkMovesForAll(false, board);

            int greatestDifference = 0;
            Board bestBoard = board;
            for (Board current_board : new_children) {
                //current_board.printBoard();
                int whiteScore = current_board.calculateScoreForOnePlayer(pieceAmount, true, current_board);
                int blackScore = current_board.calculateScoreForOnePlayer(pieceAmount, false, current_board);
                //System.out.println("The score for black/up (false) is: " + blackScore);
                //System.out.println("The score for white/down (true) is: " + whiteScore);
                int scoreDifference = blackScore - whiteScore;
                //System.out.println("The score difference is: " + scoreDifference);

                if (scoreDifference > greatestDifference) {
                    greatestDifference = scoreDifference;
                    board = current_board;
                }
            }

            delay(1500);
            wipeScreen();
            board.printBoard();
        System.out.println(board.getWinner());

        }



    private static ArrayList<Node> generateNextLayer(Iterable<Node> nodes, boolean color) {
        ArrayList<Node> children = new ArrayList<>();

        for (Node node: nodes) {
            Board current_board = node.getLeafBoard();
            List<Board> boards = current_board.checkMovesForAll(color, current_board);
            for( Board board: boards) {
                children.add(new Node(node, board, null, color));
            }
        }
        return children;
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
        for(int i = 0; i < 50; i++) {
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


    public static void welcomeScreen() {
        wipeScreen();
        System.out.println("Hello! Welcome to our Simple Chess game.");
        System.out.println("You play white (bottom), the computer plays black (top).");
        System.out.println("");
        delay(4500);
        wipeScreen();
        System.out.println("How to move?");
        System.out.println("Please enter the start and end position of the piece you want to move.");
        System.out.println("For example, to move a piece (the pawn of course) from c2 to c4, " +
        "you simply have to type 'c2c4' without the quote marks.");
        System.out.println("");
        delay(12000);
        wipeScreen();
        System.out.println("Every piece is indicated by their color (B for black and W for white) " +
        "followed by a number; 1 for pawn, 5 for rook and 9 for king.");
        System.out.println("");
        delay(7250);
        wipeScreen();
        System.out.println("Have fun and try to beat the cpu!");
        delay(2500);
        wipeScreen();
    }

}