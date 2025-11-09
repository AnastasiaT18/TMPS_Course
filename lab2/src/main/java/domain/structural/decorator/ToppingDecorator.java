package domain.structural.decorator;

import domain.builder.CustomDrinkBuilder.Topping;
import domain.models.Drink;

public class ToppingDecorator extends DrinkDecorator {

    private Topping topping;

    public ToppingDecorator(Drink drink, Topping topping) {
        super(drink);
        this.topping = topping;
    }

    @Override
    public String getName() {
        return  drink.getName() + " + " + topping + " Topping";
    }

    @Override
    public double getPrice() {
        return drink.getPrice() + 4.0;
    }


}
