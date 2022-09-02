package models;

public class Piece {
    private String name;
    private boolean blue;

    public String getName() {
        return name;
    }

    public boolean isBlue() {
        return blue;
    }

    public Piece(String name, boolean lightOrDark) {
        this.name = name;
        this.blue = lightOrDark;
    }
}
