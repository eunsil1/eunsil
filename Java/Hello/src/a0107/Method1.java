package a0107;

public class Method1 {
  public static void main(String[] args) {
    // 메서드 사용이유
    // 코드 재사용
    // 가독성 향상
    // 유지보수 쉬움
    // 역할 분리(기능별 처리)

    int result = sum(10, 20);
    int result1 = sum(30,40);
    System.out.println(result);
    System.out.println(result1);
  }
    // static 객체 생성 없이 사용가능
   static int sum(int a, int b) {
    // int - 반환값의 자료형
    // sum - 메서드(함수) 이름
    // int a , int b - 매개 변수(입력값 2개)
    return a + b;
    // a + b를 더해서 결과를 호출하는 곳으로 되돌려줌 - return

  }
    
}
