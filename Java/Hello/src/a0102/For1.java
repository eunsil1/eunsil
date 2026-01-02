package a0102;

public class For1 {
  public static void main(String[] args) {
    for (int i=1; i<=10; i++) {
      System.out.println(i);
    }

    for (int j = 10; j >= 1; j--) {
      System.out.println(j);
    }

    for (int k = 0; k <= 10; k+=2) {//k=k+2
      System.out.println(k);
    }

    int sum = 0; //밖에서 선언을 해줘야 누적값이 저장되며, for문 안에서 선언하게되면 0으로 초기화됨
    for (int m = 1; m <= 10; m++) {
      sum = sum + m;
    }
    System.out.println("1~10 합: " + sum);
    // 최종 결과 값을 출력하려면 for문 밖에서 출력해야함.
  

  // 1~20까지의 짝수 출력
    for (int i = 1; i <= 20; i++) {
      if (i % 2 == 0) {
        System.out.println(i);
        // for문 안에서 쓰인 변수 i는 for문 안에서만 유효함.
      }
    }
    
    System.out.println("=========구구단 2단=========");
      for(int j = 1; j <= 9; j++)
      System.out.println("2 X " + j + " = " + (2 * j));

    // 변수 선언 위치
    // for문 바깥에서 선언하면 for문 종료 후에도 사용 가능
    int j;
    for(j=1; j<=9; j++){
      System.out.print(j + " "); //print는 옆으로 출력
    }
    System.out.println("\nfor문 종료 후 j값: " +j);
    }
}
