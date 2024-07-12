package battleship.exceptions;

public final class WrongShipLengthException extends Exception {
    public WrongShipLengthException(String name) {
        super("Error! Wrong length of the " + name + "!");
    }
}
