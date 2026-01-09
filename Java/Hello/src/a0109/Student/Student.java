package a0109.Student;

public class Student {
  
  //접근 제어자 (private - 보안유지 가능(해킹 가능성 ↓))
  private String name;
  private int korean;
  private int english;
  private int math;
  // private int sum = 0;

  public Student(String name, int korean, int english, int math) {
    this.name = name;
    this.korean = korean;
    this.english = english;
    this.math = math;
  }

  //getter - 속성 값을 가져오는 메서드 setter - 속성 값을 변경하는 메서드 
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getKorean() {
    return korean;
  }

  public void setKorean(int korean) {
    this.korean = korean;
  }

  public int getEnglish() {
    return english;
  }

  public void setEnglish(int english) {
    this.english = english;
  }

  public int getMath() {
    return math;
  }

  public void setMath(int math) {
    this.math = math;
  }

  public void printInfo(){
    System.out.println("=== " + name + " 학생 정보 === ");
    System.out.println("국어: " + korean + "점");
    System.out.println("영어: " + english + "점");
    System.out.println("수학: " + math + "점");
    System.out.println("총점: " + calcTotal() + "점");
    System.out.println("평균: " + String.format("%.2f",calAvg()) + "점"); //소수 둘째자리까지 표현하겠다라는 뜻
    System.out.println("등급: " + calcGrade());
  }

  // private int calcTotal() {
  //   sum = korean + english + math;
  //   return sum;
  // }
  
  // private String calcGrade() {
  //   double avg = calAvg();
  //   if (avg >= 90) { //90이상이면 "A"값을 리턴
  //     return "A";
  //   }else if (avg >= 80) { 
  //     return "B";
  //   }else if (avg >= 70) { // 90이상이면 "A"값을 리턴
  //     return "C";
  //   }else if (avg >= 60) { // 90이상이면 "A"값을 리턴
  //     return "D";
  //   }else {
  //     return "F";
  //   }
  // }

  public String calcGrade() { //결과값이 한줄이면 중괄호({}) 생략 가능
    double avg = calAvg();
    if (avg >= 90) return "A";
     else if (avg >= 80) return "B";
     else if (avg >= 70) return "C";
     else if (avg >= 60) return "D";
     else return "F";
  }
  
  public double calAvg() {
    return calcTotal() / 3.0; //3.0 실수로 계산된 값이 실수
  }

  public int calcTotal() {
   return korean + english + math;
  }
}
