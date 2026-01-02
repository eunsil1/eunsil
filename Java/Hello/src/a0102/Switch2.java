package a0102;

public class Switch2 {
  public static void main(String[] args) {
    // 자바 14이상 
    int day = 3;
    String dayName = switch (day) {
      case 1 -> "월요일" ;
      case 2 -> "화요일" ;
      case 3 -> "수요일" ;
      case 4 -> "목요일" ;
      case 5 -> "금요일" ;
      case 6 -> "토요일" ;
      case 7 -> "일요일" ;
      default -> "잘못된 요일" ;
    };//값을 반환하는 표현식으로 ; 가 반드시 필요함.
    System.out.println(dayName);
  }
  }
