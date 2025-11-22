package domain.singleton;

import domain.structural.composite.MenuComponent;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private static Menu instance;
    private List<MenuComponent> menuItems;

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
        for (MenuComponent item : menuItems) {
//            System.out.println(item.getName() + ": " +  item.getPrice());
            item.print();
        }
        System.out.println("-----------------------");
    }

    public List<MenuComponent> getMenuItems() {
        return menuItems;
    }

    public void add(MenuComponent item) {
        menuItems.add(item);
    }
}
