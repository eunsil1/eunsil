package a1230;

public class Compare {
    public static void main(String[] args) {
        int a = 10;
        int b = 20;
        int c = 10;
        
        System.out.println("=== 비교 연산자 ===");
        System.out.println("a = " + a + ", b = " + b + ", c = " + c);

        //같음 비교
        System.out.println("\n=== 같음 비교 ===");
        System.out.println("a == b: " + (a == b)); //false
        System.out.println("a == c: " + (a == c)); //true

        //다름 비교
        System.out.println("\n=== 같지 않음 비교 ===");
        System.out.println("a != b: " + (a != b)); //true
        System.out.println("a != c: " + (a != c)); //false

        //크기 비교
        System.out.println("\n=== 크기 비교 ===");
        System.out.println("a > b: " + (a > b));    // false
        System.out.println("a < b: " + (a < b));    // true
        System.out.println("a >= c: " + (a >= c));   // true
        System.out.println("a <= b: " + (a <= b));  // true

        System.out.println("\n=== 실용적인 예제 ===");

        //나이 비교
        int age = 20;
        System.out.println("나이: " + age);
        System.out.println("성인 여부 (>= 18): " + (age >= 18));

        //점수 비교
        int score = 85;
        System.out.println("점수: " + score);
        System.out.println("합격 여부 (>= 60): " + (score >= 60));
        System.out.println("우수 여부 (>= 90): " + (score >= 90));

        // 문자열 비교 (주의: == 대신 equals() 사용 권장)
        String str1 = "Hello";
        String str2 = "Hello";
        String str3 = new String("Hello");
        
        System.out.println("\n문자열 비교:");
        System.out.println("str1 == str2: " + (str1 == str2));      // true (리터럴)
        System.out.println("str1 == str3: " + (str1 == str3));      // false (다른 객체)
        System.out.println("str1.equals(str3): " + str1.equals(str3));  // true (내용 비교)
    }
}
