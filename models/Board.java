package models;

import java.util.List;

public class Board {
    List<Space> board;

    public List<Space> getBoard() {
        return board;
    }

    public Space getSpecificSpace(String spaceName){
        for(Space space : board){
            if(space.getName().equals(spaceName)){
                return space;
            }
        }
        return null;
    }

    public Board(List<Space> board) {
        this.board = board;
    }
}
