package a0105;

import java.util.Random;

public class Lotto {
  public static void main(String[] args) {
      // 1. 배열 초기화(1~45)
      int[] lotto = new int[45];
      for(int i = 0; i < lotto.length; i++){
        lotto[i] = i + 1;
      }
      // index :  0   1   2   3   4  ...
      // value :  1   2   3   4   5  ...


        // 2. 섞기
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
          int n = random.nextInt(45); // 0 ~ 44까지
          // 1000번 순환

          // 0번칸과 n번칸의 값을 바꿈(Swap)
          int temp = lotto[0];
          lotto[0] = lotto[n];
          lotto[n] = temp;
           

          // index : 0 1 2 3 4 ...
          // value : 1 2 3 4 5 ...
        }
        // 출력
        System.out.println("셔플 결과 : ");
        for(int i = 0; i < 6; i++){
          System.out.print(lotto[i] + " ");
        }
  }
}
