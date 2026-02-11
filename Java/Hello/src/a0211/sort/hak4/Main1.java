package a0211.sort.hak4;

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
        //이름으로 정렬
        Collections.sort(students, new NameComparator());
        break;
      case 2:
        Collections.sort(students, new AgeComparator());
        break;
      case 3:
        Collections.sort(students, new IdComparator());
        break;
      default:
        break;
    }

    

    // 정렬된 결과 출력
    System.out.println("정렬된 학생 목록:");
    for (Student student : students) {
      System.out.println(student);
    }

    // sc.close(); // Scanner 닫기
  }

}

class Student  {
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

class NameComparator implements Comparator<Student>{

  @Override
  public int compare(Student o1, Student o2) {
    return o1.getName().compareTo(o2.getName());
  }

}

class AgeComparator implements Comparator<Student> {

  @Override
  public int compare(Student o1, Student o2) {
    return Integer.compare(o1.getAge(), o2.getAge());
  }

}

class IdComparator implements Comparator<Student> {
 //내장 함수로 id순 정렬
  @Override
  public int compare(Student o1, Student o2) {
    return Integer.compare(o1.getStudentId(), o2.getStudentId());
  }

}

//Comparable - 정렬대상 클래스 내부 Student 클래스 내부 - 정렬 기준이 1개
//메서드는 compareTo() - 실무 활용이 적다.

//Comparator - 별도 클래스(외부) - 정렬 기준을 여러개
//메서드는 compare() - 실무 활용이 많다.