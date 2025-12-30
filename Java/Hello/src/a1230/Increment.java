package a1230;

public class Increment {
    public static void main(String[] args) {
        System.out.println("=== 전위 증가 연산자 (++변수) ===");
        int a = 5;
        System.out.println("초기값 a = " + a); // 5
        a++; // 'a = a + 1' 이라는 뜻
        System.out.println("증가 a = " + a); // 6

        int b = ++a; // a를 먼저 1증가 시킨 후(7), b에 할당
        System.out.println("b = ++a 실행 후");
        System.out.println(" a = " + a); // 7
        System.out.println(" b = " + b); // 7

        System.out.println("=== 후위 증가 연산자 (변수++) ===");
        int c = 5;
        System.out.println("초기값 c = " + c); // 5

        int d = c++;
        System.out.println("d = c++ 실행 후:");
        System.out.println("c = " + c); // 6
        System.out.println("d = " + d); // 5

        System.out.println("\n=== 전위 감소 연산자 (--변수) ===");
        int e = 10;
        System.out.println("초기값 e = " + e);
        
        int f = --e;  // e를 먼저 1 감소시킨 후(9), f에 할당
        System.out.println("f = --e 실행 후:");
        System.out.println("e = " + e);  // 9
        System.out.println("f = " + f);  // 9
        
        System.out.println("\n=== 후위 감소 연산자 (변수--) ===");
        int g = 10;
        System.out.println("초기값 g = " + g);
        
        int h = g--;  // g의 현재 값(10)을 h에 할당한 후, g를 1 감소
        System.out.println("h = g-- 실행 후:");
        System.out.println("g = " + g);  // 9
        System.out.println("h = " + h);  // 10
    }
}
