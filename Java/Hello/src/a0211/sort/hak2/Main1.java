package a0211.sort.hak2;

import java.util.ArrayList;
import java.util.Scanner;

public class Main1 {
  public static void main(String[] args) {
    // Scanner sc = new Scanner(System.in);
    ArrayList<Student> students = new ArrayList<>();
    // System.out.print("학생 수 입력 :");
    // int n = sc.nextInt();
    // sc.nextLine();

    // // 학생 정보 입력
    // for (int i = 0; i < n; i++) {
    // System.out.print("학생 이름: ");
    // String name = sc.nextLine();
    // System.out.print("학생 나이: ");
    // int age = sc.nextInt();
    // System.out.print("학생 학번: ");
    // int studentId = sc.nextInt();
    // sc.nextLine(); // 개행 문자 소비

    // students.add(new Student(name, age, studentId));
    // }

    // ===== 더미 데이터 =====
    students.add(new Student("홍길동", 20, 2023001));
    students.add(new Student("김철수", 22, 2023002));
    students.add(new Student("이영희", 21, 2023003));
    students.add(new Student("박민수", 23, 2023004));
    students.add(new Student("최지훈", 19, 2023005));
    students.add(new Student("강다은", 20, 2023006));
    // ======================

    // 삽입정렬
    insertionSort(students);

    // 정렬된 결과 출력
    System.out.println("정렬된 학생 목록:");
    for (Student student : students) {
      System.out.println(student);
    }

    // sc.close(); // Scanner 닫기
  }

  // 삽입 정렬 (이름 기준 오름차순 정렬)
  private static void insertionSort(ArrayList<Student> students) {
    int n = students.size();
    for (int i = 1; i < n; i++) {
      Student currentStudent = students.get(i);
      int j = i - 1;

      // 현재 학생의 이름을 이전 학생들과 비교하여 정렬
      while (j >= 0 && students.get(j).getName().compareTo(currentStudent.getName()) > 0) {
        // students[j + 1] = students[j]; // 한 칸씩 뒤로 이동
        students.set(j + 1, students.get(j));// 변경
        j--;
      }
      // students[j + 1] = currentStudent; // 적절한 위치에 삽입
      students.set(j + 1, currentStudent);
    }
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
