package a0123.Map;

import java.util.HashMap;
import java.util.Scanner;

public class CoffeeMenu2 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    HashMap<String, Integer> menu = new HashMap<>();
    menu.put("아메리카노", 4000);
    menu.put("카페라떼", 4500);
    menu.put("카푸치노", 5000);
    menu.put("에스프레소", 3500);
    menu.put("바닐라라떼", 5500);
    menu.put("카라멜마키아토", 6000);

    // 메뉴 가격 조회
    System.out.print("조회할 커피 메뉴를 입력하세요 > ");
    String coffeeName = scanner.nextLine();

    if (menu.containsKey(coffeeName)) {
      int price = menu.get(coffeeName); //커피 이름에 해당하는 메뉴 가격
      System.out.println(coffeeName + "의 가격은: " + price + "원입니다.");
    }else{
      System.out.println("해당 메뉴가 없습니다");
    }
    scanner.close();

  }
}