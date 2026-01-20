package a0120.ex02;

import java.util.ArrayList;

public class Problem2 {
  public static void main(String[] args) {
    ArrayList<Student> list = new ArrayList<>();

    StudentManager.addStudent(list, "홍길동", 20, 85);
    // Student s1 = new Student("홍길동",20,85);
    // list.add(s1);
    StudentManager.addStudent(list, "김철수", 22, 95);
    StudentManager.addStudent(list, "이영희", 21, 75);

    System.out.println("=== 전체 학생 정보 ===");
    StudentManager.prinaAll(list);

    System.out.println("\n=== 학생 검색 ===");
    Student s = StudentManager.findStudent(list, "김철수");
    if (s != null) {
      System.out.println("찾은 학생: " + s);
    }

    System.out.println("\n=== 평균 점수 ===");
    double avg = StudentManager.getAverageScore(list);
    System.out.println("평균: " + avg);
  }
}
