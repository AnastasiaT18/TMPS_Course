package domain.models;

import domain.builder.CustomDrinkBuilder.Size;
import domain.builder.CustomDrinkBuilder.Syrup;
import domain.builder.CustomDrinkBuilder.Topping;

public class CustomDrink extends Drink{
    private final Drink baseDrink;
    private final Size size;
    private final Syrup syrup;
    private final Topping topping;
    private final boolean hasIce;
    private final boolean hasWhippedCream;

    public CustomDrink(String name, double price, Drink base, Size size, Syrup syrup, Topping topping, boolean hasIce, boolean hasWhippedCream) {
        super(name, price);
        this.baseDrink = base;
        this.size = size;
        this.syrup = syrup;
        this.topping = topping;
        this.hasIce = hasIce;
        this.hasWhippedCream = hasWhippedCream;
    }

    public Drink getBaseDrink() {
        return baseDrink;
    }

    public Size getSize() {
        return size;
    }

    public Syrup getSyrup() {
        return syrup;
    }

    public Topping getTopping() {
        return topping;
    }

    public boolean hasIce() {
        return hasIce;
    }

    public boolean hasWhippedCream() {
        return hasWhippedCream;
    }
}
