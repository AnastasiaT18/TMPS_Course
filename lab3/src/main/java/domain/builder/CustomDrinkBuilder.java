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

    enum Size{
        SMALL, MEDIUM, LARGE
    }

    enum Syrup{
        VANILLA, CARAMEL, CHOCOLATE, PUMPKIN_SPICE, COCONUT, COOKIE, BERRY, POPCORN, HAZELNUT, SALTY_CARAMEL, NONE
    }

    enum Topping {
        CINNAMON, COCOA, BROWN_SUGAR, CARAMEL_DRIZZLE, MARSHMALLOWS, NONE
    }
}

