package a0108.student;

public class Student {
  String name;
  int kor;
  int eng;
  int math;

  public Student(String name, int kor, int eng, int math) {
    this.name = name;
    this.kor = kor;
    this.eng = eng;
    this.math = math;
  }

    private int getTotal() {
      return  kor + eng +  math;
    }
    
    private double getAvg() {
      return getTotal() / 3.0 ;
    }
    
    void showInfo() {
    System.out.println("이름: " + name);
    System.out.println("국어: " + kor + ", 영어: " + eng + ", 수학: " + math );
    System.out.println("총점: " + getTotal());
    System.out.println("평균: " + getAvg());
    System.out.println("-----------------");
  }
    

    

}
