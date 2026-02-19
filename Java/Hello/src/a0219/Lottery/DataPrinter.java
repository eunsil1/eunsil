package a0219.Lottery;

import java.util.Calendar;

public class DataPrinter {
  public static void printDataTime(Calendar cal){
    //년, 월, 일, 시, 분, 초 => 24시간제
    int yy = cal.get(Calendar.YEAR);
    int mm = cal.get(Calendar.MONTH) + 1;
    int dd = cal.get(Calendar.DAY_OF_MONTH);
    int hh = cal.get(Calendar.HOUR_OF_DAY);
    int mi = cal.get(Calendar.MINUTE);
    int ss = cal.get(Calendar.SECOND);

    // 콘솔에 깔끔하게 출력 (예: 2024년 05월 22일 14:30:05)
    System.out.printf("설정된 일시: %d년 %02d월 %02d일 %02d:%02d:%02d\n",
        yy, mm, dd, hh, mi, ss);

  }
}
