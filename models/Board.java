package models;

import java.util.List;

public class Board {
    List<List<Space>> board;

    public List<List<Space>> getBoard() {
        return board;
    }

    public Space getSpecificSpace(String spaceName){
        for(List<Space> row : board){
            for(Space space : row) {
                if (space.getName().equals(spaceName)) {
                    return space;
                }
            }
        }
        return null;
    }

    public void setSpecificSpace(Piece piece, int row, int column){
        board.get(row).set(column , new Space((row + ":" + column) , piece));
    }

    public Board(List<List<Space>> board) {
        this.board = board;
    }
}
