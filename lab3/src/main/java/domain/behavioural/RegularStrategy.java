package domain.behavioural;

import domain.structural.composite.MenuComponent;

import java.util.List;

public class RegularStrategy implements Strategy {

    @Override
    public void printMenu(List<MenuComponent> items) {
        for(MenuComponent item : items) {
            item.print();
        }
    }
}
