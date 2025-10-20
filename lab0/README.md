# Laboratory Work #0 – SOLID 

### Task
Implement at least 3 SOLID principles in a simple project.

### Project Idea
I made a small **Coffee Shop** example that follows **3 SOLID principles**:
- **SRP (Single Responsibility Principle)**
- **OCP (Open/Closed Principle)**
- **DIP (Dependency Inversion Principle)**

Everything is written in one Java file inside the `src` package.

---

## S – Single Responsibility Principle
Each class has only one job.  
For example:
- `Drink` represents one drink
- `Order` handles adding drinks and calculating the total
- `OrderPrinter` is only for printing the order

```java
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
```

## O – Open for Extension, Closed for Modification
New drink types can be added without changing existing code.
I used the DrinkMaker interface, and new classes (like LatteMaker, CappuccinoMaker) just implement it.

```java
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

```
## D – Dependency Inversion Principle
The Barista class depends on the DrinkMaker interface, not on specific drink types.
This makes it easy to switch or add new drinks later.

```java
class Barista {
private final DrinkMaker drinkMaker;

    public Barista(DrinkMaker drinkMaker) {
        this.drinkMaker = drinkMaker;
    }

    public Drink prepareDrink() {
        return drinkMaker.make();
    }
}
```
## Example Run
Below is the main method that shows how everything connects together.

```java
public static void main(String[] args) {
    Barista cappuccinoBarista = new Barista(new CappuccinoMaker());
    Barista latteBarista = new Barista(new LatteMaker());
    
        Order order = new Order();
        order.addDrink(cappuccinoBarista.prepareDrink());
        order.addDrink(latteBarista.prepareDrink());
    
        OrderPrinter printer = new OrderPrinter();
        printer.printOrder(order);
}
```

- Two Barista objects are created, each using a different DrinkMaker (Cappuccino and Latte).
- Both drinks are added to an Order object.
- The OrderPrinter prints all drinks and the total price.
- The program shows that the system follows SOLID:
  - Each class has one job (SRP)
  - We could easily add more drinks (OCP)
  - The Barista works with abstractions, not specific drinks (DIP)

## Output
```
------- Order details -------
- Cappuccino: $3.0
- Latte: $3.5
  Total: $6.5
-----------------------------
```
## Conclusion
This small project demonstrates how the SOLID principles make code more organized and flexible.
By separating responsibilities, depending on interfaces, and keeping the system open for extensions, the code becomes easier to modify and reuse later.
Even though the example is simple, it shows how these ideas can be applied to any larger project.