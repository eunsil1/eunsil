package a0105;

public class IfElse {
  public static void main(String[] args) {
    int avg = 85; //int 정수 저장
    char hak;
    // 한개의 문자를 저장하는 자료형(char)
    // 저장(대입) 시 반드시 작은따옴표('')를 사용해야함
    if (avg >= 90) {
      hak = 'A';
    }else if (avg >= 80) {
      hak = 'B';
    }else if (avg >= 70) {
      hak = 'C';
    }else if (avg >= 60) {
      hak = 'D';
    }else {
      hak = 'F';
    }
    System.out.println("당신의 학점은 : " + hak + "입니다.");
  }
}
