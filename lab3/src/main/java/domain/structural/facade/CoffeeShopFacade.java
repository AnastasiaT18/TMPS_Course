package domain.structural.facade;

import domain.behavioural.MenuLogger;
import domain.behavioural.Strategy;
import domain.behavioural.StudentStrategy;
import domain.builder.CoffeeDirector;
import domain.builder.CustomDrinkBuilder;
import domain.builder.RegularDrinkBuilder;
import domain.builder.SpecialtyDrinkBuilder;
import domain.factory.CappuccinoFactory;
import domain.factory.EspressoFactory;
import domain.factory.LatteFactory;
import domain.models.Drink;
import domain.singleton.Menu;
import domain.structural.composite.Combo;
import domain.structural.composite.MenuComponent;
import domain.structural.composite.Snack;
import domain.structural.decorator.IceDecorator;
import domain.structural.decorator.SyrupDecorator;
import domain.structural.decorator.ToppingDecorator;
import domain.structural.decorator.WhippedCreamDecorator;

import java.util.List;

import static domain.builder.CustomDrinkBuilder.Syrup.COCONUT;
import static domain.builder.CustomDrinkBuilder.Topping.MARSHMALLOWS;

public class CoffeeShopFacade {

    private final Menu menu;
    private final CoffeeDirector director;

    public CoffeeShopFacade() {
        this.menu = Menu.getInstance();
        this.menu.addObserver(new MenuLogger());
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
        menu.printMenu(null);
    }

    public void showStudentMenu(){
        menu.printMenu("student");
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

//        customDrink.print();

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

    public void refreshMenuItems() {
        List<MenuComponent> items = menu.getMenuItems();

        if (!items.isEmpty()) {
            MenuComponent lastItem = items.getLast();
            menu.remove(lastItem);
        }

    }
}
