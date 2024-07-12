package battleship2;

public final class Ship {
    private final String name;
    private final int length;

    public Ship(String name, int length) {
        this.name = name;
        this.length = length;
    }

    public String getName() {
        return this.name;
    }

    public int getSize() {
        return this.length;
    }

    public String toString() {
        return name + ";" + length;
    }
}
