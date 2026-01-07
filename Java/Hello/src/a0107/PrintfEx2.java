package a0107;

public class PrintfEx2 {
  public static void main(String[] args) {
    String name = "홍길동";
    int age = 25;
    double height = 175.5;
    System.out.printf("이름: %s, 나이: %d, 키: %.1f\n",name,age,height);

    double avg = 87.456;
    System.out.printf("평균점수: %.2f점\n",avg);
    // %.2f점 -> 소수점 셋째자리에서 반올림하여 둘째자리까지 표현 87.46

    // 자리맞춤
    System.out.printf("%-15s\t %7d\n", "사과", 1200);
    System.out.printf("%-15s\t %7d\n", "바나나", 800);
    System.out.printf("%-15s\t %7d\n", "파인애플", 10000);
    // 줄바꿈 -> \n
    // 탭간격 -> \t
    // 따옴표 -> \"
    // 역슬래시 연속으로 쓸때 -> \\
    
    System.out.println("===== 영수증 =====");
    System.out.println("상품명\t가격");
    System.out.println("커피\t3000원");
    System.out.println("케이크\t4500원");
    System.out.println("=================");

  }
}
