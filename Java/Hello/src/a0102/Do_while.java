package a0102;

public class Do_while {
  public static void main(String[] args) {
    System.out.println("\n===do~while문===");
    int k = 10;
    do{
      System.out.println("Hello world " + k);
      k++;
    } while(k <= 5);
  }
  // 조건을 나중에 검사하기 때문에 최소 1번은 실행됨
  
}
