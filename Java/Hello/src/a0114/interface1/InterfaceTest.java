package a0114.interface1;

public class InterfaceTest {
  public static void main(String[] args) {
    
    Flyable f1 = new Bird();
    Flyable f2 = new AirPlane();

    f1.fly();
    f2.fly();
  }
}

// 추상과 인터페이스 차이
// 키워드 - 추상 -> abstract, 인터페이스 -> interface
// 상속 및 구현 - 추상 -> extends, 인터페이스 -> implements
// 다중상속 - 추상 -> 1개만 가능, 인터페이스 -> 여러개 가능
// 필드 - 추상 -> 일반변수, 인터페이스 -> public static final 상수만 가능
// 메서드 - 추상 -> 일반 + 추상 모두 가능, 인터페이스 -> 추상 메서드만 가능
// 생성자 - 추상 -> 있음, 인터페이스 -> 없음
// 목적 - 추상 -> 기능 + 규칙, 인터페이스 -> 규칙만(표준)
