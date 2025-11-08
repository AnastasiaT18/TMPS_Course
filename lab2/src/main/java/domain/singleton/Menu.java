package domain.singleton;
import domain.models.Drink;

import java.util.List;
import java.util.ArrayList;

public class Menu {
    private static Menu instance;
    private List<Drink> menuItems;

    private Menu(){
        menuItems = new ArrayList<>();
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

    public void add(Drink drink) {
        menuItems.add(drink);
    }
}
