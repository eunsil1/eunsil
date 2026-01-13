package a0113.ani1;

public class Cat extends Animal {

  String color;

  public Cat(String name, int age, String color) {
    super(name, age);
    this.color = color;
  }

  @Override
  public void makeSound() {
    System.out.println(name + "이(가) 야옹 웁니다.");
  }

  public void Scratch(){
    System.out.println(name + "이(가) 할퀴고 있습니다.");
  }
  
  

}
