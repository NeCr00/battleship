package naumaxia;

import static naumaxia.Tile.Type.SHIP;

public class Tile {

//enum    
    enum Type { 
        SEA("~"), SHIP("s"), HIT("x"), MISS("o");  // Named constants

        private final String symbol;      // Private variable

        private Type(String symbol) {
            this.symbol = symbol;
        }

    }

    //Variables  
    private int x, y;
    private Type type;

    public Tile(int x, int y, Type type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    //Getter and Setter
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    String draw(boolean hidden) { //methodos poy epistrefei to symbolo pou exei to tile
        if (hidden && type==SHIP) // Getter
        {
            return "~";
        } else {
           return type.symbol;
        }

    }

}
