package coffeemachine;

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
}
