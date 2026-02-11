package a0211.liststream;

import java.util.ArrayList;
import java.util.List;

class Student{
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

  public Student(int id, String name, int age) {
    this.id = id;
    this.name = name;
    this.age = age;
  }

  @Override
  public String toString() {
    return "Student [id=" + id + ", name=" + name + ", age=" + age + "]";
  }
}

public class List2 {
  public static void main(String[] args) {
        //ArrayList 생성
        List<Student> studentList = new ArrayList<>();

        //객체추가
        studentList.add(new Student(101, "Alice", 20));
        studentList.add(new Student(102, "Bob", 22));
        studentList.add(new Student(103, "Charlie", 21));

        System.out.println("Student List 출력");
        printList(studentList);
        Student student = studentList.get(1);
        String name = studentList.get(1).getName();
        System.out.println(student);
        System.out.println(name);
        System.out.println();

        System.out.println("\n모든 학생의 이름 출력");
        for(Student s : studentList) {
            System.out.println(s.getName());
        }

        //데이터 추가
        System.out.println("\n새로운 학생 추가");
        studentList.add(new Student(104, "David", 23));
        printList(studentList);
        System.out.println();
        
        //데이터 변경
        System.out.println("\n특정 학생 변경");
        updateStudent(studentList,102,"Robert",25);
        printList(studentList);
        System.out.println();

        //데이터 삭제
        System.out.println("\n특정 학생 삭제");
        deleteStudent(studentList,103);
        printList(studentList);
        System.out.println();

        //데이터 학생 검색
        System.out.println("\n특정 학생 검색");
        Student searchStudent = searchStudent(studentList,104);
        System.out.println(searchStudent != null ? searchStudent : "학생 정보를 찾을 수 없습니다.");
    }

    private static Student searchStudent(List<Student> studentList, int id) {
        for(Student student : studentList) {
            if(student.getId() == id){
                return student;
            }
        }
        return null;
    }

    private static void deleteStudent(List<Student> studentList, int id) {
        //리스트 돌면서 id가 같은것 찾아서 삭제
        for(int i = 0; i < studentList.size(); i++) {
            if(studentList.get(i).getId() == id) {
                studentList.remove(i);
                System.out.println("학생 정보 삭제");
                return;
            }
        }
        System.out.println("학생 정보 삭제 실패");

    }

    private static void updateStudent(List<Student> studentList, int id, String newName, int newAge) {
        //리스트 돌면서 id가 같은것 찾아서 이름과 나이 변경
        // for(Student student: studentList) {
        //     if(student.getId() == id) {
        //         student.setName(newName);
        //         student.setAge(newAge);
        //         System.out.println("학생 정보 변경");
        //         return;
        //     }
        // }
        // System.out.println("학생 정보 변경 실패");

        for(int i = 0; i < studentList.size(); i++) {
            if(studentList.get(i).getId() == id) {
                Student updateStudent = new Student(id, newName, newAge);
                studentList.set(i, updateStudent);
                System.out.println("학생 정보 변경");
                return;
            }
        }
        System.out.println("학생 정보 변경 실패");

    }

    public static void printList(List<Student> list) {
        for (Student student : list) {
            System.out.println(student);
        }
    }
  }
