package a0102;

public class Switch3 {
  public static void main(String[] args) {
    // 여러 case 묶기
    int month = 7;
    switch (month) {
      case 12:
      case 1:
      case 2:
        System.out.println("겨울");
        break;
      case 3:
      case 4:
      case 5:
        System.out.println("봄");
        break;
      case 6:
      case 7:
      case 8:
        System.out.println("여름");
        break;
      case 9:
      case 10:
      case 11:
        System.out.println("가을");
        break;
      default:
        System.out.println("잘못 입력 되었습니다.");
        break;
      // default에는 오류에 대해 생각해야되기 때문에 기재해야함.
    }
    // switch (score > 60) -> 불가함 (범위, 비교는 switch문에서 사용 불가)
    // switch문은 값이 딱 떨어질 때 사용함 
  }
}
