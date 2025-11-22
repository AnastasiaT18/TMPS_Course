package domain.structural.composite;

import java.util.ArrayList;
import java.util.List;


public class Combo implements MenuComponent {
    private String name;
    private List<MenuComponent> items = new ArrayList<>();


    public Combo(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getPrice() {
        return items.stream().mapToDouble(MenuComponent::getPrice).sum();
    }

    @Override
    public void print() {
        System.out.println("\nCombo: " + name);
        for (MenuComponent item : items) {
            item.print();
        }
        System.out.println("Total (" + name + "): " + getPrice());
    }

    public void addComponent(MenuComponent component) {
        items.add(component);
    }

    public void removeComponent(MenuComponent component) {
        items.remove(component);
    }
}
