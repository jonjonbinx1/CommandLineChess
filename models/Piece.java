package models;

public class Piece {
    private String name;
    private boolean lightOrDark;

    public String getName() {
        return name;
    }

    public boolean isLightOrDark() {
        return lightOrDark;
    }

    public Piece(String name, boolean lightOrDark) {
        this.name = name;
        this.lightOrDark = lightOrDark;
    }
}
