package battleship2;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputReader {
    static Scanner scanner = new Scanner(System.in);

    public static Coordinate[] readCoordinates() {
       String[] arrStartEnd = scanner.nextLine().split(" ");
       if (arrStartEnd.length != 2) {
           throw new InputMismatchException("Invalid input format");
       }
       if ((matchingX(arrStartEnd[0], arrStartEnd[1]) && !matchingY(arrStartEnd[0], arrStartEnd[1])) ||
              matchingY(arrStartEnd[0], arrStartEnd[1]) && !matchingX(arrStartEnd[0], arrStartEnd[1])) {
           sortCoordinateStrings(arrStartEnd);
           return generateCoordinateArray(arrStartEnd);
       } else {
           throw new InputMismatchException("Error wrong ship location!");
       }
    }

    public static void pressEnter() {
        scanner.nextLine();
    }

    public static Coordinate readCoordinate() {
        String coordinate = scanner.nextLine();
        return new Coordinate(coordinate);
    }

    private static boolean matchingX(String firstCoordinateString, String secondCoordinateString) {
        return firstCoordinateString.substring(0, 1).equals(secondCoordinateString.substring(0, 1));
    }

    private static boolean matchingY(String firstCoordinateString, String secondCoordinateString) {
        return firstCoordinateString.substring(1).equals(secondCoordinateString.substring(1));
    }

    private static void sortCoordinateStrings(String[] coordinates) {
        String coord0 = coordinates[0];
        String coord1 = coordinates[1];

        if (coordinates[0].charAt(0) > coordinates[1].charAt(0)) {
            coordinates[0] = coord1;
            coordinates[1] = coord0;
        } else if (coordinates[0].charAt(0) == coordinates[1].charAt(0)){
            int coord0Int = Integer.parseInt(coordinates[0].substring(1));
            int coord1Int = Integer.parseInt(coordinates[1].substring(1));
            if (coord0Int > coord1Int) {
                coordinates[0] = coord1;
                coordinates[1] = coord0;
            }
        }
    }

    private static Coordinate[] generateCoordinateArray(String[] startEnd) {
        Coordinate start = new Coordinate(startEnd[0]);
        Coordinate end = new Coordinate(startEnd[1]);
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
        throw new IllegalArgumentException();
    }
}
