# Laboratory Work #2 – Structural Design Patterns
Author: Anastasia Tiganescu

## Objectives:
1. Study and understand the Structural Design Patterns.

2. As a continuation of the previous laboratory work, think about the functionalities that your system will need to provide to the user.

3. Implement some additional functionalities using structural design patterns.


## Main tasks:
1. By extending your project, implement at least 3 structural design patterns in your project:
   - The implemented design pattern should help to perform the tasks involved in your system.
   - The object creation mechanisms/patterns can now be buried into the functionalities instead of using them into the client.
   - There should only be one client for the whole system.
2. Keep your files grouped (into packages/directories) by their responsibilities (an example project structure):

3. Document your work in a separate markdown file.

## Chosen Domain

The chosen domain remains the Coffee Shop Management System, which simulates how drinks are created, customized, and displayed on a shared menu.

In this lab, the focus shifts from object creation (covered in Lab 1) to object composition — how multiple classes and objects can work together to provide new, flexible functionalities.

## Used Design Patterns

- Decorator Pattern – for dynamic, on-demand customization of drinks.

- Composite Pattern – for grouping items like drinks and snacks into combos.

- Facade Pattern – for simplifying access to the entire system through one interface.

## Implementation

### Decorator Pattern 
#### Purpose

The Decorator Pattern allows behavior to be added to individual objects dynamically, without modifying their original class.
In the coffee shop system, this pattern is used to create custom drinks on the fly — for example, adding syrup, toppings, whipped cream, or ice to any base drink, depending on the client’s preferences.

While the Builder Pattern (from Lab 1) was used for predefined recipes like Caramel Latte or Pumpkin Spice Latte,
the Decorator Pattern now enables fully dynamic customization — letting customers create their own drinks at runtime.

#### Implementation

All decorators extend an abstract class DrinkDecorator, which itself extends the base class Drink.
This allows decorators to wrap around any Drink object and modify its behavior without changing the underlying class.

```java
public abstract class DrinkDecorator extends Drink {

    protected final Drink drink;

    DrinkDecorator(Drink drink) {
        super(drink.getName(), drink.getPrice());
        this.drink = drink;
    }
}
```

Each concrete decorator adds its own name and price modifications while keeping the structure flexible.

#### Example: Ice Decorator

```java
public class IceDecorator extends DrinkDecorator {
    
    public IceDecorator(Drink drink) {
        super(drink);
    }

    @Override
    public String getName() {
        return drink.getName() + " (iced)";
    }

    @Override
    public double getPrice() {
        return drink.getPrice() + 2.0;
    }
}

```

#### Example: Syrup Decorator

```java
public class SyrupDecorator extends DrinkDecorator {

    private Syrup syrup;

    public SyrupDecorator(Drink drink, Syrup syrup) {
        super(drink);
        this.syrup = syrup;
    }

    @Override
    public String getName() {
        return drink.getName() + " + " + syrup + " Syrup";
    }

    @Override
    public double getPrice() {
        return drink.getPrice() + 5.0;
    }
}

```

Other decorators, such as ToppingDecorator and WhippedCreamDecorator, follow the same structure.

#### Example Usage (from Main)

In the CoffeeShopFacade (will explain in the following sections), a dynamic drink is built step-by-step using decorators, starting from a base drink (Latte) and layering new features:

```java
   public void prepareCustomDrink() {
    Drink customDrink = new ToppingDecorator(
            new IceDecorator(
                    new WhippedCreamDecorator(
                            new SyrupDecorator(
                                    new LatteFactory().createDrink(), COCONUT
                            )
                    )
            ),
            MARSHMALLOWS
    );

    menu.add(customDrink);
}
```
#### Output Example:
```
- Latte + COCONUT Syrup + WhippedCream (iced) + MARSHMALLOWS Topping: 54.0

```

-----------------------
### Composite Method
#### Purpose
The Composite Pattern lets you treat individual objects and groups of objects in the same way. It is especially useful for representing tree-like structures, such as menus or orders that contain other items.

In this project, the Composite pattern was used to create combos or bundles of items in the coffee shop system.
For example, a combo can include both drinks and snacks — or even other combos — and still be handled as a single MenuComponent.

In my code, this allows the system to specifically calculate the total price and display the entire order tree recursively.

#### Implementation

To implement this pattern, I first restructured my model slightly compared to Lab 1.
Instead of having Drink as the root class, I created an abstract class BaseItem and an interface MenuComponent to unify the structure of all menu elements.

```java
public interface MenuComponent {
    String getName();
    double getPrice();
    void print();
}
```

```java
public abstract class BaseItem implements MenuComponent {
    private final String name;
    private final double price;

    public BaseItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void print() {
        System.out.println("- " + this.getName() + ": " + this.getPrice());
    }
}
```

Each individual menu item (drink or snack - leaf components) extends BaseItem, while groups of items (combos) implement the same MenuComponent interface — making them all compatible in the same hierarchy.

```java
public class Drink extends BaseItem {
    public Drink(String name, double price) {
        super(name, price);
    }
}
```

```java
public class Snack extends BaseItem{

    public Snack(String name, double price) {
        super(name, price);
    }
}
```

The Combo class stores a list of MenuComponent objects (which can be single items or other combos).
It overrides getPrice() to sum up the prices of all included components, and print() recursively prints their structure.
```java
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

```

#### Example Usage 

In the CoffeeShopFacade, I used this pattern to create nested combos of drinks and snacks:

```java
 public void createCombos() {
        Combo morningCombo = new Combo("Morning Combo");
        morningCombo.addComponent(new LatteFactory().createDrink());
        morningCombo.addComponent(new Snack("Croissant", 15));

        Combo afternoonCombo = new Combo("Afternoon Combo");
        afternoonCombo.addComponent(new EspressoFactory().createDrink());
        afternoonCombo.addComponent(new Snack("Cookie", 15));

        Combo fullOrder = new Combo("Full Order");
        fullOrder.addComponent(morningCombo);
        fullOrder.addComponent(afternoonCombo);

        menu.add(fullOrder);
    }
```

#### Output Example:
```
Combo: Full Order

Combo: Morning Combo
- Latte: 40.0
- Croissant: 15.0
Total (Morning Combo): 55.0

Combo: Afternoon Combo
- Espresso: 20.0
- Cookie: 15.0
Total (Afternoon Combo): 35.0
Total (Full Order): 90.0
-----------------------
```
-----------------------

### Facade Pattern 
#### Purpose

The Facade Pattern provides a simplified, unified interface to a complex subsystem. It hides the internal details and dependencies of multiple components behind a single high-level class, making it easier for clients to interact with the system.

In this project, the CoffeeShopFacade class acts as the main entry point for all coffee shop operations.
It brings together functionalities from multiple patterns — Factory, Builder, Decorator, Composite, and Singleton — and exposes them through simple, high-level methods like prepareBasicMenu(), prepareCustomDrink(), or createCombos().

This allows the client (in this case, the Main class) to interact with the system using just a few intuitive method calls, without worrying about how objects are created, decorated, or added to the menu.

```java
public class CoffeeShopFacade {

    private final Menu menu;
    private final CoffeeDirector director;

    public CoffeeShopFacade() {
        this.menu = Menu.getInstance();
        this.director = new CoffeeDirector();

    }

    public void prepareBasicMenu() {
        menu.add(new EspressoFactory().createDrink());
        menu.add(new LatteFactory().createDrink());
        menu.add(new CappuccinoFactory().createDrink());
    }

    public void prepareSpecialtyMenu() {
        CustomDrinkBuilder regularBuilder = new RegularDrinkBuilder();
        CustomDrinkBuilder specialBuilder = new SpecialtyDrinkBuilder();

        menu.add(director.makeCaramelLatte(regularBuilder));
        menu.add(director.makeChocolateCappuccino(specialBuilder));
        menu.add(director.makeIcedChocolateMocha(specialBuilder));
        menu.add(director.makeCinnamonCaramelLatte(specialBuilder));
        menu.add(director.makePumpkinSpiceLatte(specialBuilder));

    }

    public void showMenu() {
        menu.printMenu();
    }

    public void prepareCustomDrink() {
        Drink customDrink = new ToppingDecorator(
                new IceDecorator(
                        new WhippedCreamDecorator(
                                new SyrupDecorator(
                                        new LatteFactory().createDrink(), COCONUT
                                )
                        )
                ),
                MARSHMALLOWS
        );

        customDrink.print();

        menu.add(customDrink);
    }

    public void createCombos() {
        Combo morningCombo = new Combo("Morning Combo");
        morningCombo.addComponent(new LatteFactory().createDrink());
        morningCombo.addComponent(new Snack("Croissant", 15));

        Combo afternoonCombo = new Combo("Afternoon Combo");
        afternoonCombo.addComponent(new EspressoFactory().createDrink());
        afternoonCombo.addComponent(new Snack("Cookie", 15));

        Combo fullOrder = new Combo("Full Order");
        fullOrder.addComponent(morningCombo);
        fullOrder.addComponent(afternoonCombo);

        menu.add(fullOrder);
    }
}

```

#### Example Usage 
With the Facade in place, the Main class (the only client) doesn’t need to know anything about the internal structure of the system — it simply calls the high-level methods:

```java
public class Main {
    public static void main(String[] args) {

        CoffeeShopFacade coffeeShop = new CoffeeShopFacade();

        coffeeShop.prepareBasicMenu();
        coffeeShop.prepareSpecialtyMenu();

        coffeeShop.prepareCustomDrink();
        coffeeShop.createCombos();

        coffeeShop.showMenu();

    }
}
```
#### Output Example:
```
- Espresso: 20.0
- Latte: 40.0
- Cappuccino: 50.0
- Caramel Latte: 60.0
- Chocolate Cappuccino: 60.0
- Iced Chocolate Mocha: 65.0
- Cinnamon Caramel Latte: 75.0
- Pumpkin Spice Latte: 75.0
- Latte + COCONUT Syrup + WhippedCream (iced) + MARSHMALLOWS Topping: 54.0

Combo: Full Order

Combo: Morning Combo
- Latte: 40.0
- Croissant: 15.0
Total (Morning Combo): 55.0

Combo: Afternoon Combo
- Espresso: 20.0
- Cookie: 15.0
Total (Afternoon Combo): 35.0
Total (Full Order): 90.0
-----------------------

```

## Conclusion
In this laboratory work, I successfully extended the Coffee Shop Management System by integrating three structural design patterns — Decorator, Composite, and Facade — to enhance flexibility, scalability, and usability.

The Decorator Pattern allowed dynamic drink customization without altering the base classes, making it possible to combine multiple modifications (like syrup, toppings, or ice) at runtime. The Composite Pattern introduced hierarchical menu structures, enabling individual items and combos to be treated uniformly. Finally, the Facade Pattern unified the system under a single, simple interface, making the client’s interaction effortless and clean.

Overall, this lab demonstrated how structural design patterns help organize complex systems into modular, extensible, and easy-to-maintain architectures — turning the coffee shop simulation into a cohesive and realistic software model.