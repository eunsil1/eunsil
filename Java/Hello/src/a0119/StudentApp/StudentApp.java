package a0119.StudentApp;

import java.util.ArrayList;

public class StudentApp {
  public static void main(String[] args) {
    ArrayList<Student> list = new ArrayList<>();
    list.add(new Student("홍길동", 20));
    list.add(new Student("김철수", 22));
    list.add(new Student("이기자", 25));

    // 리스트를 출력하는 메서드를 만드시오
    printAll(list);
    
    // 수정 - 학생이름
    System.out.println("===수정===");
    boolean result = updateStudent(list , "홍길동", 25);
      if (result) {
        System.out.println("수정 완료");
      }else{
        System.out.println("수정 실패");
      }
    printAll(list);

    // 삭제
    System.out.println("===삭제===");
    boolean result1 = deleteStudent(list, "김철수");
      if (result1) {
        System.out.println("삭제 완료");
      }else{
        System.out.println("삭제 실패");
      }
    printAll(list);

    // 검색 -> 검색한 객체 홍길동, 25
    System.out.println("===검색===");
    Student s = findStudent(list,"홍길동");
    System.out.println(s);
  }

  

  private static Student findStudent(ArrayList<Student> list, String name) {
    for(Student s : list){
      if (s.name.equals(name)) {
        return s;
      }
    }
    return null;
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

  private static void printAll(ArrayList<Student> list) {
    for(Student s : list){
      System.out.println(s);
    }
  }
}
