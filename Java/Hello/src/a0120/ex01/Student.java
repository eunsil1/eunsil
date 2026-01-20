package a0120.ex01;

public class Student {
  String name;
  int age;
  int score;

  public Student() { // 초기값을 넣기 위해 생성자를 만듦
  }

  public Student(String name, int age, int score) {
    this.name = name;
    this.age = age;
    this.score = score;
  }

  @Override
  public String toString() {
    return "이름: " + name + ", 나이: " + age + ", 점수: " + score;
  }

  public String getGrade() {
    if (score >= 90) {
      return "A";
    }else if(score >= 80){
      return "B";
    }else if(score >= 70){
      return "C";
    }else if(score >= 60){
      return "D";
    }else{
      return "F";
    }
  }

  
}
