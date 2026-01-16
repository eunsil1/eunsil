package a0116;

public class Sample2 {
  public static void main(String[] args) {
    int c ;
    try {
      c = 4 / 2;
    } catch (ArithmeticException e) {
      c = -1; //예외가 발생하면 이 문자 수행
    }

    System.out.println(c);
  }
}
