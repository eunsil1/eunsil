package a0120.ex01;

public class Problem1 {
  public static void main(String[] args) {
    Student s1 = new Student("홍길동", 20, 85); //값을 넣어야하는 매개변수가 있으면 생성자를 생성해야함.
    Student s2 = new Student("김철수", 22, 95);
    Student s3 = new Student();
    s3.name = "이말자";
    s3.age = 30;
    s3.score = 80;
    
    System.out.println(s1);
    System.out.println("등급: " + s1.getGrade());
    System.out.println(s2);
    System.out.println("등급: " + s2.getGrade());
    System.out.println(s3);
    
    // int a;
    // System.out.println(a);
  }
}
