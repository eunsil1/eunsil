package a0113.abstract1;

public class Cat extends Animal{

  public Cat(String name) {
    super(name);
  }

  @Override
  void makeSound() {
    System.out.println(name + "이(가) 야옹 웁니다.");
  }
  
}
