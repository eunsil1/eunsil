package a0202.coffee2;

import java.util.Map;

public class Customer {
  private int orderNum;//고객의 주문번호
  private int money;//고객이 가지고 있는 돈을 저장
  private Map<String, Integer> coffeeOder;//커피주문

  public Customer(int orderNum) {
    this.orderNum = orderNum;
    this.money = 20000; //초기값 설정
  }

  public int getOrderNum() {
    return orderNum;
  }

  public void setOrderNum(int orderNum) {
    this.orderNum = orderNum;
  }

  public int getMoney() {
    return money;
  }

  public void setMoney(int money) {
    this.money = money;
  }

  public Map<String, Integer> getCoffeeOder() {
    return coffeeOder;
  }

  public void setCoffeeOder(Map<String, Integer> coffeeOder) {
    this.coffeeOder = coffeeOder;
  }

  // 고객1, 고객2 ..... 하게 늘어나는 구조
  public String getOrderName(){
    return "고객 " + orderNum;
  }
  
  
}
