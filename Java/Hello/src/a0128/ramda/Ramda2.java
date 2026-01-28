package a0128.ramda;

interface Calculator {
  int sum(int a, int b);
  // 인터페이스는 sum 추상메서드 하나만 가지고 있다.
  
}

public class Ramda2 {

  public static void main(String[] args) {
    // 익명(이름이 없는) 클래스
    Calculator mc = (a,  b) ->  a + b;
    int result = mc.sum(3, 4);
    System.out.println("result = " + result);
  }
}
