package client;

import domain.structural.facade.CoffeeShopFacade;


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
