package battleship.exceptions;

public final class IllegalShipPlacementException extends Exception {
    public IllegalShipPlacementException() {
        super("Error! You placed it too close to another one.");
    }
}
