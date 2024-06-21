package cinemamanager;
import java.util.Scanner;

public class Cinema {
    public static void main(String[] args) {
        System.out.println("Enter the number of rows:");
        Scanner scanner = new Scanner(System.in);
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();
        System.out.println();

        char[][] cinemaSeats = buildSeats(rows, seats);
        boolean run = true;
        int totalIncome = 0;
        int totalTickets = 0;
        int seatsFilled = 0;
        int totalSoldOut;

        if (rows * seats < 60) {
            totalSoldOut = rows * seats * 10;
        } else {
            int highPriceTotal = rows / 2 * seats * 10;
            int lowPriceTotal = (rows  - rows / 2) * seats * 8;
            totalSoldOut = highPriceTotal + lowPriceTotal;
        }

        while (run) {
            System.out.println("1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit");
            boolean open = true;
            switch (scanner.nextInt()) {
                case 1:
                    showSeats(cinemaSeats);
                    break;
                case 2:
                    while (open) {
                        System.out.println();
                        System.out.println("Enter a row number:");
                        int rowNumber = scanner.nextInt();
                        if (rowNumber > rows) {
                            System.out.println("Wrong input!");
                            continue;
                        }
                        System.out.println("Enter a seat number in that row:");
                        int seatNumber = scanner.nextInt();
                        if (seatNumber > seats) {
                            System.out.println("Wrong input!");
                            continue;
                        }
                        if (cinemaSeats[rowNumber - 1][seatNumber - 1] == 'B') {
                            System.out.println("That ticket has already been purchased");
                            continue;
                        }
                            System.out.println();

                            int ticketPrice = buyTicket(cinemaSeats, rowNumber);
                            totalIncome += ticketPrice;
                            totalTickets++;
                            seatsFilled++;

                            fillSeat(cinemaSeats, rowNumber, seatNumber);
                            showSeats(cinemaSeats);
                            open = false;
                    }
                    break;
                case 3:
                    System.out.println("Number of purchased tickets: " + totalTickets);
                    float percentage = (float) seatsFilled / ((float) rows * (float) seats) * 100;
                    System.out.printf("Percentage: %.2f", percentage);
                    System.out.println("%");
                    System.out.println("Current Income: $" + totalIncome);
                    System.out.println("Total income: $" + totalSoldOut);
                    break;
                case 0:
                    run = false;
                    break;
                default:
                    System.out.println("Wrong input!");
            }
        }
    }

    public static char[][] buildSeats(int rows, int seats) {
        char[][] cinemaSeats = new char[rows][seats];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seats; j++) {
                cinemaSeats[i][j] = 'S';
            }
        }
        return cinemaSeats;
    }

    public static void showSeats(char[][] cinemaSeats) {
        System.out.print("Cinema:\n  ");
        for (int i = 1; i <= cinemaSeats[0].length; i++) {
            System.out.print(i + " ");
        }

        System.out.println();
        for (int i = 1; i <= cinemaSeats.length; i++) {
            System.out.print(i + " ");
            for (char character : cinemaSeats[i - 1]) {
                System.out.print(character + " ");
            }
            System.out.println();
        }
    }

    public static int buyTicket(char[][] cinemaSeats, int rowNumber) {
        int priceLow;
        int priceHigh = 10;
        if (cinemaSeats[0].length * cinemaSeats.length < 60) {
            priceLow = 10;
        } else {
            priceLow = 8;
        }

        int ticketPrice;
        if (cinemaSeats.length / 2 >= rowNumber) {
            ticketPrice = priceHigh;
        } else {
            ticketPrice = priceLow;
        }

        System.out.println("Ticket price: $" + ticketPrice);
        return ticketPrice;
    }

    public static void fillSeat(char[][] cinemaSeats, int rowNumber, int seatNumber) {
        cinemaSeats[rowNumber - 1][seatNumber - 1] = 'B';
    }
}