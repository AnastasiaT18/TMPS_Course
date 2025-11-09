package domain.structural.decorator;

import domain.models.Drink;

public abstract class DrinkDecorator extends Drink {

    protected final Drink drink;

    DrinkDecorator(Drink drink) {
        super(drink.getName(), drink.getPrice());
        this.drink = drink;
    }

//    public abstract String getName();

//    public abstract double getPrice();
}
