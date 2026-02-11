package a0211.sort.hak5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Main1 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    ArrayList<Student> students = new ArrayList<>();

    // ===== 더미 데이터 =====
    students.add(new Student("홍길동", 20, 2023001));
    students.add(new Student("김철수", 22, 2023002));
    students.add(new Student("이영희", 21, 2023003));
    students.add(new Student("박민수", 23, 2023004));
    students.add(new Student("최지훈", 19, 2023005));
    students.add(new Student("강다은", 20, 2023006));
    // ======================

    // 정렬옵션 선택
    System.out.println("정렬 기준을 선택하세요");
    System.out.print("1. 이름");
    System.out.print("2. 나이");
    System.out.print("3. 학번");
    int choice = sc.nextInt();

    switch (choice) {
      case 1:
        // 이름으로 정렬
        students.sort(Comparator.comparing(Student::getName));
        // Student 객체 2개를 꺼내서 
        // getName() 값(String)을 비교해서 -> 오름차순 정렬하라
        // Student::getName = student -> student.getName(); 와 동일
        break;
      case 2:
        students.sort(Comparator.comparing(Student::getAge));
        break;
      case 3:
        students.sort(Comparator.comparing(Student::getStudentId));
        break;
      default:
        break;
        //Comparator.comparing 객체의 특정 필드 기준으로 정렬
        //이름 내림차순
        //students.sort(Comparator.comparing(Student::getName).reversed());
        //나이(숫자) 내림차순
        //students.sort(Comparator.comparingInt(Student::getAge).reversed());
    }

    // 정렬된 결과 출력
    System.out.println("정렬된 학생 목록:");
    for (Student student : students) {
      System.out.println(student);
    }

    // sc.close(); // Scanner 닫기
  }

}

class Student {
  private String name;
  private int age;
  private int studentId;

  public Student(String name, int age, int studentId) {
    this.name = name;
    this.age = age;
    this.studentId = studentId;
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

  public int getStudentId() {
    return studentId;
  }

  public void setStudentId(int studentId) {
    this.studentId = studentId;
  }

  @Override
  public String toString() {
    return "Student [ name = " + name + ", age = " + age + ", studentId = " + studentId + "]";
  }

}



