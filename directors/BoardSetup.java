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


        //make blueColored Back Row
        Piece blueBack = new Piece("blue r1", true);
        result.add(blueBack);
        blueBack = new Piece("blue kn1", true);
        result.add(blueBack);
        blueBack = new Piece("blue b1", true);
        result.add(blueBack);
        blueBack = new Piece("blue queen", true);
        result.add(blueBack);
        blueBack = new Piece("blue king", true);
        result.add(blueBack);
        blueBack = new Piece("blue b2", true);
        result.add(blueBack);
        blueBack = new Piece("blue kn2", true);
        result.add(blueBack);
        blueBack = new Piece("blue r2", true);
        result.add(blueBack);

        //get blueColoredPawns
        for(int i = 1; i < 9; i++){
            Piece piece = new Piece("blue p"+i, true);
            result.add(piece);
        }

        //make red colored pawns
        for(int i = 1; i < 9; i++){
            Piece piece = new Piece("red p"+i, false);
            result.add(piece);
        }

        //make red colored back row
        Piece redBack = new Piece("red r1", false);
        result.add(redBack);
        redBack = new Piece("red kn1", false);
        result.add(redBack);
        redBack = new Piece("red b1", false);
        result.add(redBack);
        redBack = new Piece("red king", false);
        result.add(redBack);
        redBack = new Piece("red queen", false);
        result.add(redBack);
        redBack = new Piece("red b2", false);
        result.add(redBack);
        redBack = new Piece("red kn2", false);
        result.add(redBack);
        redBack = new Piece("red r2", false);
        result.add(redBack);
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
