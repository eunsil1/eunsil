package a0123.Map;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CoffeeMenu3 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    // 커피 메뉴 초기화
    HashMap<String, Integer> menu = new HashMap<>();
    menu.put("아메리카노", 4000);
    menu.put("카페라떼", 4500);
    menu.put("카푸치노", 5000);

    System.out.println("=== 초기 메뉴 ===");
    for (Map.Entry<String, Integer> entry : menu.entrySet()) {
      System.out.println(entry.getKey() + ": " + entry.getValue() + "원");
    }
    // 새메뉴 추가
    System.out.print("\n추가할 메뉴 이름 : ");
    String newMenu = scanner.nextLine();
    System.out.print("가격 : ");
    int newPrice = scanner.nextInt();
    scanner.nextLine(); // 버퍼비우기

    menu.put(newMenu, newPrice);
    System.out.println(newMenu + " 메뉴가 추가됬습니다.");
    System.out.println("=== 추가후 메뉴 ===");
    for (Map.Entry<String, Integer> entry : menu.entrySet()) {
      System.out.println(entry.getKey() + ": " + entry.getValue() + "원");
    }

    // 기존가격 수정
    System.out.print("\n수정할 메뉴 이름 : ");
    String updateMenu = scanner.nextLine();
    if (menu.containsKey(updateMenu)) {
      System.out.print("새 가격 : ");
      int updatePrice = scanner.nextInt();
      menu.put(updateMenu, updatePrice);
      System.out.println(updateMenu + "의 가격이 " + updatePrice + "원으로 수정");
    } else {
      System.out.println("해당 메뉴가 없어요");
    }
    System.out.println("=== 최종 메뉴 ===");
    for (Map.Entry<String, Integer> entry : menu.entrySet()) {
      System.out.println(entry.getKey() + ": " + entry.getValue() + "원");
    }

  }
}
