package a0113.person;

public class PersonMain {
  public static void main(String[] args) {
    Student s1 = new Student("민수", 20, "컴퓨터공학");
    Teacher t1 = new Teacher("김선생", 40, "자바");

    s1.introduce();
    s1.work();
    s1.study();
    s1.displayInfo();
    
    System.out.println();

    t1.introduce();
    t1.work();
    t1.teach();
    t1.displayInfo();
  }

  
}
