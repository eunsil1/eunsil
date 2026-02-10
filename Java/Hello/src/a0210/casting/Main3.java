package a0210.casting;

public class Main3 {
  public static void main(String[] args) {
    Parent1 p = new Child1();
    p.method();
    //부모 -> 자식 순서
    // Parent 생성자
    // Child 생성자
    // 메서드(오버라이딩) -> 실제 객체기준으로 실행
  }
}

class Parent1 {
  Parent1(){
    System.out.println("Parent 생성자");
  }

  void method(){
    System.out.println("Parent method");
  }
}

class Child1 extends Parent1{
  Child1(){
    System.out.println("Child 생성자");
  }

  void method(){
    System.out.println("Child method");
  }
  // 오버라이딩 된 메서드 실제 객체(Child) 기준으로 결정
}

// 한줄암기
// 부모타입 = 자식 객체 O
// 자식타입 = 부모 객체 X
// 메서드실행 = 실제 객체 기준

// Parent p = new Child(); // 업캐스팅 (자동)
// Child c = (Child) p; // 다운캐스팅 (명시)