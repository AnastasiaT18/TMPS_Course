package domain.behavioural;

import domain.structural.composite.MenuComponent;

import java.util.List;

public class StudentStrategy implements Strategy {
    @Override
    public void printMenu(List<MenuComponent> items) {
        for (MenuComponent item : items) {
            System.out.println(item.getName() + ".........." + item.getPrice()*0.5+" (was  " + item.getPrice() + ")");
        }
        System.out.println("-----------------------");
    }
}
