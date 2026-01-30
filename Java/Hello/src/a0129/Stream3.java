package a0129;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Stream3 {
  public static void main(String[] args) {
    // 객체의 특정 필드 추출
    List<Student> students = Arrays.asList(
    new Student("Alice", 20),
    new Student("Bob", 22)
    );
    
    List<String> studentNames = students.stream()
    .map(Student::getName)
    .toList();

    System.out.println(studentNames);











    



  }
}

class Student{
  private String name;
  private int age;

  @Override
  public String toString() {
    return "Student [name=" + name + ", age=" + age + "]";
  }

  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public int getAge() {
    return age;
  }
  public void setAge(int age) {
    this.age = age;
  }

  public Student(String name, int age) {
    this.name = name;
    this.age = age;
  }



  
}
