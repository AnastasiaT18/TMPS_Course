package src;// TMPS - Lab 0
// Coffee Shop Example implementing 3 SOLID principles:
// 1. SRP – Order and OrderPrinter separated
// 2. OCP – New drink types can be added without modifying existing code
// 3. DIP – Barista depends on the DrinkMaker interface

import java.util.List;
import java.util.ArrayList;

// S: Each class has ONE responsibility

class Drink {
    private final String name;
    private final double price;

    public Drink(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

class Order {
    private final List<Drink> drinks = new ArrayList<>();

    public void addDrink(Drink drink) {
        drinks.add(drink);
    }

    public double totalPrice() {
        return drinks.stream().mapToDouble(Drink::getPrice).sum();
    }

    public List<Drink> getDrinks() {
        return drinks;
    }
}

class OrderPrinter {
    public void printOrder(Order order) {
        System.out.println("------- Order details -------");
        for (Drink drink : order.getDrinks()) {
            System.out.println("- " + drink.getName() + ": $" + drink.getPrice());
        }
        System.out.println("Total: $" + order.totalPrice());
        System.out.println("-----------------------------");
    }
}

// O: Open for extension, closed for modification

interface DrinkMaker {
    Drink make();
}

class CappuccinoMaker implements DrinkMaker {
    @Override
    public Drink make() {
        return new Drink("Cappuccino", 3.0);
    }
}

class LatteMaker implements DrinkMaker {
    @Override
    public Drink make() {
        return new Drink("Latte", 3.5);
    }
}

// D: Depend on abstractions, not concretions

class Barista {
    private final DrinkMaker drinkMaker;

    public Barista(DrinkMaker drinkMaker) {
        this.drinkMaker = drinkMaker;
    }

    public Drink prepareDrink() {
        return drinkMaker.make();
    }
}

// Example usage

public class CoffeeShop {
    public static void main(String[] args) {
        Barista cappuccinoBarista = new Barista(new CappuccinoMaker());
        Barista latteBarista = new Barista(new LatteMaker());

        Order order = new Order();
        order.addDrink(cappuccinoBarista.prepareDrink());
        order.addDrink(latteBarista.prepareDrink());

        OrderPrinter printer = new OrderPrinter();
        printer.printOrder(order);
    }
}
