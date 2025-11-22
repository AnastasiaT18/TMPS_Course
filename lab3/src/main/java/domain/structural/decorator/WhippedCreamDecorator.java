package domain.structural.decorator;

import domain.models.Drink;

public class WhippedCreamDecorator extends DrinkDecorator {


    public WhippedCreamDecorator(Drink drink) {
        super(drink);
    }

    @Override
    public String getName() {
        return drink.getName() + " + WhippedCream";
    }

    @Override
    public double getPrice() {
        return drink.getPrice() + 3.0;
    }
}
