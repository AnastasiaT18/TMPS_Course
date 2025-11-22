package domain.factory;

import domain.models.Drink;
import domain.models.Latte;

public class LatteFactory extends DrinkFactory{
    @Override
    public Drink createDrink() {
        return new Latte();
    }
}
