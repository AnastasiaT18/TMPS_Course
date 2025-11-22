package domain.behavioural;


import domain.structural.composite.MenuComponent;

public interface Observer {
     void update(MenuComponent item, String action);

}
