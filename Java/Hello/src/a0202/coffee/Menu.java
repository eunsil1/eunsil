package a0202.coffee;

import java.util.HashMap;
import java.util.Map;

public class Menu {
  private Map<String, Coffee> menuMap;
  
  public Menu(){
    menuMap = new HashMap<>();
    // initializeMenu();
  }

  // private void initializeMenu() {
  //   menuMap.put("Americano",new Coffee("Americano",3000));
  //   menuMap.put("Latte", new Coffee("Latte", 4000));
  //   menuMap.put("Mocha", new Coffee("Mocha", 4500));
  //   menuMap.put("Espresso", new Coffee("Espresso", 2500));
  // }
}
