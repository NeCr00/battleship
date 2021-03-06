/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naumaxia;

import java.util.ArrayList;
import static naumaxia.Tile.Type.SEA;
import static naumaxia.Tile.Type.SHIP;

/**
 *
 * @author giann
 */
public abstract class Ship {

    // Variables
    private int shipSize; //megethos ploiou
    private int[] cellStart = new int[2]; // to arxiko keli toy ploiou
    private char direction; // h kateuthinsi tou ploiou

    //constructor
    public Ship(int shipSize, char direction, int[] cellStart) {
        this.shipSize = shipSize;
        this.direction = direction;
        this.cellStart = cellStart;
    }

    // setter & getter 
    public int getShipSize() {
        return shipSize;
    }

    public void setShipSize(int shipSize) {
        this.shipSize = shipSize;
    }

    public int[] getCellStart() {
        return cellStart;
    }

    public void setCellStart(int[] cellStart) {
        this.cellStart = cellStart;
    }

    public char getDirection() {
        return direction;
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }

   // methodos pou dexetai enan disdiastato pinaka kai ena boolean msg kai topotheti to ploio . To msg deixnei ean tha energopoiithoun ta exception  
    Boolean PlaceShip(Board board, Boolean msg) throws OverlapTilesException, OversizeException, AdjacentTilesException { 
        boolean success = true;
        ArrayList<Tile> b = new ArrayList();
        Tile[][] pin = new Tile[10][10];
        pin = board.getPin();
        int k = 0, l = 0;

        if (direction == 'H' && cellStart[1] + shipSize > pin.length - 1) { //elegxei an einai ektos pinaka i topothetisi tou ploiou
            success = false;
            if (msg) {
                throw new OversizeException();
            }
        } else if (direction == 'V' && cellStart[0] + shipSize > pin.length - 1) { //elegxei an einai ektos pinaka i topothetisi tou ploiou
            success = false;
            if (msg) {
                throw new OversizeException();
            }
        } else {
            if (pin[cellStart[0]][cellStart[1]].getType() != SEA) { //elegxei an to arxiko keli exei idi ploio 
                success = false;
                if (msg) {
                    throw new OverlapTilesException();
                }
            }

            int i = 0;
            while (i < shipSize) {   //loop ,pou gia kathe keli pairnei ta geitonika toy tile me tin synartisi getAdjacementTiles kai elegxei an paraviazei tis synthikes topothetisis

                if (direction == 'H') {
                    l = i;
                } else {
                    k = i;
                }

                b = board.getAdjacentTiles(pin[cellStart[0] + k][cellStart[1] + l], pin);
                for (int j = 0; j < b.size(); j++) {
                    if (b.get(j).getType() != SEA) {
                        success = false;
                        if (msg) {
                            throw new AdjacentTilesException();
                        }
                        break;
                    }

                }
                i++;
            }
        }

        if (success) {  //elegxei an paraviastike kapoia synthiki apo tin parapano kai analoga topothtetei to ploio 
            for (int i = 0; i <= shipSize - 1; i++) {
                if (direction == 'H') {
                    pin[cellStart[0]][cellStart[1] + i].setType(SHIP);
                } else {
                    pin[cellStart[0] + i][cellStart[1]].setType(SHIP);
                }

            }
            board.setPin(pin); //gyrnaei ton pinaka kai ton thetei ws kainourgio
        }
        return success;

    }
}
