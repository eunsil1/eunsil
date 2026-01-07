package a0107;

public class Ex3 {
  public static void main(String[] args) {
    int x = 5;
    System.out.println("x = " + x);
    print(x);
    System.out.println("x = " + x);
  }

  private static void print(int x) {
    System.out.println("x = "+ x);
    x = x + 10;
    System.out.println("x = " + x);
  }
}
