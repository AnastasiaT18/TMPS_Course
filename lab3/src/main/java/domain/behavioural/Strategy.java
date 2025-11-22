package domain.behavioural;

import domain.structural.composite.MenuComponent;

import java.util.List;

public interface Strategy {
    void printMenu(List<MenuComponent> items);
}
