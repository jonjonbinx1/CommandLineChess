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


//        Board board = gameBoard.getBoard();
        printBoard(gameBoard);

        System.out.println("what piece would you like to move");
        String pieceName = userInput.nextLine();
        Space currentSpace = null;
        Piece currentPiece = null;
        //TODO validate user input
        for(List<Space> row : gameBoard.getBoard()){
            for(Space space : row){
                if(space.getPiece() != null) {
                    if (pieceName.equals(space.getPiece().getName())) {
                        currentSpace = space;
                        currentPiece = currentSpace.getPiece();
                    }
                }
            }
        }

        System.out.println("where would you like to move this piece");
        String spaceToMoveTo = userInput.nextLine();
        System.out.println("attempting to move piece " + currentPiece.getName());
       System.out.println(pieceMovement.movePiece(currentPiece, currentSpace, spaceToMoveTo, gameBoard));

        printBoard(gameBoard);
    }

    private static void printBoard(Board board){
        for(int newLines = 0; newLines < 3; newLines++) {
            System.out.println();
        }
        for(List<Space> row : board.getBoard()){
            for (Space space : row) {
                String pieceName = null;
                if (space.getPiece() != null) {
                    pieceName = space.getPiece().getName();
                } else {
                    pieceName = "none";
                }
                System.out.printf("%-20s" , space.getName() + "/" +pieceName + "   ");
            }
            for(int newLines = 0; newLines < 3; newLines++) {
                System.out.println();
            }
        }
    }
}
