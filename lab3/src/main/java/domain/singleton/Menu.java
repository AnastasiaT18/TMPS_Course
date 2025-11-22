package domain.singleton;

import domain.behavioural.*;
import domain.structural.composite.MenuComponent;
import java.time.LocalTime;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private static Menu instance;
    private List<MenuComponent> menuItems;

    // Lab 4 Additions
    private List<Observer> observers; //Observer pattern
    public Strategy strategy; //Strategy pattern

    private Menu(){
        menuItems = new ArrayList<>();
        observers = new ArrayList<>();
        }

    public static Menu getInstance() {
        if (Menu.instance == null) {
            Menu.instance = new Menu();
        }
        return Menu.instance;
    }

    public void printMenu(String type) {
        if (type!=null && type.equals("student")) {
            this.setStrategy(new StudentStrategy());
            System.out.println("\n==================== STUDENT MENU (50% discount) ====================");
        }
        else{
            LocalTime now = LocalTime.now();
//            LocalTime now = LocalTime.of(17, 30);
            LocalTime happyHourStart = LocalTime.of(17, 0); // 5:00 PM
            LocalTime happyHourEnd = LocalTime.of(19, 0);

            if (now.isAfter(happyHourStart) && now.isBefore(happyHourEnd)){
                this.setStrategy(new HappyHourStrategy());
                System.out.println("\n==================== HAPPY HOUR MENU (17:00–19:00, -20%) ====================");
            }
            else{
                this.setStrategy(new RegularStrategy());
                System.out.println("\n==================== MENU ====================");
            }
        }
        this.strategy.printMenu(menuItems);
    }

//    public void printMenu(String type){
//        if (type.equals("student")) {
//            this.setStrategy(new StudentStrategy());
//            System.out.println("Take advantage of your student discount!");
//
//        }
//        this.strategy.printMenu(menuItems);
//    }

    public List<MenuComponent> getMenuItems() {
        return menuItems;
    }

    public void add(MenuComponent item) {

        menuItems.add(item);
        notifyObservers(item, "added");
    }

    public void remove(MenuComponent item) {
        menuItems.remove(item);
        notifyObservers(item, "removed");
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(MenuComponent item, String action) {
        for (Observer observer : observers) {
            observer.update(item, action);
        }
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
}
