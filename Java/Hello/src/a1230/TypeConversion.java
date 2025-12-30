package a1230;

public class TypeConversion {
    public static void main(String[] args) {
        System.out.println("===자동 타입 변환===");

        // 1. 정수형에서 실수형으로 자동 타입 변환 int -> double
        int intValue = 100;
        double doubleValue = intValue; //자동 타입 변환
        System.out.println("int: " + intValue + " -> double: " + doubleValue);
         
        // long -> float(자동변환)
        long longValue = 1000000L;
        float floatValue = longValue;
        System.out.println("long: " + longValue + " -> float: " + floatValue);

        // char -> int(자동변환 아스키코드값)
        char ch = 'A';
        int ascii = ch; //자동 변환
        System.out.println("char: '" + ch + "' -> int: " + ascii);

        // 2. Double -> int (강제형변환, 소수점 손실)
        double pi = 3.14159;
        int intPi = (int) pi; //강제변환
        System.out.println("double: " + pi + " -> int: " + intPi);

        // int -> char (강제변환)
        int num = 66;
        char character = (char) num; //강제변환
        System.out.println("int: " + num + " -> char: '" + character + "'");

        //큰 범위 -> 작은범위(데이터 손실 가능)
        int bigNum = 300;
        byte smallByte = (byte) bigNum; //byte 범위 (-128~127)를 초과
        System.out.println("int: " + bigNum + " -> byte: " + smallByte + " (데이터 손실!)");
        
        System.out.println("\n=== 실용적인 예제 ===");

        // 정수 나눗셈을 실수로 변환
        int a = 10;
        int b = 3;
        double result1 = a / b;
        double result2 = (double) a / b; //a 나 b 둘 중 하나에 double을 붙히면 실수로 계산됨 
        System.out.println("10 / 3 (정수): " + result1);
        System.out.println("10 / 3 (실수): " + result2);


    }
}
