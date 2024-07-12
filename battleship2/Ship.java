package battleship;

import battleship.exceptions.WrongShipLengthException;

public final class Ship {
    private final String name;
    Coordinate[] coordinates;

    public Ship(String name, Coordinate[] coordinates) {
        this.name = name;
        this.coordinates = coordinates;
    }

    public void setShipCoordinates(Coordinate[] inputCoords) throws WrongShipLengthException {
        if (this.coordinates.length != inputCoords.length) {
            throw new WrongShipLengthException(name);
        }
       this.coordinates = inputCoords.clone();
    }

    public Coordinate[] getShipCoordinates() {
        return this.coordinates.clone();
    }

    public String getName() {
        return this.name;
    }

    public int getSize() {
        return this.coordinates.length;
    }

    public String toString() {
        return name + ";" + coordinates[0] + ";" + coordinates[coordinates.length-1];
    }
}
