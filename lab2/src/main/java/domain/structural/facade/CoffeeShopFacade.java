package domain.structural.facade;

import domain.builder.*;
import domain.factory.*;
import domain.models.Drink;
import domain.singleton.Menu;
import domain.structural.composite.*;
import domain.structural.decorator.*;


import static domain.builder.CustomDrinkBuilder.Syrup.COCONUT;
import static domain.builder.CustomDrinkBuilder.Topping.MARSHMALLOWS;

public class CoffeeShopFacade {

    private final Menu menu;
    private final CoffeeDirector director;

    public CoffeeShopFacade() {
        this.menu = Menu.getInstance();
        this.director = new CoffeeDirector();

    }

    public void prepareBasicMenu() {
        menu.add(new EspressoFactory().createDrink());
        menu.add(new LatteFactory().createDrink());
        menu.add(new CappuccinoFactory().createDrink());
    }

    public void prepareSpecialtyMenu() {
        CustomDrinkBuilder regularBuilder = new RegularDrinkBuilder();
        CustomDrinkBuilder specialBuilder = new SpecialtyDrinkBuilder();

        menu.add(director.makeCaramelLatte(regularBuilder));
        menu.add(director.makeChocolateCappuccino(specialBuilder));
        menu.add(director.makeIcedChocolateMocha(specialBuilder));
        menu.add(director.makeCinnamonCaramelLatte(specialBuilder));
        menu.add(director.makePumpkinSpiceLatte(specialBuilder));

    }

    public void showMenu() {
        menu.printMenu();
    }

    public void prepareCustomDrink() {
        Drink customDrink = new ToppingDecorator(
                new IceDecorator(
                        new WhippedCreamDecorator(
                                new SyrupDecorator(
                                        new LatteFactory().createDrink(), COCONUT
                                )
                        )
                ),
                MARSHMALLOWS
        );

        menu.add(customDrink);
    }

    public void createCombos() {
        Combo morningCombo = new Combo("Morning Combo");
        morningCombo.addComponent(new LatteFactory().createDrink());
        morningCombo.addComponent(new Snack("Croissant", 15));

        Combo afternoonCombo = new Combo("Afternoon Combo");
        afternoonCombo.addComponent(new EspressoFactory().createDrink());
        afternoonCombo.addComponent(new Snack("Cookie", 15));

        Combo fullOrder = new Combo("Full Order");
        fullOrder.addComponent(morningCombo);
        fullOrder.addComponent(afternoonCombo);

        menu.add(fullOrder);
    }
}
