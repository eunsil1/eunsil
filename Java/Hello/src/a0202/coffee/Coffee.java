package a0202.coffee;

public class Coffee {
  private String name;
  private int price;
  
  public Coffee(String name, int price) {
    this.name = name;
    this.price = price;
  }

  public String getName() {
    return name;
  }

  public int getPrice() {
    return price;
  }

  @Override
  public String toString() {
    return name + "- " + price + "원";
  }

  
}
