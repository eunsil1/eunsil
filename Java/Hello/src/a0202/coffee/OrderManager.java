package a0202.coffee;

import java.util.HashMap;
import java.util.Map;

public class OrderManager {
  private Map<String, Integer> orders;
  private Menu menu;

  public OrderManager(Menu menu){
    this.orders = new HashMap<>();
    this.menu = menu;
  }
}
