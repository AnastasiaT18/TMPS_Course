package domain.builder;

import domain.factory.CappuccinoFactory;
import domain.factory.EspressoFactory;
import domain.factory.LatteFactory;
import domain.models.CustomDrink;
import domain.models.Espresso;
import domain.models.Latte;

import static domain.builder.CustomDrinkBuilder.Size.*;
import static domain.builder.CustomDrinkBuilder.Syrup.*;
import static domain.builder.CustomDrinkBuilder.Topping.*;



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

    public CustomDrink makeIcedChocolateMocha(CustomDrinkBuilder builder) {
        builder.setBase(new EspressoFactory().createDrink());
        builder.setSize(LARGE);
        builder.addSyrup(CHOCOLATE);
        builder.addIce(true);
        builder.addWhippedCream(true);
        builder.addTopping(COCOA);
        builder.setName("Iced Chocolate Mocha");
        builder.setPrice(65);
        return builder.build();
    }

    public CustomDrink makeCinnamonCaramelLatte(CustomDrinkBuilder builder) {
        builder.setBase(new LatteFactory().createDrink());
        builder.setSize(LARGE);
        builder.addSyrup(CARAMEL);
        builder.addIce(false);
        builder.addWhippedCream(true);
        builder.addTopping(CINNAMON);
        builder.setName("Cinnamon Caramel Latte");
        builder.setPrice(75);
        return builder.build();
    }

    public CustomDrink makePumpkinSpiceLatte(CustomDrinkBuilder builder) {
        builder.setBase(new LatteFactory().createDrink());
        builder.setSize(LARGE); // you can choose SMALL, MEDIUM, LARGE
        builder.addSyrup(PUMPKIN_SPICE); // if you added it to the enum
        builder.addWhippedCream(true);
        builder.addTopping(CINNAMON);
        builder.addIce(false); // usually served hot
        builder.setName("Pumpkin Spice Latte");
        builder.setPrice(75);
        return builder.build();
    }
}
