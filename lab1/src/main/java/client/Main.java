package client;

import domain.singleton.Menu;

public class Main {
    public static void main(String[] args) {

        Menu menu1 = Menu.getInstance();
        Menu menu2 = Menu.getInstance();

        menu1.printMenu();
        menu2.printMenu();

        System.out.println(menu1 == menu2 ? "Same menu" : "Different menu");
    }
}
