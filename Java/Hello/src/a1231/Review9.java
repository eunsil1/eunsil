package a1231;

public class Review9 {
    public static void main(String[] args) {
        int score = 55;

        boolean result = score >= 60 || score >= 90;

        System.out.println(result);

        boolean isRain = true;
        System.out.println(!isRain);
        // 참 -> 거짓, 거짓 -> 참
    }
}
