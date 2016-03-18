package com.company;

import java.util.*;

public class Main {

    private static int BOARD_SIZE = 8;                  // size of a chess board
    private static final int AMOUNT_OF_PIECES = 22;     // 8x2 pawns, 2x2 rooks, 1x2 kings


    // declare new object from the Board class
    private static Board board = new Board(BOARD_SIZE, AMOUNT_OF_PIECES);

    // declare new LinkedList to keep track of all board configurations
    //private static LinkedList<Board> queue = new LinkedList<>();


    public static void main(String[] args) {
        board.addPieces();          // adds the pieces to the board
        //queue.add(board);         // adds first board to the queue

        //System.out.println("The TEST board below is to test the score calculation:");
        //board.addTestBoard();     // adds the pieces in a specific way (to test score function)

        board.printBoard();         // prints the board

        // pieceAmount is used to know whether the game status is begingame, midgame or endgame
        int pieceAmount = AMOUNT_OF_PIECES;

        //calculateBestMoveForCPU(board, 22);
        /*
        // CALCULATING SCORE AI ALGORITHM SCORE TESTING
        // parameters are int pieceAmount, boolean color and Board board)
        int whiteScore = board.calculateScoreForOnePlayer(pieceAmount, true, board);
        int blackScore = board.calculateScoreForOnePlayer(pieceAmount, false, board);
        System.out.println("The score for black/up (false) is: " + blackScore);
        System.out.println("The score for white/down (true) is: " + whiteScore);
        */

        //main game play loop
        while (board.gameIsNotFinished()) {
            board = userMove();
            delay(1000);
            wipeScreen();
            board.printBoard();


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
            }

            HashSet<Node> parentNodes = new HashSet<>();

            for (Node node : nodes) {
                parentNodes.add(node.getParent());
                Integer score = node.getParent().getScore();
                if (score == null) {
                    node.getParent().setScore(node.getScore());
                    } else if(!node.getColor() && node.getScore() < score) {
                    //blackScore < parent Node score -> vervangen;
                    node.getParent().setScore(score);
                    } else if(node.getColor() && node.getScore() > score) {
                    // whiteScore > parent Node score -> vervangen;
                    node.getParent().setScore(score);
                    }
                }


                System.out.println(parentNodes.size());

            /*
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
            */
            }
            System.out.println(board.getWinner());

        /*
        ArrayList<Board> new_children = board.checkMovesForAll(false, board);
        int greatestDifference = 0;
        Board bestBoard = board;
        for (int j = 0; j < new_children.size(); j++) {
            Board child = new_children.get(j);
            child.printBoard();
            int whiteScore = child.calculateScoreForOnePlayer(pieceAmount, true, child);
            int blackScore = child.calculateScoreForOnePlayer(pieceAmount, false, child);
            System.out.println("The score for black/up (false) is: " + blackScore);
            System.out.println("The score for white/down (true) is: " + whiteScore);
            int scoreDifference = blackScore-whiteScore;
            System.out.println("The score difference is: " + scoreDifference);

            if(scoreDifference > greatestDifference) {
                greatestDifference = scoreDifference;
                bestBoard = child;
            }

        }

        System.out.println("The greatest difference is: " + greatestDifference);
        System.out.println("The best move is:");
        bestBoard.printBoard();
        */
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


    private static void calculateBestMoveForCPU(Board input_board, int piece_amount) {
        // how deep in the search tree does the cpu have to look
        // i.e. deepness of 1 is only the first possible moves and then the best score
        // deepness of 3 is all moves of cpu, all moves of human, all moves of cpu and then the best score
        int layerDeepnessOfMoves = 1;

        if(piece_amount <= 22 && piece_amount > 16) {
            layerDeepnessOfMoves = 1;
        } else if(piece_amount <= 16 && piece_amount > 10) {
            layerDeepnessOfMoves = 3;
        } else if(piece_amount <= 10 && piece_amount > 5) {
            layerDeepnessOfMoves = 5;
        } else if(piece_amount <= 5 && piece_amount > 4) {
            layerDeepnessOfMoves = 7;
        } else if(piece_amount <= 4 && piece_amount > 0) {
            layerDeepnessOfMoves = 9;
        }

        if(layerDeepnessOfMoves == 1) {
            ArrayList<Board> new_children = input_board.checkMovesForAll(false, input_board);
            // en dan score berekenen en het board met de beste score kiezen


            for(int z = 0; z < new_children.size(); z++) {
                //new_children.get(z).printBoard();
            }

            for(int k = 0; k < new_children.size(); k++) {
                Board current_board = new_children.get(k);

                System.out.println("test");
                current_board.printBoard();
            }
        }

        if(layerDeepnessOfMoves == 3) {
            ArrayList<Board> black1_new_children = input_board.checkMovesForAll(false, input_board);
            System.out.println("test1");

            ArrayList<Board> white_new_children = new ArrayList<>();
            for(int i = 0; i < black1_new_children.size(); i++) {
                Board current_board = black1_new_children.get(i);
                white_new_children.addAll(current_board.checkMovesForAll(true, current_board));
            }

            System.out.println("test2");

            ArrayList<Board> black2_new_children = new ArrayList<>();
            for(int j = 0; j < white_new_children.size(); j++) {
                Board current_board = white_new_children.get(j);
                black2_new_children.addAll(current_board.checkMovesForAll(true, current_board));
            }

            System.out.println("test3");

            for(int k = 0; k < black2_new_children.size(); k++) {
                Board current_board = black2_new_children.get(k);
                current_board.printBoard();
            }
        }
    }
}