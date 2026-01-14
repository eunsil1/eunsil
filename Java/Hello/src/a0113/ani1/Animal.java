package a0113.ani1;

public class Animal {
  String name;
  int age;

  public Animal(String name, int age) {
    this.name = name;
    this.age = age;
  }

  public void eat(){
    System.out.println(name + "이(가) 먹습니다.");
  }
  
  public void sleep(){
    System.out.println(name + "이(가) 잡니다.");
  }

  public void makeSound(){
    System.out.println(name + "이(가) 소리를 냅니다.");
  }

  public void displayInfo(){
    System.out.println("이름: " + name + ", 나이: " + age);
  }

}
