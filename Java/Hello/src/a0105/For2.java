package a0105;

public class For2 {
  public static void main(String[] args) {
    int sum = 0;
    for(int i = 1; i <= 100; i++){
      // sum = sum + i;
      sum += i;
      // i = 1 sum = 0 -> sum = 1
      // i = 2 sum = 1 -> sum = 3
      // i = 3 sum = 3 -> sum = 6
    }
    System.out.println("1~100까지 합은 : " + sum);
  }
}
