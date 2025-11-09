package domain.structural.decorator;

import domain.models.Drink;

public class IceDecorator extends DrinkDecorator {


    public IceDecorator(Drink drink) {
        super(drink);
    }

    @Override
    public String getName() {
        return drink.getName() + " (iced)";
    }

    @Override
    public double getPrice() {
        return drink.getPrice() + 2.0;
    }


}
