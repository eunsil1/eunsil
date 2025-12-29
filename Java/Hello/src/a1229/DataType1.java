package a1229;

public class DataType1 {
  public static void main(String[] args) {
    //정수형
     int age = 25;
     age = 26; //변수 값 변경 가능
     long population = 7800000000L;

     //실수형
     double height = 175.5;
     float weight = 70.5F;

     //문자형
     char grade = 'A';

     //논리형
     boolean isStudent = true;
     
     //참조 자료형(String) heap 영역에 저장, 주소값만 있음, 데이터가 직접 저장 되어 있지 않음
     String name = "홍길동";

     //상수
     final double PI = 3.14159;
     final int Max_SCORE = 100;
     // PI = 1.19; //에러 발생, 상수는 값 변경 불가

     //출력
      System.out.println("이름: " + name);
      System.out.println("나이: " + age);
      System.out.println("키: " + height + "cm");
      System.out.println("학생 여부: " + isStudent);
      System.out.println("원주율: " + PI);

  }
}
