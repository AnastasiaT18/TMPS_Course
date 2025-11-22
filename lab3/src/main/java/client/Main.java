package client;

import domain.structural.facade.CoffeeShopFacade;


public class Main {
    public static void main(String[] args) {

        CoffeeShopFacade coffeeShop = new CoffeeShopFacade();

        System.out.println("LOGS: Preparing the menu...");

        coffeeShop.prepareBasicMenu();
        coffeeShop.prepareSpecialtyMenu();

        coffeeShop.prepareCustomDrink();
        coffeeShop.createCombos();

        coffeeShop.showMenu();

        System.out.println("\nLOGS: Removing last item...");
        coffeeShop.refreshMenuItems();

        coffeeShop.showMenu();

        System.out.println("Student came in...");
        coffeeShop.showStudentMenu();


        System.out.println("Student left:");
        coffeeShop.showMenu();

    }
}
