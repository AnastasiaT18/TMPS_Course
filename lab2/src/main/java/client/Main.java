package client;

import domain.builder.CoffeeDirector;
import domain.builder.CustomDrinkBuilder;
import domain.builder.RegularDrinkBuilder;
import domain.builder.SpecialtyDrinkBuilder;
import domain.factory.CappuccinoFactory;
import domain.factory.EspressoFactory;
import domain.factory.LatteFactory;
import domain.models.CustomDrink;
import domain.singleton.Menu;

public class Main {
    public static void main(String[] args) {

        Menu menu = Menu.getInstance();

        menu.add(new EspressoFactory().createDrink());
        menu.add(new LatteFactory().createDrink());
        menu.add(new CappuccinoFactory().createDrink());

        CustomDrinkBuilder regularBuilder = new RegularDrinkBuilder();
        CustomDrinkBuilder specialBuilder = new SpecialtyDrinkBuilder();

        CoffeeDirector director = new CoffeeDirector();

        menu.add(director.makeCaramelLatte(regularBuilder));
        menu.add(director.makeChocolateCappuccino(specialBuilder));
        menu.add(director.makeIcedChocolateMocha(specialBuilder));
        menu.add(director.makeCinnamonCaramelLatte(specialBuilder));
        menu.add(director.makePumpkinSpiceLatte(specialBuilder));

        menu.printMenu();

    }
}
