package domain.factory;

import domain.models.Cappuccino;
import domain.models.Drink;

public class CappuccinoFactory extends DrinkFactory{
    @Override
    public Drink createDrink() {
        return new Cappuccino();
    }
}

