package battleship;

public final class Coordinate implements Comparable<Coordinate> {
    private final int x;
    private final int y;
    private CoordinateState value;

    private static final String pattern = "[A-J]([1-9]|10)";

    public Coordinate(int x, int y) throws IllegalArgumentException {
        if (x < 0 || x > 9 || y < 0 || y > 9) {
            throw new IllegalArgumentException();
        }
        this.x = x;
        this.y = y;
    }

    public Coordinate(int x, int y, CoordinateState value) throws IllegalArgumentException {
        if (x < 0 || x > 9 || y < 0 || y > 9) {
            throw new IllegalArgumentException();
        }
        this.x = x;
        this.y = y;
        this.value = value;
    }

    public Coordinate(String coordinateString) throws IllegalArgumentException {
        if (!coordinateString.matches(pattern)) {
            throw new IllegalArgumentException("Error! You entered the wrong coordinates");
        }
        this.x = Integer.parseInt(coordinateString.substring(1)) - 1;
        this.y = coordinateString.charAt(0) - 'A';
    }

    public void setCoordValue(CoordinateState value) {
        this.value = value;
    }

    public CoordinateState getCoordValue() {
        return this.value;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return (char) (x + 'A') + "" + (y + 1);
    }

    @Override
    public int compareTo(Coordinate other) {
        if (y != other.getY()) {
            return Integer.compare(y, other.getY());
        } else {
            return Integer.compare(x, other.getX());
        }
    }
}
