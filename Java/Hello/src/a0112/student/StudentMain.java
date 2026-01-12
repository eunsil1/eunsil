package a0112.student;

public class StudentMain {
  public static void main(String[] args) {
    Student student1 = new Student("김철수", 90);
    Student student2 = new Student("이영희", -10);

    System.out.println("=== 학생 정보 ===");
    System.out.printf("학생1: %s, %d점\n", student1.getName(), student1.getScore());
    System.out.printf("학생1: %s, %d점\n", student2.getName(), student2.getScore());

    student2.setScore(95);
    System.out.printf("학생1: %s, %d점", student2.getName(), student2.getScore());
    }
}



