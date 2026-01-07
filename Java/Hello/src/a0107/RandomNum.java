package a0107;

public class RandomNum {
  public static void main(String[] args) {
    int n = rollDie();
    System.out.printf("주사위의 눈: %d",n);
  }

  private static int rollDie() {
   double x = 6 * Math.random(); //결과값 실수로 나옴
   int temp = (int) x; //형변환(다운캐스팅) -> 소수점제거
   return temp + 1;
  }
}
