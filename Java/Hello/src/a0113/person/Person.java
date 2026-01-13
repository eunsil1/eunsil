package a0113.person;

public class Person {
  String name;
  int age;

  public Person(String name, int age) {
    this.name = name;
    this.age = age;
  }

  public void introduce(){
    System.out.println(name + "입니다.");
  }

  public void work(){
    System.out.println(name + "이(가) 일을 합니다.");
  }

  public void displayInfo(){
    System.out.println("이름: " + name + ", 나이: " + age);
  }

  
}
