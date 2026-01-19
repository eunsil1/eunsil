package a0119.StudentApp1;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentApp1 {
  public static void main(String[] args) {
    ArrayList<Student> list = new ArrayList<>();
    //더미데이터
    list.add(new Student("홍길동", 20));
    list.add(new Student("김철수", 22));
    list.add(new Student("이영희", 21));

    Scanner sc = new Scanner(System.in);
    boolean run = true;

    while (run) {
      System.out.println("-------------");
      System.out.println("1. 학생 등록");
      System.out.println("2. 학생 검색");
      System.out.println("3. 학생 수정");
      System.out.println("4. 학생 삭제");
      System.out.println("5. 전체 출력");
      System.out.println("6. 종료");
      System.out.print("선택 > ");

      int menu;
      try {
        menu = Integer.parseInt(sc.nextLine());
      } catch (Exception e) {
        System.out.println("숫자만 입력하세요");
        continue;
        // 밑에 있는 코드를 무시하고 while문으로 복귀
        // 메뉴를 다시 선택
      }

      switch (menu) {

        case 1:
          // 학생 등록
          System.out.print("이름입력 : ");
          String name = sc.nextLine();
          System.out.print("나이입력 : ");
          int age;
          try {
            age = Integer.parseInt(sc.nextLine());
          } catch (Exception e) {
            System.out.println("나이는 숫자만 입력하세요");
            break;
            // Switch (menu){..} 블럭을 종료
            // while 조건식으로 되돌아감
          }
          list.add(new Student(name, age));
          System.out.println("학생등록완료");
          break;

        case 2:
          // 학생 검색
          System.out.print("검색할 이름 : ");
          String sname = sc.nextLine();
          Student s = findStudent(list, sname);
          if (s != null) {
            System.out.println(s);
          } else {
            System.out.println("찾는 학생이 없어요");
          }
          break;

        case 3:
          // 학생 수정
          System.out.print("수정할 이름: ");
          name = sc.nextLine();
          System.out.print("새 나이 입력: ");
          try {
            age = Integer.parseInt(sc.nextLine());
          } catch (Exception e) {
            System.out.println("나이는 숫자만 입력하세요");
            break;
          }
          if (updateStudent(list, name, age)) {
            System.out.println("수정완료");
            System.out.println(list);
          } else {
            System.out.println("해당학생이 없습니다.");
          }
          break;
          
        case 4:
          // 학생 삭제
          System.out.print("삭제할 이름: ");
          name = sc.nextLine();
          if (deleteStudent(list, name)) {
            System.out.println("삭제완료");
          } else {
            System.out.println("삭제할 학생이 없습니다.");
          }
          break;

        case 5:
          // 전체 출력
          if (list.isEmpty()) {
            System.out.println("등록된 학생이 없습니다.");
          }else{
            for (Student st : list) {
              System.out.println(st);
            }
          }
          break;

        case 6:
          run = false;
          break;
        default:
          System.out.println("번호를 다시 입력하세요");
      }

    }

  }

  private static boolean deleteStudent(ArrayList<Student> list, String name) {
    for(int i = 0; i < list.size(); i++){
      if (list.get(i).name.equals(name)) {
        list.remove(i);
        return true;
      }
    }
    return false;
  }

  private static boolean updateStudent(ArrayList<Student> list, String name, int newAge) {
    for (Student s : list) {
      if (s.name.equals(name)) {
        s.age = newAge;
        return true;
      }
    }
    return false;
  }

  private static Student findStudent(ArrayList<Student> list, String sname) {
    for (Student s : list) {
      if (s.name.equals(sname)) {
        return s;
      }
    }
    return null;
  }
}
