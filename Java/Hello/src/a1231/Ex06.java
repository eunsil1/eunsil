package a1231;

public class Ex06 {
    public static void main(String[] args) {
        // 윤년이란
        // 윤년은 2월이 29일까지 있는 해
        // 윤년 판별 규칙
        // 1. 4로 나누어 떨어지고
        // 2. 100으로 나누어 떨어지지 않거나
        // 3. 400으로 나누어 떨어지면 윤년

        // (4의 배수 && 100의 배수 아님 || 400의 배수)

        int year = 2024;
        boolean leapyear = ((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0);
        // 윤년이면 true, 아니면 false
        System.out.println(leapyear);

        if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) {
            System.out.println(year + "년은 윤년입니다.");
        }else{
            System.out.println(year + "년은 윤년이 아닙니다.");
        }
    }
}
