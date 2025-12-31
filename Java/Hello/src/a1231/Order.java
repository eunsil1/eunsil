package a1231;

public class Order {
    public static void main(String[] args) {
        
        int result1 = 10 + 2 * 3;
        System.out.println(result1);

        // 괄호 우선 계산
        int result2 = (10 + 2) * 3;
        System.out.println(result2);

        int result3 = 20 / 4 + 2;
        System.out.println(result3);

        boolean result4 = 10 > 5 && 3 > 5;
        System.out.println(result4);

        //논리 연산자 우선순위
        boolean result5 = true || false && false;
        System.out.println(result5);
        // 중요 - 논리 연산자 우선순위
        // 1. ! (not)
        // 2. && (and)
        // 3. || (or)

        //대입 연산자
        int x = 5;
        x += 3 * 2;
        System.out.println(x);

        boolean result = 10 + 2 * 3 > 15 && 5 + 5 == 10;
        System.out.println(result);
    }
}
