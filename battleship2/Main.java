package battleship2;

import static battleship2.Player.DisplayOption.FOW;
import static battleship2.Player.DisplayOption.OPEN;
import java.util.InputMismatchException;

public final class Main {
    static Player player1;
    static Player player2;

    public static void main(String[] args) {
        player1 = new Player("Player 1", 10);
        player2 = new Player("Player 2", 10);
        boolean isPlayerOneActive = true;

        Ship[] ships = {
                new Ship("Aircraft Carrier", 5),
                new Ship("Battleship", 4),
                new Ship("Submarine", 3),
                new Ship("Cruiser", 3),
                new Ship("Destroyer", 3)
        };

        placeShips(ships, getActivePlayer(isPlayerOneActive));
        isPlayerOneActive = switchActivePlayer(isPlayerOneActive);

        placeShips(ships, getActivePlayer(isPlayerOneActive));
        isPlayerOneActive = switchActivePlayer(isPlayerOneActive);

        while (getActivePlayer(isPlayerOneActive).hasFloatingShips()) {
            System.out.println(getActivePlayer(!isPlayerOneActive).display(FOW));
            System.out.println("---------------------");
            System.out.println(getActivePlayer(isPlayerOneActive).display(OPEN));
            System.out.printf("%s, it's your turn:", getActivePlayer(isPlayerOneActive).getName());
            try {
                System.out.println(
                        switch (getActivePlayer(!isPlayerOneActive).shootAt(InputReader.readCoordinate())) {
                            case DAMAGED -> "You hit a ship!";
                            case MISSED -> "You missed!";
                            case SANK -> "You sank a ship!";
                            case GAME_OVER -> "You sank the last ship. You won. Congratulations!";
                        }
                );
                isPlayerOneActive = switchActivePlayer(isPlayerOneActive);
            } catch (InputMismatchException | IllegalArgumentException e) {
                System.out.printf("%s Try again: ", e.getMessage());
            }
        }
    }

    static boolean switchActivePlayer(boolean isPlayerOneActive) {
        System.out.println("Press enter and pass the move to another player");
        InputReader.pressEnter();
        return !isPlayerOneActive;
    }

    static Player getActivePlayer(boolean isPlayerOneActive) {
        if (isPlayerOneActive) return player1;
        else return player2;
    }

    private static void placeShips(Ship[] ships, Player player) {
        System.out.printf("%s, place your ships on the game field\n", player.getName());
        for (Ship ship : ships) {
            System.out.println(player.display(OPEN));
            System.out.printf("Enter the coordinates of the %s (%d cells)\n", ship.getName(), ship.getSize());
            boolean shipPlaced = false;
            while (!shipPlaced) {
                try {
                    shipPlaced = player.placeShip(ship, InputReader.readCoordinates());
                    System.out.println(player.display(OPEN));
                } catch (Exception e) {
                    System.out.printf("%s Try again:\n", e.getMessage());
                }
            }
        }
    }
}
