package a0123.Map;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CoffeeMenu4 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
        
        // 커피 메뉴 초기화
        HashMap<String, Integer> menu = new HashMap<>();
        menu.put("아메리카노", 4000);
        menu.put("카페라떼", 4500);
        menu.put("카푸치노", 5000);
        menu.put("에스프레소", 3500);
        menu.put("바닐라라떼", 5500);
        menu.put("카라멜마키아토", 6000);
        menu.put("콜드브루", 5500);

        // 주문 내역 저장
        HashMap<String, Integer> order = new HashMap<>();


        while (true) {
          // 메뉴 출력
            System.out.println("\n=== 커피 메뉴 ===");
            for (Map.Entry<String, Integer> entry : menu.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue() + "원");
            }
            System.out.print("\n주문할 메뉴를 입력하세요 (종료: '종료'): ");
            String coffeeName = scanner.nextLine();

            if (coffeeName.equals("종료")) {
              break;
            }
            if (!menu.containsKey(coffeeName)) {
              System.out.println("해당 메뉴가 없습니다. 다시 입력해 주세요");
              continue;
            }

            //수량 입력
            System.out.print("수량을 입력하세요: ");
            int quantity = scanner.nextInt();
            scanner.nextLine();

            if (quantity <= 0) {
              System.out.println("수량은 1개 이상이어야 합니다");
              continue;
            }

            // 아메리카노 2
            // 주문 내역에 추가(이미 주문한 메뉴면 수량 추가)
            if (order.containsKey(coffeeName)) {
              order.put(coffeeName, order.get(coffeeName) + quantity);
              // order.get(coffeeName)수량(기존값) + quantity(새로 입력한 수량)
            }else{
              order.put(coffeeName,quantity);
            }
            int price = menu.get(coffeeName);
            System.out.println(coffeeName + " " + quantity + "개가 주문되었습니다. (금액: " + (price * quantity) + "원)");
        }
        // 주문내역 및 총금액
        System.out.println("\n=== 주문 내역 ===");
        int totalAmount = 0;
        int totalQuantity = 0;
        for (Map.Entry<String, Integer> entry : order.entrySet()) {
          // System.out.println(entry.getKey() + ": " + entry.getValue() + "원");
          String coffeeName = entry.getKey();
          int quantity = entry.getValue(); //지역변수
          int price = menu.get(coffeeName); //메뉴 가격
          int subTotal = price * quantity;
          System.out.println(coffeeName + " X " + quantity + " = " + subTotal);
          totalAmount += subTotal; //개개별 상품 총액을 전체 금액에 누적
          totalQuantity += quantity;
        }
        System.out.println("\n총 금액: " + totalAmount + "원");
        scanner.close();

        // 평균 금액
        if (totalQuantity > 0) {
         double averageAmount = (double)totalAmount / totalQuantity;
         System.out.printf("평균 금액: %.2f원\n", averageAmount);
        }
        
        
  }
}
