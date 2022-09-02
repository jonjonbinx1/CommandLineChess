package directors;

import models.Board;
import models.Piece;
import models.Space;

public class PieceMovement {
    public boolean movePiece(Piece piece,Space currentSpace, String spaceToMoveTo, Board board){
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
        }else if(pieceName.contains("b")){
            canMove = moveBishop(piece, row, column, board, currentSpace);
        }
        return canMove;
    }

    private boolean movePawn(Piece piece, int row, int column, Board board, Space currentSpace){
        boolean moved = false;
        int currentRow = Integer.parseInt(currentSpace.getName().charAt(0) + "");
        int currentColumn = Integer.parseInt(currentSpace.getName().charAt(2) + "");
        if(piece.isBlue()){
            if((row == currentRow + 1 && currentColumn == column) || (row == currentRow + 2 && currentColumn == column && currentRow == 1)){
                if(board.getSpecificSpace((row + ":" + column)).getPiece() == null){
                    moved = true;
                    board.setSpecificSpace(piece, row, column);
                    board.setSpecificSpace(null, currentRow, currentColumn);
                }
            }else if(row == currentRow + 1 && (currentColumn == column + 1 || currentColumn == column - 1)){
                if(board.getSpecificSpace((row + ":" + column)).getPiece() != null){
                    if(board.getSpecificSpace((row + ":" + column)).getPiece().isBlue() != piece.isBlue()){
                        moved = true;
                        board.setSpecificSpace(piece, row, column);
                        board.setSpecificSpace(null, currentRow, currentColumn);
                    }
                }
            }
        }else{
            if((row == currentRow - 1 && currentColumn == column) || (row == currentRow - 2 && currentColumn == column && currentRow == 6)){
                if(board.getSpecificSpace((row + ":" + column)).getPiece() == null){
                    moved = true;
                    board.setSpecificSpace(piece, row, column);
                    board.setSpecificSpace(null, currentRow, currentColumn);
                }
            }else if(row == currentRow - 1 && (currentColumn == column + 1 || currentColumn == column - 1)){
                if(board.getSpecificSpace((row + ":" + column)).getPiece() != null){
                    if(board.getSpecificSpace((row + ":" + column)).getPiece().isBlue() != piece.isBlue()){
                        moved = true;
                        board.setSpecificSpace(piece, row, column);
                        board.setSpecificSpace(null, currentRow, currentColumn);
                    }
                }
            }
        }
        return moved;
    }

    private boolean moveBishop(Piece piece, int row, int column, Board board, Space currentSpace){
        boolean moved = false;
        int currentRow = Integer.parseInt(currentSpace.getName().charAt(0) + "");
        int currentColumn = Integer.parseInt(currentSpace.getName().charAt(2) + "");

        int amountOfRowsMoved = Math.abs((row - currentRow));
        int amountofColumnsMoved = Math.abs((column - currentColumn));

        if(amountOfRowsMoved == amountofColumnsMoved){
            //check the diagonal that the bishop is on to ensure there is no piece blocking the path
            //check the final spot to see if there is an enemy in the way
            //if so replace enemy with the bishop

            int rowDirection;
            int columnDirection;

            int startRow = currentRow;
            int startColumn = currentColumn;

            int endRow = row;

            if(currentRow < row) {
                rowDirection = 1;
            }else{
                rowDirection = -1;
            }

            if(currentColumn < column){
                columnDirection = 1;
            }else{
                columnDirection = -1;
            }

            boolean shouldBlock = false;

            while ( startRow!= endRow){
                String checkSpace = startRow +":" + startColumn;
                if(checkSpace.equals(currentSpace.getName())){
                    startRow += rowDirection;
                    startColumn += columnDirection;
                    continue;
                } else if (board.getSpecificSpace(checkSpace).getPiece() != null){
                    shouldBlock = true;
                    System.out.println("Piece : " + board.getSpecificSpace(checkSpace).getPiece().getName() + " is blocking this move");
                }
                startRow += rowDirection;
                startColumn += columnDirection;
            }

            if(board.getSpecificSpace((row + ":" + column)).getPiece() != null  && !shouldBlock){
                if(board.getSpecificSpace((row + ":" + column)).getPiece().isBlue() != piece.isBlue()){
                    moved = true;
                    board.setSpecificSpace(piece, row, column);
                    board.setSpecificSpace(null, currentRow, currentColumn);
                }else{
                    System.out.println("Piece : " + board.getSpecificSpace((row + ":" + column)).getPiece().getName() + " is blocking this move");
                }
            }else if (!shouldBlock){
                moved = true;
                board.setSpecificSpace(piece, row, column);
                board.setSpecificSpace(null, currentRow, currentColumn);
            }
        }
        return moved;
    }
}
