package a0113.abstract1;

public class AnimalMain {
  public static void main(String[] args) {
    // Animal animal = new Animal("동물"); 객체 생성 불가
    Animal dog = new Dog("뽀삐"); //다양하게 저장할수 있음 - 다형성
    Animal cat = new Cat("나비");

    dog.eat();
    dog.makeSound();

    cat.eat();
    cat.makeSound();
  }
}
