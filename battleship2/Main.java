package battleship2;

import battleship2.exceptions.IllegalShipPlacementException;
import battleship2.exceptions.WrongShipLengthException;

import static battleship2.Player.DisplayOption.FOW;
import static battleship2.Player.DisplayOption.OPEN;

import java.util.InputMismatchException;


public class Main {
    static Player player1;
    static Player player2;
    static ActivePlayer activePlayer = ActivePlayer.PLAYER_ONE;

    public static void main(String[] args) {
        player1 = new Player("Player 1", 10);
        player2 = new Player("Player 2", 10);

        Ship[] ships = new Ship[5];
        ships[0] = new Ship("Aircraft Carrier", new Coordinate[5]);
        ships[1] = new Ship("Battleship", new Coordinate[4]);
        ships[2] = new Ship("Submarine", new Coordinate[3]);
        ships[3] = new Ship("Cruiser", new Coordinate[3]);
        ships[4] = new Ship("Destroyer", new Coordinate[2]);

        placeShips(ships.clone(), getActivePlayer());
        switchActivePlayer();

        placeShips(ships, getActivePlayer());
        switchActivePlayer();

        boolean isRunning = true;
        while (isRunning) {
            System.out.println(getOtherPlayer().display(FOW));
            System.out.println("---------------------");
            System.out.println(getActivePlayer().display(OPEN));
            try {
                System.out.println(getActivePlayer().getName() + ", it's your turn:");
                switch (getOtherPlayer().shootAt(InputReader.readCoordinate())) {
                    case DAMAGED -> System.out.println("You hit a ship! Try again:");
                    case MISSED -> System.out.println("You missed! Try again:");
                    case SANK -> System.out.println("You sank a ship! Specify a new target:");
                    case GAME_OVER -> {
                        System.out.println("You sank the last ship. You won. Congratulations!");
                        isRunning = false;
                    }
                }
                switchActivePlayer();
            } catch (InputMismatchException | IllegalArgumentException e) {
                System.out.println(e.getMessage() + " Try again:");
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
        System.out.println(player.getName() + ", place your ships on the game field");
        for (int i = 0; i < ships.length; ) {
            System.out.println(player.display(OPEN));
            System.out.printf("Enter the coordinates of the %s (%d cells)\n", ships[i].getName(), ships[i].getSize());
            boolean shipPlaced = false;
            while (!shipPlaced) {
                try {
                    ships[i].setShipCoordinates(InputReader.readCoordinates());
                    player.placeShip(ships[i]);
                    shipPlaced = true;
                    i++;
                    System.out.println(player.display(OPEN));
                } catch (InputMismatchException | WrongShipLengthException | IllegalShipPlacementException e) {
                    System.out.println(e.getMessage() + " Try again:");
                }
            }
        }
    }
}
