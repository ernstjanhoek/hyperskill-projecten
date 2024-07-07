package coffeemachine;

class Inventory {
    Resource beans;
    Resource milk;
    Resource water;
    Resource cups;
    Resource money;

    Inventory(int beans, int milk, int water, int cups, int money) {
        this.beans = new Resource(beans, "beans");
        this.milk = new Resource(milk, "milk");
        this.water = new Resource(water, "water");
        this.cups = new Resource(cups, "water");
        this.money = new Resource(money, "money");
    }

    public Inventory update(Drink drink) throws ResourceException {
        this.beans.checkResource(drink.beans());
        this.milk.checkResource(drink.milk());
        this.water.checkResource(drink.water());
        this.cups.checkResource(drink.cups());

        return new Inventory(
                this.beans.getValue() - drink.beans(),
                this.milk.getValue() - drink.milk(),
                this.water.getValue() - drink.water(),
                this.cups.getValue() - 1,
                this.money.getValue() + drink.price()
        );
    }
}
