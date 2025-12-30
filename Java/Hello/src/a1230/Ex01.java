package a1230;

public class Ex01 {
    public static void main(String[] args) {
        System.out.println("\n--- 복합 연산 예제 ---");
        int score1 = 85;
        int score2 = 90;
        int score3 = 78;

        // 1. 각 점수를 인쇄하시오.
        System.out.println("점수: " + score1 + " ," + score2 + " ," + score3);
        // 2. 총점과 평균을 구해서 인쇄하시오.
        int sum = score1 + score2 + score3;
        double avg = (double) sum /3;
        System.out.println("총점: " + sum);
        System.out.println("평균: " + avg);

        // 3. 평균 60점 이상이면 합격(true)
        boolean isPass = avg >= 60;
        System.out.println("합격여부(>=60): " + isPass);

        // 4. 평균 90점 이상이면 우수(true)
        boolean isExcellent = avg >= 90;
        System.out.println("우수여부(>=90): " + isExcellent);
}
}