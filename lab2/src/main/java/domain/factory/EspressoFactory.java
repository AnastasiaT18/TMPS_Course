package domain.factory;

import domain.models.Drink;
import domain.models.Espresso;

public class EspressoFactory extends DrinkFactory{
    @Override
    public Drink createDrink() {
        return new Espresso();
    }
}
