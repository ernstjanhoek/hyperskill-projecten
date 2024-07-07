package battleship2.exceptions;

public class WrongShipLengthException extends Exception {
    public WrongShipLengthException(String name) {
        super("Error! Wrong length of the " + name + "!");
    }
}
