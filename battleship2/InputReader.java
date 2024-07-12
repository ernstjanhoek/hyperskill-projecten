package battleship;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public final class InputReader {
    static Scanner scanner = new Scanner(System.in);

    public static Coordinate[] readCoordinates() throws IllegalArgumentException {
        String[] inputArray = scanner.nextLine().split(" ");
        Coordinate[] arrStartEnd = new Coordinate[inputArray.length];
        arrStartEnd[0] = new Coordinate(inputArray[0]);
        arrStartEnd[1] = new Coordinate(inputArray[1]);
        Arrays.sort(arrStartEnd);

        if (arrStartEnd.length != 2) {
            throw new InputMismatchException("Invalid input format");
        }
        return generateCoordinateArray(arrStartEnd);
    }

    public static void pressEnter() {
        scanner.nextLine();
    }

    public static Coordinate readCoordinate() {
        String coordinate = scanner.nextLine();
        return new Coordinate(coordinate);
    }

    private static Coordinate[] generateCoordinateArray(Coordinate[] startEnd) throws IllegalArgumentException {
        Coordinate start = startEnd[0];
        Coordinate end = startEnd[1];
        if (start.getX() != end.getX() && start.getY() == end.getY()) {
            Coordinate[] array = new Coordinate[end.getX() - start.getX() + 1];
            array[0] = start;
            array[array.length-1] = end;
            for (int i = 0; i < array.length; i++) {
                array[i] = new Coordinate(i + start.getX(), start.getY());
            }
            return array;
        } else if (start.getY() != end.getY() && start.getX() == end.getX()) {
            Coordinate[] array = new Coordinate[end.getY() - start.getY() + 1];
            array[0] = start;
            array[array.length-1] = end;
            for (int i = 0; i < array.length; i++) {
                array[i] = new Coordinate(start.getX(), i + start.getY());
            }
            return array;
        }
        throw new IllegalArgumentException("Wrong ship location");
    }
}
