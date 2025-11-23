# Laboratory Work #3 – Behavioral Design Patterns
Author: Anastasia Tiganescu

## Objectives:
1. Study and understand the Behavioral Design Patterns.

2. As a continuation of the previous laboratory work, think about what communication between software entities might be involed in your system.

3. Implement some additional functionalities using behavioral design patterns.


## Main tasks:
1. By extending your project, implement at least 1 behavioral design pattern in your project:
   - The implemented design pattern should help to perform the tasks involved in your system.
   - The behavioral DPs can be integrated into you functionalities alongside the structural ones.   
   - There should only be one client for the whole system.
2. Keep your files grouped (into packages/directories) by their responsibilities (an example project structure):
3. Document your work in a separate markdown file according to the requirements presented below (the structure can be extended of course):

## Chosen Domain

The chosen domain remains the Coffee Shop Management System, which models how drinks are created, customized, and presented to customers inside a digital café environment.

In this laboratory work, the focus shifts from the structural organization of drinks and menu items to the behaviour and interaction of system components. Instead of concentrating on how objects are built or combined, this lab explores how they communicate, react to changes, and adapt their behaviour at runtime.
## Used Behavioural Patterns

- Observer Pattern – to monitor menu changes and automatically notify listeners when items are added or removed.

- Strategy Pattern – to dynamically switch between different pricing rules (regular pricing, student discount, happy hour discount), depending on context such as time of day or user type.

## Implementation

### Observer Pattern 
#### Purpose

The Observer Pattern provides a way for objects to “listen” and react whenever another object changes.
Instead of manually printing logs everywhere, the system now automatically logs menu updates when items are added or removed.

This makes the communication between components cleaner, more maintainable, and decoupled.

#### Implementation

The Observer interface defines a simple update method:

```java
public interface Observer {
   void update(MenuComponent item, String action);

}
```

A concrete observer MenuLogger listens to the menu:

```java
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
```

In order to apply the Observer pattern, I extended the Menu class with:

1. A list of observers: 

```java
private List<Observer> observers;
```

2. New methods to manage and notify observers:

```java
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
```

3. Integration of observers inside menu modification methods:

```java
 public void add(MenuComponent item) {

   menuItems.add(item);
   notifyObservers(item, "added");
}

public void remove(MenuComponent item) {
   menuItems.remove(item);
   notifyObservers(item, "removed");
}
```

#### Output Example:


```
LOGS: Preparing the menu...
        -New menu item added: Espresso - 20.0
        -New menu item added: Latte - 40.0
```

-----------------------
### Strategy Pattern
#### Purpose
The Strategy Pattern provides a way to select different algorithms or behaviors at runtime, without modifying the class that uses them.
In this project, the Strategy Pattern is used to dynamically switch between different pricing and menu-display rules depending on:

- whether the user is a student (student discount),

- whether it is Happy Hour (time-based discount),

- or neither (regular prices).

This allows the system to adapt automatically to different situations (time of day or user type) while keeping the Menu class clean and free of large if-else blocks.
#### Implementation

All pricing strategies implement the same method printMenu(), which defines how menu items should be displayed and priced:
```java
public interface Strategy {
   void printMenu(List<MenuComponent> items);
}
```
Each strategy defines its own pricing logic:

```java
public class StudentStrategy implements Strategy {
   @Override
   public void printMenu(List<MenuComponent> items) {
      for (MenuComponent item : items) {
         System.out.println(item.getName() + ".........." + item.getPrice()*0.5+" (was  " + item.getPrice() + ")");
      }
      System.out.println("-----------------------");
   }
}
```

```java
public class HappyHourStrategy implements Strategy {
   @Override
   public void printMenu(List<MenuComponent> items) {
      for (MenuComponent item : items) {
         System.out.println(item.getName() + ".........." + item.getPrice()*0.8+" (was  " + item.getPrice() + ")");
      }
      System.out.println("-----------------------");
   }
}
```

```java
public class RegularStrategy implements Strategy {

   @Override
   public void printMenu(List<MenuComponent> items) {
      for(MenuComponent item : items) {
         item.print();
      }
   }
}
```

To support Strategy, the Menu class was extended with:

1. A strategy field: 

```java
private Strategy strategy; 
```

2. A setter: 

```java
public void setStrategy(Strategy strategy) {
   this.strategy = strategy;
}
```

3. A redesigned printMenu() method:

```java
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

```



#### Example Usage 

In the Main class, the client can choose which menu to display simply by calling a specific function:


```
   System.out.println("Student came in...");
        coffeeShop.showStudentMenu();


        System.out.println("Student left:");
        coffeeShop.showMenu();
```

CoffeeShopFacade was also adapted to fit the strategy pattern:

```java 
  public void showMenu() {
   menu.printMenu(null);
}

public void showStudentMenu(){
   menu.printMenu("student");
}
```

#### Output Example:
```
==================== STUDENT MENU (50% discount) ====================
Espresso..........10.0 (was  20.0)
Latte..........20.0 (was  40.0)
Cappuccino..........25.0 (was  50.0)
Caramel Latte..........30.0 (was  60.0)
Chocolate Cappuccino..........30.0 (was  60.0)
Iced Chocolate Mocha..........32.5 (was  65.0)
Cinnamon Caramel Latte..........37.5 (was  75.0)
Pumpkin Spice Latte..........37.5 (was  75.0)
Latte + COCONUT Syrup + WhippedCream (iced) + MARSHMALLOWS Topping..........27.0 (was  54.0)
-----------------------
Student left:

==================== MENU ====================
Espresso..........20.0
Latte..........40.0
Cappuccino..........50.0
Caramel Latte..........60.0
Chocolate Cappuccino..........60.0
Iced Chocolate Mocha..........65.0
Cinnamon Caramel Latte..........75.0
Pumpkin Spice Latte..........75.0
Latte + COCONUT Syrup + WhippedCream (iced) + MARSHMALLOWS Topping..........54.0
```
-----------------------



## Conclusion
In this laboratory work, the Coffee Shop Management System was extended with behavioural design patterns that enhance communication, flexibility, and runtime adaptability between system components.

The Observer Pattern introduced an event-based mechanism that automatically reports changes to the menu. Whenever a drink or combo is added or removed, registered observers (such as the MenuLogger) are immediately notified. This eliminated the need for manual logging inside business logic, reduced coupling, and improved system transparency.

The Strategy Pattern allowed the pricing logic to be fully decoupled from the Menu class. Depending on the context — whether a student requests the menu or whether the time falls within Happy Hour — the system switches strategies seamlessly. This provides clean, scalable runtime behaviour without cluttering the code with conditional logic.

Together, these behavioural patterns improved the modularity, maintainability, and extensibility of the system. The Coffee Shop project now not only supports advanced structural composition of drinks and combos, but also demonstrates intelligent runtime decisions and inter-object communication — forming a more complete and realistic software architecture.