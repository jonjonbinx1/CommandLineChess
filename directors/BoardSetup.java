package directors;

import models.Board;
import models.Piece;
import models.Space;

import java.util.ArrayList;
import java.util.List;

public class BoardSetup {
    public Board initialSetup(){
        List<Piece> pieces = createPieces();
        List<Space> spaces = createSpaces(pieces);
        Board result = new Board(spaces);
        return result;
    }

    private List<Piece> createPieces(){
        List<Piece> result = new ArrayList<>();


        //make lightColored Back Row
        Piece lightBack = new Piece("rook1", true);
        result.add(lightBack);
        lightBack = new Piece("knight1", true);
        result.add(lightBack);
        lightBack = new Piece("bishop1", true);
        result.add(lightBack);
        lightBack = new Piece("queen", true);
        result.add(lightBack);
        lightBack = new Piece("king", true);
        result.add(lightBack);
        lightBack = new Piece("bishop2", true);
        result.add(lightBack);
        lightBack = new Piece("knight2", true);
        result.add(lightBack);
        lightBack = new Piece("rook2", true);
        result.add(lightBack);

        //get lightColoredPawns
        for(int i = 1; i < 9; i++){
            Piece piece = new Piece("pawn"+i, true);
            result.add(piece);
        }

        //make dark colored pawns
        for(int i = 1; i < 9; i++){
            Piece piece = new Piece("pawn"+i, false);
            result.add(piece);
        }

        //make dark colored back row
        Piece darkBack = new Piece("rook1", false);
        result.add(darkBack);
        darkBack = new Piece("knight1", false);
        result.add(darkBack);
        darkBack = new Piece("bishop1", false);
        result.add(darkBack);
        darkBack = new Piece("king", false);
        result.add(darkBack);
        darkBack = new Piece("queen", false);
        result.add(darkBack);
        darkBack = new Piece("bishop2", false);
        result.add(darkBack);
        darkBack = new Piece("knight2", false);
        result.add(darkBack);
        darkBack = new Piece("rook2", false);
        result.add(darkBack);
        return result;
    }

    private List<Space> createSpaces(List<Piece> pieces){
        List<Space> result = new ArrayList<>();
        int pieceToGet = 0;
        Piece pieceToAdd = null;
        //columns loop
        for(int i = 1; i < 9; i++){
            //rows loop
            for(int j = 1; j < 9; j++){
                String spaceName= i + ":" + j;
                if(i == 1 | i == 2 | i == 7 | i ==8){
                    //get the proper Piece
                    pieceToAdd = pieces.get(pieceToGet);
                    pieceToGet++;
                }else{
                    pieceToAdd = null;
                }
                result.add(new Space(spaceName, pieceToAdd));
            }
        }
        return result;
    }
}
