package a0113.abstract1;

public class Dog extends Animal{

  public Dog(String name) {
    super(name);
  }

  @Override
  void makeSound() {
    System.out.println(name + "이(가) 멍멍 짖습니다.");
  }

  
  

}
