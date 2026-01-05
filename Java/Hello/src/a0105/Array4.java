package a0105;

import java.util.Scanner;

public class Array4 {
  public static void main(String[] args) {
    // 사용자로부터 정수 3개를 입력 받아 저장 출력하시오.
    // for문 이용
    Scanner sc = new Scanner(System.in);
    // int a = sc.nextInt();
    // int b = sc.nextInt();
    // int c = sc.nextInt();
    
    int[] arr = new int[3];
    for(int i = 0; i < arr.length; i++){
      arr[i] = sc.nextInt();
      //System.out.println(arr[i]);
      //arr[0] = 10 arr[1] = 20 arr[2] = 30
      //역순으로 출력하려면
    }
    for(int i = arr.length-1; i >= 0; i--){
      System.out.println(arr[i]);
    }

  }
}
