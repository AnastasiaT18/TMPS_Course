package domain.structural.decorator;

import domain.builder.CustomDrinkBuilder.Syrup;
import domain.models.Drink;

public class SyrupDecorator extends DrinkDecorator {

    private Syrup syrup;

    public SyrupDecorator(Drink drink, Syrup syrup) {
        super(drink);
        this.syrup = syrup;
    }

    @Override
    public String getName() {
        return drink.getName() + " + " + syrup + " Syrup";
    }

    @Override
    public double getPrice() {
        return drink.getPrice() + 5.0;
    }


}
