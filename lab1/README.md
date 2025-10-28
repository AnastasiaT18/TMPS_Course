# Laboratory Work #1 – Creational Design Patterns
Author: Anastasia Tiganescu

## Objectives:
1. Study and understand the Creational Design Patterns.

2. Choose a domain, define its main classes/models/entities and choose the appropriate instantiation mechanisms.

3. Use some creational design patterns for object instantiation in a sample project.


## Main tasks:
1. Choose an OO programming language and a suitable IDE or Editor (No frameworks/libs/engines allowed).

2. Select a domain area for the sample project.

3. Define the main involved classes and think about what instantiation mechanisms are needed.

4. Based on the previous point, implement atleast 3 creational design patterns in your project.

## Chosen Domain

The chosen domain is a Coffee Shop Management System, which simulates how drinks (like espresso, latte, and cappuccino) are created, customized, and displayed on a dynamic menu.
This project focuses on how different patterns can simplify object creation and enhance flexibility — especially when building both standard and customized drinks.

## Used Design Patterns

- Factory Method – to create different types of base drinks (Espresso, Latte, Cappuccino).

- Singleton – to manage a single shared menu instance across the system.

- Builder – to construct complex customized drinks (like “Iced Mocha” or “Pumpkin Spice Latte”) step-by-step.

## Implementation

### Singleton Pattern 
#### Purpose

The Singleton Pattern ensures that a class has only one instance throughout the system and provides a global access point to it.
In this project, the `Menu` class represents the coffee shop’s shared menu — a single, consistent list of available drinks that can be accessed and updated from anywhere in the application.

The `Menu` class contains:

- A private static instance field (instance), which stores the single allowed instance.
- A private constructor, preventing external instantiation. 
- A public static method getInstance() that either creates or returns the single instance. 
- Methods for managing and printing the list of drinks.

```java
public class Menu {
    private static Menu instance;
    private List<Drink> menuItems;
    
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

```

All parts of the program — including factories, builders, and the client — interact with the same Menu object.
This ensures a unified and consistent list of drinks, avoiding duplication or data conflicts.

#### Example Usage (from Main)

```java
Menu menu = Menu.getInstance();

menu.add(new EspressoFactory().createDrink());
menu.add(new LatteFactory().createDrink());
menu.add(new CappuccinoFactory().createDrink());

menu.printMenu();
```
#### Output Example:
```
Espresso: 20.0
Latte: 40.0
Cappuccino: 50.0
-----------------------
```

-----------------------
### Factory Method
#### Purpose
The Factory Method pattern defines an interface for creating an
object but lets subclasses decide which class to instantiate. 
This allows the code to remain flexible and decoupled from 
specific implementations.

In my lab, the Factory Method is used to create different types of coffee drinks (Espresso, Cappuccino, Latte) without exposing the instantiation logic to the client. Each specific drink type has its own factory class responsible for creating that particular drink.

The abstract class DrinkFactory defines the method createDrink() that subclasses must implement.
```java
package domain.factory;

import domain.models.Drink;

public abstract class DrinkFactory {
    public abstract Drink createDrink();
}
```

Each concrete factory (e.g., EspressoFactory, CappuccinoFactory, LatteFactory) overrides this method to return a corresponding Drink object.

```java
package domain.factory;

import domain.models.Drink;
import domain.models.Espresso;

public class EspressoFactory extends DrinkFactory{
    @Override
    public Drink createDrink() {
        return new Espresso();
    }
}
```
```java
package domain.factory;

import domain.models.Drink;
import domain.models.Latte;

public class LatteFactory extends DrinkFactory{
    @Override
    public Drink createDrink() {
        return new Latte();
    }
}
```

```java
package domain.factory;

import domain.models.Cappuccino;
import domain.models.Drink;

public class CappuccinoFactory extends DrinkFactory{
    @Override
    public Drink createDrink() {
        return new Cappuccino();
    }
}
```

`Drink` is the base class (or “product”) in the Factory Method structure.
It defines common properties (name, price) and serves as the superclass for all concrete drinks.

```java
package domain.models;

public class Drink {
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
```

`Espresso` (or `Latte`, `Cappuccino`) extends `Drink`, calling the superclass constructor to set specific attributes like name and price.

```java
package domain.models;

public class Espresso extends Drink{
    public Espresso() {
        super("Espresso", 20);
    }
}
```

#### Example Usage (from Main)

```java
Menu menu = Menu.getInstance();

menu.add(new EspressoFactory().createDrink());
menu.add(new LatteFactory().createDrink());
menu.add(new CappuccinoFactory().createDrink());

menu.printMenu();
```
#### Output Example:
```
Espresso: 20.0
Latte: 40.0
Cappuccino: 50.0
-----------------------
```
-----------------------

### Builder Pattern 
#### Purpose
The Builder Pattern is used to construct complex objects step by step.
It allows you to create different representations of an object using the same construction process.

In my lab, the Builder Pattern was used to create customized coffee drinks — with flexible combinations of base type, size, syrup, toppings, ice, and whipped cream — while keeping the creation process organized and separated from the final product class.

I started by defining the `CustomDrinkBuilder` interface, which contains all the steps necessary to construct a coffee drink:

```java 
package domain.builder;

import domain.models.CustomDrink;
import domain.models.Drink;

public interface CustomDrinkBuilder {
    void setName(String name);
    void setPrice(double price);
    void setBase(Drink base);
    void setSize(Size size);
    void addSyrup(Syrup syrup);
    void addTopping(Topping topping);
    void addWhippedCream(boolean hasWhippedCream);
    void addIce(boolean hasIce);
    CustomDrink build();
}
```

I also created a new model called `CustomDrink`, which extends the base Drink class.
It contains additional fields such as the selected syrup, topping, size, and boolean flags for ice and whipped cream.
By doing this, I reused the existing Drink structure and extended it to represent complex beverages.

```java
public class CustomDrink extends Drink {
    private final Drink baseDrink;
    private final Size size;
    private final Syrup syrup;
    private final Topping topping;
    private final boolean hasIce;
    private final boolean hasWhippedCream;
}
```

Then, I implemented two concrete builders:

- `RegularDrinkBuilder`: for simpler drinks (no ice, no toppings, no whipped cream). 
- `SpecialtyDrinkBuilder`: for advanced, highly customizable beverages.

Each builder implements the same interface but behaves slightly differently depending on the type of drink being created.

To simplify the drink creation process, I introduced a Director class called `CoffeeDirector`, which defines preset recipes such as Caramel Latte or Iced Chocolate Mocha.
The Director encapsulates the building steps in a fixed order, ensuring that the final product is built consistently every time.

```java
public class CoffeeDirector {

    public CustomDrink makeCaramelLatte(CustomDrinkBuilder builder) {
        builder.setBase(new LatteFactory().createDrink());
        builder.setSize(MEDIUM);
        builder.addSyrup(CARAMEL);
        builder.setName("Caramel Latte");
        builder.setPrice(60);
        return builder.build();
    }

    public CustomDrink makeChocolateCappuccino(CustomDrinkBuilder builder) {
        builder.setBase(new CappuccinoFactory().createDrink());
        builder.setSize(SMALL);
        builder.addSyrup(CHOCOLATE);
        builder.setName("Chocolate Cappuccino");
        builder.setPrice(60);
        return builder.build();
    }
}
```

#### Example Usage 
In the main class, I used both builders (RegularDrinkBuilder and SpecialtyDrinkBuilder) through the CoffeeDirector.
The resulting drinks were then added to the Singleton Menu instance.
This shows how the Builder Pattern integrates smoothly with other creational patterns.

```java
CustomDrinkBuilder regularBuilder = new RegularDrinkBuilder();
CustomDrinkBuilder specialBuilder = new SpecialtyDrinkBuilder();

CoffeeDirector director = new CoffeeDirector();

menu.add(director.makeCaramelLatte(regularBuilder));
menu.add(director.makeChocolateCappuccino(specialBuilder));
menu.add(director.makeIcedChocolateMocha(specialBuilder));
menu.add(director.makeCinnamonCaramelLatte(specialBuilder));
menu.add(director.makePumpkinSpiceLatte(specialBuilder));

menu.printMenu();
```
#### Output Example:
```
Caramel Latte: 60.0
Chocolate Cappuccino: 60.0
Iced Chocolate Mocha: 65.0
Cinnamon Caramel Latte: 75.0
Pumpkin Spice Latte: 75.0
-----------------------
```

## Conclusion
In this laboratory, I applied three creational design patterns — Singleton, Factory Method, and Builder — to a coffee shop system. The Singleton ensured a single, consistent menu instance, the Factory Method simplified creating different base drinks, and the Builder allowed step-by-step construction of complex, customized beverages. Using these patterns together made the code more organized, flexible, and easy to extend, demonstrating the practical benefits of structured object creation.