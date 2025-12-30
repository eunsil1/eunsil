package a1230;

public class Arithmetic {
    public static void main(String[] args) {
        int a = 10;
        int b = 3;
        int result = a + b;
        System.out.println("=== 산술 연산자 ===");
        System.out.println("더한 결과 : " + result); // 13

        System.out.println("a = " + a + ", b = " + b);
        System.out.println("a + b = " + (a + b)); // 13
        System.out.println("a - b = " + (a - b)); // 7
        System.out.println("a * b = " + (a * b)); // 30
        System.out.println("a / b = " + (a / b)); // 3 (정수 나눗셈)
        System.out.println("a % b = " + (a % b)); // 1 (나머지)

        System.out.println("\n=== 정수 나눗셈 vs 실수 나눗셈 ===");
        int x = 10;
        int y = 3;
        
        int result1 = x / y;                    // 정수 나눗셈
        double result2 = x / y;                  // 정수 나눗셈 후 double 변환
        double result3 = (double) x / y;         // 실수 나눗셈
        
        System.out.println("10 / 3 (정수): " + result1);        // 3
        System.out.println("10 / 3 (double): " + result2);      // 3.0
        System.out.println("10 / 3 (실수): " + result3);         // 3.333...

        System.out.println("\n=== 나머지 연산자 활용 ===");
        int num = 15;
        System.out.println(num + "% 2 = " + (num % 2)); // 2의 배수 확인
        System.out.println(num + "% 3 = " + (num % 3)); // 3의 배수 확인
        System.out.println(num + "% 5 = " + (num % 5)); // 5의 배수 확인

        //짝수 / 홀수 판별
        if (num % 2 == 0) {
            System.out.println(num + "짝수 입니다.");
        }else{
            System.out.println(num + "홀수 입니다.");
        }

    }
}
