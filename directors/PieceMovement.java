package directors;

import models.Board;
import models.Piece;
import models.Space;

public class PieceMovement {
    public boolean movePiece(Piece piece,Space currentSpace, String spaceToMoveTo, Board board){
        System.out.println("attempting to move " + piece.getName());
        boolean canMove = false;
        int row = -1;
        int column = -1;
        String[] rowAndColumn = spaceToMoveTo.split(":");
        try {
            row = Integer.parseInt(rowAndColumn[0]);
            column = Integer.parseInt(rowAndColumn[1].replace(":", ""));
        }catch (Exception e){
            System.out.println(e.getMessage());
            //TODO handle this, likely before we get to this method
        }
        String pieceName = piece.getName();
        if(pieceName.contains("p")){
            canMove = movePawn(piece, row, column, board, currentSpace);
        }
        return canMove;
    }

    private boolean movePawn(Piece piece, int row, int column, Board board, Space currentSpace){
        boolean moved = false;
        int currentRow = Integer.parseInt(currentSpace.getName().charAt(0) + "");
        int currentColumn = Integer.parseInt(currentSpace.getName().charAt(2) + "");
        if(piece.isLightOrDark()){
            if(row > currentRow && currentColumn == column){
                if(board.getSpecificSpace((row + ":" + column)).getPiece() == null){
                    moved = true;
                    board.setSpecificSpace(piece, row, column);
                    board.setSpecificSpace(null, currentRow, currentColumn);
                }
            }
        }
        return moved;
    }
}
