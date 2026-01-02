package a0102;

public class Gugudan {
  public static void main(String[] args) {
    // 전체 구구단
    for(int dan = 2; dan <= 9; dan++){
      System.out.println("====" + dan + "단====");
       for(int i=1; i<=9; i++){
        System.out.println(dan + " X " + i + " = " + (dan * i));
       }
       System.out.println();//단 사이 줄바꿈
    }
    // 바깥 for 문 : 2~9단
    // 안쪽 for 문 : 각 단의 1~9 곱하기

    // 가로 구구단 출력
    for (int i = 1; i <= 9; i++) {
      for (int dan = 2; dan <= 9; dan++) {
        System.out.print(dan + " X " + i + " = " + (dan * i) + "\t");
      }
      System.out.println();
    }
  }
}


  
  