package a1231;

public class If5 {
  public static void main(String[] args) {
    int num = 7;
    if (num % 2 == 0) {
      System.out.println(num + "는 짝수입니다.");
    } else {
      System.out.println(num + "는 홀수입니다.");
    }

    // 삼항 연산자
    // 조건식 ? 값1 : 값2;
    // 조건식이 true면 값1, false면 값2
    String result = (num % 2 == 0) ? "짝수" : "홀수";
    System.out.println(num + "은" + result + "입니다.");
  }
}
