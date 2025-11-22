package domain.behavioural;

import domain.structural.composite.MenuComponent;

public class MenuLogger implements Observer{

    @Override
    public void update(MenuComponent item, String action) {
        if (action.equals("added")) {
        System.out.println("-New menu item added: " + item.getName() + " - " + item.getPrice());
    }
        else if (action.equals("removed")) {
            System.out.println("-Menu item removed: " + item.getName() + " - " + item.getPrice());
        }
    }
}
