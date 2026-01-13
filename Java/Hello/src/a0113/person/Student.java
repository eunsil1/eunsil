package a0113.person;

public class Student extends Person{
  String major;

  public Student(String name, int age, String major) {
    super(name ,age);
    this.major = major;
  }

  @Override
  public void work() {
    System.out.println(name + "이(가) 공부를 합니다.");
  }

  public void study(){
    System.out.println(name +"이(가) " + major + "를 공부합니다.");
  }

  @Override
  public void displayInfo() {
    super.displayInfo();
    System.out.println("전공: " + major);
  }

  

  
}
