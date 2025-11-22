package domain.structural.composite;

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
        System.out.println(this.getName() + ".........." + this.getPrice());
    }
}
