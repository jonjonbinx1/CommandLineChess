package directors;

import models.Board;
import models.Piece;
import models.Space;

import java.util.ArrayList;
import java.util.List;

public class BoardSetup {
    public Board initialSetup(){
        List<Piece> pieces = createPieces();
        Board result = createSpaces(pieces);
//        Board result = new Board(spaces);
        return result;
    }

    private List<Piece> createPieces(){
        List<Piece> result = new ArrayList<>();


        //make lightColored Back Row
        Piece lightBack = new Piece("light r1", true);
        result.add(lightBack);
        lightBack = new Piece("light kn1", true);
        result.add(lightBack);
        lightBack = new Piece("light b1", true);
        result.add(lightBack);
        lightBack = new Piece("light queen", true);
        result.add(lightBack);
        lightBack = new Piece("light king", true);
        result.add(lightBack);
        lightBack = new Piece("light b2", true);
        result.add(lightBack);
        lightBack = new Piece("light kn2", true);
        result.add(lightBack);
        lightBack = new Piece("light r2", true);
        result.add(lightBack);

        //get lightColoredPawns
        for(int i = 1; i < 9; i++){
            Piece piece = new Piece("light p"+i, true);
            result.add(piece);
        }

        //make dark colored pawns
        for(int i = 1; i < 9; i++){
            Piece piece = new Piece("dark p"+i, false);
            result.add(piece);
        }

        //make dark colored back row
        Piece darkBack = new Piece("dark r1", false);
        result.add(darkBack);
        darkBack = new Piece("dark kn1", false);
        result.add(darkBack);
        darkBack = new Piece("dark b1", false);
        result.add(darkBack);
        darkBack = new Piece("dark king", false);
        result.add(darkBack);
        darkBack = new Piece("dark queen", false);
        result.add(darkBack);
        darkBack = new Piece("dark b2", false);
        result.add(darkBack);
        darkBack = new Piece("dark kn2", false);
        result.add(darkBack);
        darkBack = new Piece("dark r2", false);
        result.add(darkBack);
        return result;
    }

    private Board createSpaces(List<Piece> pieces){
        List<Space> row = new ArrayList<>();
        List<List<Space>> result = new ArrayList<>();
        int pieceToGet = 0;
        Piece pieceToAdd = null;
        //columns loop
        for(int i = 0; i < 8; i++){
            //rows loop
            for(int j = 0; j < 8; j++){
                String spaceName= i + ":" + j;
                if(i == 0 | i == 1 | i == 6 | i ==7){
                    //get the proper Piece
                    pieceToAdd = pieces.get(pieceToGet);
                    pieceToGet++;
                }else{
                    pieceToAdd = null;
                }
                row.add(new Space(spaceName, pieceToAdd));
            }
            result.add(row);
            row = new ArrayList<>();
        }
        Board resultBoard = new Board(result);
        return resultBoard;
    }
}
