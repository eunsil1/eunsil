package a0102;

public class Println {
  public static void main(String[] args) {
    // println() : 출력 후 줄바꿈
    // print()  : 출력 후 줄바꿈 안함
    // printf() : 형식 지정 출력, f는 format의 약자

    int age = 20;
    System.out.println("나이: " + age + "살 입니다.");
    System.out.printf("나이는 %d살 입니다.\n", age);
    // %d : 정수 -> int %d

    double avg = 87.456;
    System.out.printf("평균 : %.1f점\n", avg);

  }
}
