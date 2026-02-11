package a0211.liststream;

import java.util.ArrayList;
import java.util.List;

class Student_1{
  private int id;
  private String name;
  private int age;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
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

  public Student_1(int id, String name, int age) {
    this.id = id;
    this.name = name;
    this.age = age;
  }

  @Override
  public String toString() {
    return "Student [id=" + id + ", name=" + name + ", age=" + age + "]";
  }  
}

public class Stream2 {
  public static void main(String[] args) {
    //ArrayList 생성
    List<Student_1> studentList = new ArrayList<>();

    //객체추가
    studentList.add(new Student_1(101, "Alice", 20));
    studentList.add(new Student_1(102, "Bob", 22));
    studentList.add(new Student_1(103, "Charlie", 21));

    System.out.println("Student List 출력");
    printList(studentList);
    Student_1 student = studentList.get(1);
    String name = studentList.get(1).getName();
    System.out.println(student);
    System.out.println(name);
    System.out.println();

    System.out.println("\n모든 학생의 이름 출력");
    studentList.stream()
      .map(Student_1::getName)
      .forEach(System.out::println);

    // 데이터 추가
    System.out.println("\n새로운 학생 추가");
    studentList.add(new Student_1(104, "David", 23));
    printList(studentList);
    System.out.println();

    // 데이터 변경
    System.out.println("\n특정 학생 변경");
    updateStudent(studentList, 102, "Robert", 25);
    printList(studentList);
    System.out.println();

    // 데이터 삭제
    System.out.println("\n특정 학생 삭제");
    deleteStudent(studentList, 103);
    printList(studentList);
    System.out.println();

    // 데이터 학생 검색
    System.out.println("\n특정 학생 검색");
    Student_1 searchStudent = searchStudent(studentList, 104);
    System.out.println(searchStudent != null ? searchStudent : "학생 정보를 찾을 수 없습니다.");

  }

  private static Student_1 searchStudent(List<Student_1> studentList, int id) {
    return studentList.stream()
      .filter(student -> student.getId() == id)
      .findFirst() //여러개 찾아도 그중 제일 처음것
      .orElse(null);
  }

  private static void deleteStudent(List<Student_1> studentList, int id) {
    boolean removed = studentList.removeIf(student -> student.getId() == id);
    System.out.println(removed ? "학생 ID" + id + "정보가 삭제되었습니다." : "학생 ID" + id + "를 찾을수 없습니다..");
  }

  private static void updateStudent(List<Student_1> studentList, int id, String newName, int newAge) {
    boolean updated = studentList.stream()
        .filter(student -> student.getId() == id)
        .findFirst()
        .map(student -> {
          student.setName(newName);
          student.setAge(newAge);
          return true;
        })
        .orElse(false);

    System.out.println(updated
        ? "학생 ID " + id + " 정보가 수정되었습니다."
        : "학생 ID " + id + "를 찾을 수 없습니다.");
  }

  
  // private static void updateStudent(List<Student_1> studentList, int id, String newName, int newAge) {
  //   boolean updated = studentList.stream()
  //     .filter(student -> student.getId() == id)
  //     .peek(student -> {
  //       student.setName(newName);
  //       student.setAge(newAge);
  //     })
  //     .findFirst() //첫번째 학생을 찾았는지 여부
  //     .isPresent(); //있으면 true 값이 update 저장
  //   System.out.println(updated ? "학생 ID" + id + "정보수정되었습니다." : "학생 ID" + id + "를 찾을 수 없습니다.");
    

  // }

  public static void printList(List<Student_1> studentList){
    studentList.forEach(System.out::println);
  }
}

