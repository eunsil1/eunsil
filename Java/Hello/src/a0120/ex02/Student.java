package a0120.ex02;

public class Student {
  String name;
  int age;
  int score;
  
  public Student(String name, int age, int score) {
    this.name = name;
    this.age = age;
    this.score = score;
  }

  @Override
  public String toString() {
    return "이름: " + name + ", 나이: " + age + ", 점수: " + score;
  }

  
}
