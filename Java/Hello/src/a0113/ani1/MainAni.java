package a0113.ani1;

public class MainAni extends Object {
  public static void main(String[] args) {
    Dog dog = new Dog("뽀삐",3, "골든리트리버");
    Cat cat = new Cat("나비",2,"검은색");

    // 부모클래스의 메서드 사용 가능
    dog.eat(); //상속받은 메서드
    dog.sleep();
    dog.makeSound(); //오버라이딩한 메서드
    dog.bark(); //자식클래스만의 메서드
    dog.displayInfo();

    System.out.println();

    cat.eat();
    cat.sleep();
    cat.makeSound();
    cat.Scratch();
    cat.displayInfo();
  }
}
// 상속의 특징
// 단일상속만 가능 : 자바는 하나의 부모클래스만 상속 가능
// super 키워드 : 부모클래스의 생성자, 메서드, 필드에 접근 가능
// 메서드 오버라이딩 : 자식클래스에서 부모클래스 재정의
// -도그- makeSound(), displayInfo()
// 모든 클래스는 Object 클래스 상속 
