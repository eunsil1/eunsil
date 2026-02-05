package a0205.calendar;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateEx {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("기본 ISO 8601 형식 : "+now);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        //mm으로 쓰면 분으로 인식할수 있어서 MM으로 작성
        //HH로 쓰면 24시간으로 표기
        //hh로 쓰면 12시간으로 표기
        String formattedDate = now.format(formatter);
        System.out.println("포맷팅된 날짜 : "+formattedDate);
    }
}
