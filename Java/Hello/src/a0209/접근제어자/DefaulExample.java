package a0209.접근제어자;

class DefaultClass {
  void display() {// default void display(){} 인데 default가 생략됨
    System.out.println("패키지[폴더]() 내부에서만 접근 가능");
  }
}

public class DefaulExample {
  public static void main(String[] args) {
    
  }
}

//public 어디서나 접근 가능
//protected 같은 패키지 + 다른 패키지의 자식