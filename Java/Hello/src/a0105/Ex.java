package a0105;

public class Ex {
  public static void main(String[] args) {
    // 4. "붕어빵 매출 왕 찾기" (심화: 최대값과 인덱스)
    // 상황: 일주일 동안 판 붕어빵 개수가 배열에 들어있습니다. (월~일 순서) int[] sales = {120, 150, 95, 200, 180, 300, 250};
    // 일주일 중 가장 많이 판 날의 개수를 구하세요.
    // [보너스] 가장 많이 판 날이 '몇 번째 날(무슨 요일)'인지도 함께 출력해보세요. (힌트: 최대값을 찾을 때 인덱스 번호를 따로 저장할 변수가 필요합니다.)

    String[] days = { "월", "화", "수", "목", "금", "토", "일" };
    int[] sales = { 120, 150, 95, 200, 180, 300, 250 };
    int max = sales[0];
    int day = 0;
    for(int i = 0; i < sales.length; i++){
      if(sales[i] > max){
        max = sales[i];
        day = i;
      }
    }
    System.out.println("가장 많이 판 날의 갯수 : " + max);
    System.out.println("가장 많이 판 날 : " + days[day] + "요일");
  }
}
