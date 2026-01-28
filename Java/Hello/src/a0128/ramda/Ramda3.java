package a0128.ramda;

interface Calculator1 {
  int sum(int a, int b);
  // 인터페이스는 sum 추상메서드 하나만 가지고 있다.
  
}

public class Ramda3 {

  public static void main(String[] args) {
    // 익명(이름이 없는) 클래스
    Calculator1 mc = Integer::sum;
    // (a,b) -> a + b
    // Integer::sum 이미 만들어진 메서드 (sum) 를 이름으로 연결
    // 메서드 참조 방식
    // Integer::max 큰수(최대값)
    // Integer::min 작은수(최소값)
    int result = mc.sum(3, 4);
    System.out.println("result = " + result);
  }
}
