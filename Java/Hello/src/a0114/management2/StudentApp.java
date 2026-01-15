package a0114.management2;

import java.util.Scanner;

public class StudentApp {
  private static Student[] studentsArray = new Student[100];
  private static Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    boolean run = true;

    while (run) {
      System.out.println("---------------------------------------------------------------");
      System.out.println("1. 학생등록 | 2. 학생목록 | 3. 학생검색 | 4. 학생수정 | 5. 학생삭제 | 6. 종료");
      System.out.println("---------------------------------------------------------------");
      System.out.print("선택> ");
    

    int selNum = 0;
    try {
      selNum = Integer.parseInt(scanner.nextLine());
    } catch (Exception e) {
      } 
      switch (selNum) {
        case 1:
          registerSteudent();
          break;
        case 2:
          studentList();
          break;
        case 3:
          searchStudent();
          break;
        case 4:
          updateStudent();
          break;

        case 6:
        System.out.println("프로그램 종료");
        run = false;
        break;

        default:
          break;
      }
    }
  }










  private static void updateStudent() {
    System.out.println();
    System.out.println("-----------");
    System.out.println("학생수정");
    System.out.println("-----------");

    System.out.print("학번: ");
    String studentId = scanner.nextLine();
    Student student = findStudent(studentId);

    if(studentId == null){
      System.out.println("결과: 학생을 찾을 수 없습니다.");
      return;
    }
    System.out.println("이름(수정): ");
    String name = scanner.nextLine();
    System.out.println("나이(수정): ");
    int age = 0;
    
  }










  private static void searchStudent() {
    System.out.println();
    System.out.println("-----------");
    System.out.println("학생검색");
    System.out.println("-----------");
    
    System.out.print("학번: ");
    String studentId = scanner.nextLine();
    Student student = findStudent(studentId);
    if ((studentId) == null) {
      System.out.println("결과: 학생을 찾을 수 없습니다.");
    }else{
      System.out.println("결과: 학생을 찾았습니다.");
      System.out.printf("학번: %s, 이름: %s, 나이: %d, 전공: %s, 국어: %d, 영어: %d, 수학: %d",
      student.getStudentId(),
      student.getName(),
      student.getAge(),
      student.getMajor(),
      student.getKor(),
      student.getEng(),
      student.getMath());
      
    }

    
  }

  private static void studentList() {
    System.out.println();
    System.out.println("-----------");
    System.out.println("학생목록");
    System.out.println("-----------");
    boolean hasStudent = false;
    for(int i = 0; i < studentsArray.length; i++){
      if(studentsArray[i] != null){
        Student student = studentsArray[i];
        System.out.printf("%s %s %d %s 국어: %d 영어: %d 수학: %d\n",
          student.getStudentId(),
          student.getName(),
          student.getAge(),
          student.getMajor(),
          student.getKor(),
          student.getEng(),
          student.getMath()
          );
      }
    }

  }


  private static void registerSteudent() {
    System.out.println();
    System.out.println("-----------");
    System.out.println("학생등록");
    System.out.println("-----------");

    System.out.print("학번: ");
    String studentId = scanner.nextLine();

    if (findStudent(studentId) != null) {
      System.out.println("이미 존재하는 학번입니다.");
      return;
    }
    System.out.print("이름: ");
    String name = scanner.nextLine();

    System.out.print("나이: ");
    int age = 0;
    try {
      age = Integer.parseInt(scanner.nextLine());
    } catch (Exception e) {
      System.out.println("잘못된 입력입니다. 숫자를 입력하세요.");
    }

    System.out.print("전공: ");
    String major = scanner.nextLine();

    System.out.print("국어: ");
    int kor = 0;
    try {
      kor = Integer.parseInt(scanner.nextLine());
    } catch (Exception e) {
      System.out.println("잘못된 입력입니다. 숫자를 입력하세요.");
    }

    System.out.print("영어: ");
    int eng = 0;
    try {
      eng = Integer.parseInt(scanner.nextLine());
    } catch (Exception e) {
      System.out.println("잘못된 입력입니다. 숫자를 입력하세요.");
    }

    System.out.print("수학: ");
    int math = 0;
    try {
      math = Integer.parseInt(scanner.nextLine());
    } catch (Exception e) {
      System.out.println("잘못된 입력입니다. 숫자를 입력하세요.");
    }

    Student newStudent = new Student(studentId, name, age, major, kor, eng, math);
    for(int i = 0; i < studentsArray.length; i++){
      if(studentsArray[i] == null){
        studentsArray[i] = newStudent;
        System.out.println("결과: 학생이 등록되었습니다.");
        break;
      }
    }

    
  }

  private static Student findStudent(String studentId) {
    Student student = null;
    for (int i = 0; i < studentsArray.length; i++) {
      if (studentsArray[i] != null) { 
        String dbStudentId = studentsArray[i].getStudentId(); 
        if (dbStudentId.equals(studentId)) { 
          student = studentsArray[i];
          break;
        }
      }
    }
    return student;
  }
  }
  

