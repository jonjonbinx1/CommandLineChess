import directors.BoardSetup;
import models.Board;
import models.Space;

import java.util.List;

public class CommandLineChess {
    public static void main(String[] args) {
        BoardSetup boardSetup = new BoardSetup();
        Board gameBoard = boardSetup.initialSetup();

        List<Space> spaces = gameBoard.getBoard();
        for(Space space : spaces){
            String pieceName = null;
            if(space.getPiece() != null){
                pieceName = space.getPiece().getName();
            }else{
                pieceName = "none";
            }
            System.out.println(space.getName() + " piece on it is " + pieceName);
        }
    }
}
