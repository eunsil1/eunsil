package a0109.Student;

public class StudentMain {
  public static void main(String[] args) {
    
    Student student1 = new Student("홍길동",90,85,80);
    Student student2 = new Student("김철수",75,80,70);
    Student student3 = new Student("이영희",95,92,88);

    //학생 정보를 출력 - 메서드(함수)를 이용하면 private에서도 출력 가능
    // student1.printInfo();
    // student2.printInfo();
    // student3.printInfo();

    Student[] students = {student1,student2,student3}; //배열
    // 향상된 for 문
    for(Student s : students){
      s.printInfo();
    }
    // students 배열에서 객체인 Student 하나씩 꺼내서 s에 담아 실행

    System.out.println("\n======개별 메서드 테스트======");
    
    // 총점 계산 테스트
    System.out.println(student1.getName() + "의 총점: " + student1.calcTotal());
    System.out.println(student2.getName() + "의 총점: " + student2.calcTotal());
    System.out.println(student3.getName() + "의 총점: " + student3.calcTotal());

    // 평균 계산 테스트
    System.out.println(student1.getName() + "의 평균: " + student1.calAvg());
    System.out.println(student2.getName() + "의 평균: " + student2.calAvg());
    System.out.println(student3.getName() + "의 평균: " + student3.calAvg());

    student1.setMath(95); //80의 점수를 95로 수정

    System.out.println("\n======= Getter 메서드 테스트 ========");
    System.out.println("이름: " + student1.getName());
    System.out.println("국어: " + student1.getKorean() + "점");
    System.out.println("영어: " + student1.getEnglish() + "점");
    System.out.println("수학: " + student1.getMath() + "점");


    // System.out.println(student1.name);
  
  }
}
