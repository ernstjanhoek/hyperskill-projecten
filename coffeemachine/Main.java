package coffeemachine;

import java.util.Scanner;

import static coffeemachine.CoffeeConstants.*;

/* Een CoffeeMachine object heeft als veld een Inventory object. Het Inventory object bestaat uit Resource objects.
Afzonderlijke drankjes zijn Drink objects, die hebben geen Resource velden om hun bestandsdelen aan te geven, maar
'normale' ints. Als uitdaging stelde ik om de Inventory mutable qua opzet te maken en de Drink objecten final.
In eerste instantie wilde ik voor de ingredienten in Drink ook Resource classes gebruiken, maar in dat geval was het
nog steeds mogelijk om na instantiering van de Drink objects de Resource velden aan te passen met de interface van
Resource.
*/

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Inventory startingInventory = new Inventory(120, 540, 400, 9, 550);

        final Drink cappuccino = new Drink(CAPPU_BEANS, CAPPU_WATER, CAPPU_MILK, CAPPU_COST, 1);

        final Drink latte = new Drink(LATTE_BEANS, LATTE_WATER, LATTE_MILK, LATTE_COST, 1);

        final Drink espresso = new Drink(ESPR_BEANS, ESPR_WATER, 0, ESPR_COST, 1);

        CoffeeMachine coffeeMachine = new CoffeeMachine(startingInventory);
        while (coffeeMachine.isPoweredOn()) {
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            String mainMenuString = scanner.next();
            switch (mainMenuEnum.fromStr(mainMenuString)) {
                case REMAINING -> System.out.println(coffeeMachine.remainingMessage());
                case BUY -> {
                    System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                    try {
                        switch (buyMenuEnum.fromStr(scanner.next()))  {
                            case CAPPUCCINO -> coffeeMachine.make(cappuccino);
                            case LATTE ->  coffeeMachine.make(latte);
                            case ESPRESSO -> coffeeMachine.make(espresso);
                            case BACK -> {}
                            default -> throw new Exception("invalid selection");
                        }
                    } catch(Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                case FILL -> coffeeMachine.fillFromInput(scanner);
                case TAKE -> coffeeMachine.takeMoney();
                case EXIT -> coffeeMachine.powerOff();
            }
        }
    }

    enum mainMenuEnum {
        REMAINING, BUY, FILL, TAKE, EXIT, INVALID;
        static mainMenuEnum fromStr(String input) {
            try {
                return mainMenuEnum.valueOf(input.toUpperCase());
            } catch (Exception e) {
                return mainMenuEnum.INVALID;
            }
        }
    }

    enum buyMenuEnum {
        ESPRESSO, LATTE, CAPPUCCINO, BACK, INVALID;

        static buyMenuEnum fromStr(String input) {
            if ("back".equals(input)) {
                return matchInput(input);
            }
            try {
                int buyOptionInt = Integer.parseInt(input);
                return matchInput(buyOptionInt);
            } catch (Exception e) {
                return buyMenuEnum.INVALID;
            }
        }

        private static buyMenuEnum matchInput(int input) {
            return switch (input) {
                case 1 -> buyMenuEnum.ESPRESSO;
                case 2 -> buyMenuEnum.LATTE;
                case 3 -> buyMenuEnum.CAPPUCCINO;
                default -> buyMenuEnum.INVALID;
            };
        }

        private static buyMenuEnum matchInput(String input) {
            if ("back".equals(input)) {
                return buyMenuEnum.BACK;
            }
            return buyMenuEnum.INVALID;
        }
    }
}
