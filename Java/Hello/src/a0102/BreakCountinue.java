package a0102;

public class BreakCountinue {
  public static void main(String[] args) {
    System.out.println("==break문 : 반복문 종료==");
    System.out.println("1~10까지 출력하다가 5에서 종료");
    for(int i = 1; i <= 10; i++){
      if (i == 5){
        break; //반복문 종료
      }
      System.out.println(i);
    }
    System.out.println();

    System.out.println("==countinue문 : 현재 반복문 건너뛰기==");
    System.out.println("1~10까지 출력하다가 5는 건너뛰기");
    for (int i = 1; i <= 10; i++) {
      if (i == 5) {
        continue; // 현재 반복 건너뛰고 다음 반복 계속
      }
      System.out.println(i);
    }
    System.out.println();
  }
}
