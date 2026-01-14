package a0114.abstract1;

public class AbstractMain {
  public static void main(String[] args) {
    
    Animal dog = new Dog("뽀삐"); //Animal을 상속받았기 때문에 Dog로 객체 생성 가능
    Animal cat = new Cat("나비");

    dog.eat();
    dog.sound();

    cat.eat();
    cat.sound();
    
  }
}

// 추상 클래스
// 공통 상태(name(필드))가 필요할때
// 공통 기능(sound) 코드를 물려주고 싶을때 사용