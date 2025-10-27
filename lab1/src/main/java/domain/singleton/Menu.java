package domain.singleton;


import domain.models.Drink;

import java.util.List;
import java.util.ArrayList;

public class Menu {
    private static Menu instance;

    private List<Drink> menuItems;

    private Menu(){
        menuItems = new ArrayList<>();
        menuItems.add(new Drink("Espresso", 25));
        menuItems.add(new Drink("Latte", 40));
        menuItems.add(new Drink("Cappuccino", 50));
        menuItems.add(new Drink("Pumpkin Spice Latte", 70));
    }

    public static Menu getInstance() {
        if (Menu.instance == null) {
            Menu.instance = new Menu();
        }
        return Menu.instance;
    }

    public void printMenu() {
        for (Drink drink : menuItems) {
            System.out.println(drink.getName() +": " + drink.getPrice());
        }
        System.out.println("-----------------------");
    }

    public List<Drink> getMenuItems() {
        return menuItems;
    }
}
