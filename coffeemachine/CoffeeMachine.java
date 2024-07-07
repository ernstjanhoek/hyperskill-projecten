package coffeemachine;

import java.util.Scanner;

class CoffeeMachine {
    Inventory inventory;
    private boolean poweredOn;

    public CoffeeMachine(Inventory inventory) {
        this.inventory = inventory;
        this.poweredOn = true;
    }

    public boolean isPoweredOn() {
        return this.poweredOn;
    }

    public void powerOff() {
        this.poweredOn = false;
    }

    public String remainingMessage() {
        return "The coffee machine has:\n" +
                this.inventory.water.getValue() + " ml of water\n" +
                this.inventory.milk.getValue() + " ml of milk\n" +
                this.inventory.beans.getValue() + " g of coffee beans\n" +
                this.inventory.cups.getValue() + " disposable cups\n" +
                "$" + this.inventory.money.getValue() + " of money";
    }

    public void make(Drink drink) throws ResourceException {
        this.inventory = this.inventory.update(drink);
        System.out.println("I have enough resources, making you a coffee!");

    }

    public void fillFromInput(Scanner scanner) {
        System.out.println("Write how many ml of water you want to add:");
        this.inventory.water.fill(scanner.nextInt());
        System.out.println("Write how many ml of milk you want to add:");
        this.inventory.milk.fill(scanner.nextInt());
        System.out.println("Write how many grams of coffee beans you want to add:");
        this.inventory.beans.fill(scanner.nextInt());
        System.out.println("Write how many disposable cups you want to add:");
        this.inventory.cups.fill(scanner.nextInt());
    }

    public void takeMoney() {
        System.out.println("I gave you $" + this.inventory.money.getValue());
        this.inventory.money.take();
    }
}
