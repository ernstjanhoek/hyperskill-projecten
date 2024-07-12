package battleship2;

import battleship2.exceptions.IllegalShipPlacementException;
import battleship2.exceptions.WrongShipLengthException;

import static battleship2.Player.DisplayOption.FOW;
import static battleship2.Player.DisplayOption.OPEN;

import java.util.InputMismatchException;


public final class Main {
    static Player player1;
    static Player player2;
    static ActivePlayer activePlayer = ActivePlayer.PLAYER_ONE;

    public static void main(String[] args) {
        player1 = new Player("Player 1", 10);
        player2 = new Player("Player 2", 10);

        Ship[] ships = {
                new Ship("Aircraft Carrier", 5),
                new Ship("Battleship", 4),
                new Ship("Submarine", 3),
                new Ship("Cruiser", 3),
                new Ship("Destroyer", 3)
        };

        placeShips(ships.clone(), getActivePlayer());
        switchActivePlayer();

        placeShips(ships, getActivePlayer());
        switchActivePlayer();

        while (getActivePlayer().hasFloatingShips()) {
            System.out.println(getOtherPlayer().display(FOW));
            System.out.println("---------------------");
            System.out.println(getActivePlayer().display(OPEN));
            System.out.printf("%s, it's your turn:", getActivePlayer().getName());
            try {
                System.out.println(
                        switch (getOtherPlayer().shootAt(InputReader.readCoordinate())) {
                            case DAMAGED -> "You hit a ship!";
                            case MISSED -> "You missed!";
                            case SANK -> "You sank a ship!";
                            case GAME_OVER -> "You sank the last ship. You won. Congratulations!";
                        }
                );
                switchActivePlayer();
            } catch (InputMismatchException | IllegalArgumentException e) {
                System.out.printf("%s Try again: ", e.getMessage());
            }
        }
    }

    enum ActivePlayer {
        PLAYER_ONE, PLAYER_TWO
    }

    static void switchActivePlayer() {
        System.out.println("Press enter and pass the move to another player");
        InputReader.pressEnter();
        activePlayer = activePlayer == ActivePlayer.PLAYER_ONE ? ActivePlayer.PLAYER_TWO : ActivePlayer.PLAYER_ONE;
    }

    static Player getActivePlayer() {
        if (activePlayer == ActivePlayer.PLAYER_ONE) return player1;
        else return player2;
    }

    static Player getOtherPlayer() {
        if (activePlayer == ActivePlayer.PLAYER_ONE) return player2;
        else return player1;
    }

    private static void placeShips(Ship[] ships, Player player) {
        System.out.printf("%s, place your ships on the game field\n", player.getName());
        for (Ship ship : ships) {
            System.out.println(player.display(OPEN));
            System.out.printf("Enter the coordinates of the %s (%d cells)\n", ship.getName(), ship.getSize());
            boolean shipPlaced = false;
            while (!shipPlaced) {
                try {
                    player.placeShip(ship, InputReader.readCoordinates());
                    shipPlaced = true;
                    System.out.println(player.display(OPEN));
                } catch (IllegalArgumentException | InputMismatchException | WrongShipLengthException |
                         IllegalShipPlacementException e) {
                    System.out.printf("%s Try again:\n", e.getMessage());
                }
            }
        }
    }
}
