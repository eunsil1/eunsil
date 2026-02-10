package a0210.casting;


// 정의 : 자식 클래스 객체를 부모 클래스 타입으로 변환하는것
public class Upcasting1 {
  public static void main(String[] args) {
    Animal animal = new Dog();
    animal.sound();
  }
}

class Animal{
  void sound(){
    System.out.println("동물 소리");
  }
}

class Dog extends Animal{
  void sound(){
    System.out.println("멍멍");
  }
}
