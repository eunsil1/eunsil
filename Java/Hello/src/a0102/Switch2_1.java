package a0102;
import java.util.Scanner;

public class Switch2_1 {
  public static void main(String[] args) {

    // 숫자 입력 -> 요일 출력

    Scanner sc = new Scanner(System.in);
    System.out.print("요일 번호 입력(1~7)");
    
    int day = sc.nextInt();

    switch (day) {
      case 1:
        System.out.println("월요일");
        break;
      case 2:
        System.out.println("화요일");
        break;
      case 3:
        System.out.println("수요일");
        break;
      case 4:
        System.out.println("목요일");
        break;
      case 5:
        System.out.println("금요일");
        break;
      default:
        System.out.println("주말");
        break;
    }
  }
}
