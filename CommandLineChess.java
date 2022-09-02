import directors.BoardSetup;
import directors.PieceMovement;
import models.Board;
import models.Piece;
import models.Space;

import java.util.List;
import java.util.Scanner;

public class CommandLineChess {
    public static void main(String[] args) {
        BoardSetup boardSetup = new BoardSetup();
        Board gameBoard = boardSetup.initialSetup();

        Scanner userInput = new Scanner(System.in);
        PieceMovement pieceMovement = new PieceMovement();

        boolean bluesTurn = true;
        boolean gameWon = false;

        while (!gameWon){
            bluesTurn = playersTurn(gameBoard, userInput, pieceMovement, bluesTurn);
        }
    }

    private static void printBoard(Board board){
        for(int newLines = 0; newLines < 3; newLines++) {
            System.out.println();
        }
        for(List<Space> row : board.getBoard()){
            for (Space space : row) {
                String color = null;
                String colorReset = "\u001B[0m";
                String pieceName = null;
                if (space.getPiece() != null) {
                    pieceName = space.getPiece().getName();
                    if(space.getPiece().isBlue()){
                        color = "\u001B[36m";
                        pieceName = pieceName;
                    }else{
                        color = "\033[1;31m";
                        //black ansi "\u001B[30m"
                        pieceName = pieceName;
                    }
                } else {
                    pieceName = "none";
                }
                if(color != null) {
                    System.out.print(color);
                }
                System.out.printf("%-20s" , space.getName() + "/" +pieceName + "   ");
                System.out.print(colorReset);
                color = null;
            }
            for(int newLines = 0; newLines < 3; newLines++) {
                System.out.println();
            }
        }
    }
    private static boolean playersTurn(Board gameBoard, Scanner userInput, PieceMovement pieceMovement, boolean bluesTurn){
        printBoard(gameBoard);
        boolean turnPassed = false;

        turnLoop:
        while(!turnPassed) {
            if (bluesTurn) {
                System.out.println( "\u001B[36m"+ "blues turn");
            } else {
                System.out.println("\033[1;31m" + "reds turn");
            }

            Space currentSpace = null;
            Piece currentPiece = null;

            //boolean to check if userInput is valid
            boolean inputValid = false;
            while (!inputValid) {
                System.out.println("what piece would you like to move");
                String pieceName = userInput.nextLine();

                if(pieceName.isEmpty()){
                    System.out.println("please enter a valid value (ie. blue p1)");
                    continue ;
                }

                if(pieceName.equals("print")){
                    printBoard(gameBoard);
                    continue turnLoop;
                }

                if (bluesTurn) {
                    if (pieceName.contains("dark")) {
                        System.out.println("please select one of your own pieces");
                        continue;
                    }
                } else {
                    if (pieceName.contains("light")) {
                        System.out.println("please select one of your own pieces");
                        continue;
                    }
                }


                //TODO validate user input
                for (List<Space> row : gameBoard.getBoard()) {
                    for (Space space : row) {
                        if (space.getPiece() != null) {
                            if (pieceName.equals(space.getPiece().getName())) {
                                currentSpace = space;
                                currentPiece = currentSpace.getPiece();
                            }
                        }
                    }
                }
                if (currentPiece == null) {
                    System.out.println("piece you wished to select is not on the board please select a piece on the board");
                } else {
                    inputValid = true;
                }
            }
            System.out.println("where would you like to move this piece");
            String spaceToMoveTo = userInput.nextLine();

            if(spaceToMoveTo.isEmpty()){
                System.out.println("Please enter a valid space value (ie. 2:3)");
                return bluesTurn;
            }
            if(Character.isDigit(spaceToMoveTo.charAt(0)) && Character.isDigit(spaceToMoveTo.charAt(2))){
                int row = Integer.parseInt(spaceToMoveTo.charAt(0) + "");
                int column = Integer.parseInt(spaceToMoveTo.charAt(2) + "");
                if(withinBounds(row,column)) {
                    if (spaceToMoveTo.charAt(1) == ':') {
                        if (spaceToMoveTo.length() == 3) {
                            turnPassed = pieceMovement.movePiece(currentPiece, currentSpace, spaceToMoveTo, gameBoard);
                        }
                    }
                }
            }
            if(!turnPassed){
                System.out.println("Could not move " + currentPiece.getName() + " to " + spaceToMoveTo + " please try another move");
            }
        }
        return !bluesTurn;
    }

    private static boolean withinBounds(int row, int column){
        boolean inbounds = true;
        if(row > 7 || row < 0 || column > 7 || column < 0){
            inbounds = false;
        }
        return inbounds;
    }
}
