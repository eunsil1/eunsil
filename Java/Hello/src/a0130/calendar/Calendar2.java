package a0130.calendar;

import java.util.Calendar;

public class Calendar2 {
  public static void main(String[] args) {
    Calendar startDate = Calendar.getInstance();
    startDate.set(2025,Calendar.JANUARY,1);

    Calendar endDate = Calendar.getInstance();
    endDate.set(2025, Calendar.DECEMBER, 1);

    long startMillis = startDate.getTimeInMillis();
    // getTimeInMillis() 1970.1.1부터 지정한 시간까지를 1/1000초
    System.out.println("startMillis: " + startMillis);

    long endMillis = startDate.getTimeInMillis();
    long diff = endMillis - startMillis;
    long diffDays = diff / (1000 * 60 * 60 * 24);
    // 1000 * 60 * 60 * 24 = 1일의 밀리초
    // 1초 * 60 -> 1분 * 60 -> 1시간 * 24 -> 1일
    System.out.println("두 날짜의 차이 = " + diffDays);
  }
}
