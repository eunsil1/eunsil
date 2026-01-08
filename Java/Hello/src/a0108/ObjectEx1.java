package a0108;

public class ObjectEx1 {
  public static void main(String[] args) {
    Person p1 = new Person();
    p1.name = "홍길동";
    p1.age = 20;
    p1.sayHello();

    Person p2 = new Person();
    p2.name = "김철수";
    p2.age = 25;
    p2.sayHello();
    // 클래스 ≠ 객체
    // 객체는 new로 생성
    // 객체는 여러개 생성 가능
    // 객체는 값이 독립적
  }
}

// public은 바깥쪽에 동시에 존재할 수 없음, 접근제어자 쓰면 안됨
// 클래스(Class) -> 객체를 만들기 위한 설계도(붕어빵 기계)
// 객체(Object) -> 설계도로 만든 실제 물건(붕어빵)

// 비유
// 클래스 -> 집 설계도
// 객체 -> 실제 지어진 집
// 변수 -> 집의 속성(방 개수, 벽지 색상 등)
// 메서드 -> 집의 기능(문열기, 불켜기 등)

class Person {
  String name; //멤버변수(필드)
  int age;
  void sayHello(){ //메서드
    System.out.println("안녕하세요, 저는 " + name + "입니다.") ;
  }


}
