package models;

public class Space {
    private String name;
    private Piece piece;

    public String getName() {
        return name;
    }

    public Piece getPiece() {
        return piece;
    }

    public Space(String name, Piece piece) {
        this.name = name;
        this.piece = piece;
    }
}
