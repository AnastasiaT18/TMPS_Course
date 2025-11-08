package domain.builder;

import domain.models.CustomDrink;
import domain.models.Drink;

public class RegularDrinkBuilder implements CustomDrinkBuilder{
    private String name;
    private double price;
    private Drink baseDrink;
    private Size size;
    private Syrup syrup;
    private Topping topping = Topping.NONE;
    private boolean hasIce = false;
    private boolean hasWhippedCream = false;

    @Override
    public void setName(String name){
        this.name = name;
    }

    @Override
    public void setBase(Drink base) {
        this.baseDrink = base;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public void setSize(Size size) {
        this.size = size;
    }


    @Override
    public void addSyrup(Syrup syrup) {
        this.syrup = syrup;
    }

    @Override
    public void addTopping(Topping topping) {
    }

    @Override
    public void addWhippedCream(boolean hasWhippedCream) {
    }

    @Override
    public void addIce(boolean hasIce) {
    }

    @Override
    public CustomDrink build() {
        return new CustomDrink(name, price, baseDrink, size, syrup, topping, hasIce, hasWhippedCream);
    }
}
