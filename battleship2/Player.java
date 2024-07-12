package battleship2;

import battleship2.exceptions.IllegalShipPlacementException;
import battleship2.exceptions.WrongShipLengthException;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public final class Player {
    private final String name;
    private final Coordinate[][] board;
    private int health;

    public boolean hasFloatingShips() {
        return health > 0;
    }

    public Player(String name, int size) {
        this.name = name;
        this.board = new Coordinate[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = new Coordinate(j, i, CoordinateState.SEA);
            }
        }
    }

    public ShotResult shootAt(Coordinate coord) {
        int yTarget = coord.getY();
        int xTarget = coord.getX();
        CoordinateState target = board[yTarget][xTarget].getCoordValue();
        if (target == CoordinateState.SHIP || target == CoordinateState.HIT) {
            if (target == CoordinateState.SHIP) {
                health--;
            }
            board[yTarget][xTarget].setCoordValue(CoordinateState.HIT);
            if (health == 0) {
                return ShotResult.GAME_OVER;
            } else if (isShipSunk(coord)) {
                return ShotResult.SANK;
            } else {
                return ShotResult.DAMAGED;
            }
        } else {
            board[yTarget][xTarget].setCoordValue(CoordinateState.MISS);
            return ShotResult.MISSED;
        }
    }

    public void placeShip(Ship ship, Coordinate[] coordinates) throws IllegalShipPlacementException, WrongShipLengthException {
        if (ship.getSize() != coordinates.length) {
            throw new WrongShipLengthException("The ship's length does not match the number of coordinates");
        }
        for (Coordinate coord: coordinates) {
            if (hasNeighbourShip(coord)) throw new IllegalShipPlacementException();
        }
        for (Coordinate coord: coordinates) {
            board[coord.getY()][coord.getX()].setCoordValue(CoordinateState.SHIP);
        }
        health += ship.getSize();
    }

    public String display(DisplayOption option) {
        StringBuilder sb = new StringBuilder();
        sb.append("  1 2 3 4 5 6 7 8 9 10\n");
        for (int i = 0; i < board.length; i++) {
            sb.append((char) ('A' + i)).append(' ');
            for (int j = 0; j < board[i].length; j++) {
                if (option == DisplayOption.OPEN) {
                    sb.append(board[i][j].getCoordValue().getCharValue()).append(' ');
                } else {
                    CoordinateState state = board[i][j].getCoordValue() == CoordinateState.SHIP ? CoordinateState.SEA : board[i][j].getCoordValue();
                    sb.append(state.getCharValue()).append(' ');
                }
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    private boolean isShipSunk(Coordinate coord) {
        Queue<Coordinate> queue = new LinkedList<>();
        Set<Coordinate> visited = new HashSet<>();
        queue.add(coord);
        while (!queue.isEmpty()) {
            Coordinate[] neighbours = findNeighbours(queue.poll());
            for (Coordinate neighbour : neighbours) {
                if (visited.contains(neighbour)) {
                    continue;
                }
                visited.add(neighbour);
                switch (neighbour.getCoordValue()) {
                    case SHIP -> { return false; }
                    case SEA, MISS -> {}
                    case HIT -> queue.add(neighbour);
                }
            }
        }
        return true;
    }

    private Coordinate[] findNeighbours(Coordinate coord) {
        int yStart = Math.max(0, coord.getY() - 1);
        int xStart = Math.max(0, coord.getX() - 1);
        int yEnd = Math.min(board.length - 1, coord.getY() + 1);
        int xEnd = Math.min(board.length - 1, coord.getX() + 1);

        Coordinate[] outArray = new Coordinate[(yEnd - yStart + 1) * (xEnd - xStart + 1)];

        int index = 0;
        for (int y = yStart; y <= yEnd; y++) {
            for (int x = xStart; x <= xEnd; x++) {
                outArray[index++] = board[y][x];
            }
        }
        return outArray;
    }

    private boolean hasNeighbourShip(Coordinate coord) {
        for (Coordinate field : findNeighbours(coord)) {
            if (field.getCoordValue() == CoordinateState.SHIP) return true;
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public enum ShotResult {
        MISSED, DAMAGED, SANK, GAME_OVER
    }

    public enum DisplayOption {
        OPEN, FOW
    }
}
