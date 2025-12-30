package a1230;

public class ReferenceType {
    public static void main(String[] args) {

        // String 클래스(가장 많이 사용하는 참조 자료형)
        String name = "홍길동";
        String message = "안녕하세요";
        String empty = " "; //빈 문자열(공백)
        String greeting = "Hello, " + name ; //문자열 연결 가능
        
        System.out.println("이름: " + name);
        System.out.println("인사말: " + message);
        System.out.println("인사: " + greeting);

        // 배열(참조 자료형)
        int[] numbers = {1, 2, 3, 4, 5};
        String[] fruits = {"사과", "바나나", "오렌지"};

        System.out.println("\n=== 배열 ===");
        System.out.println("첫번째 숫자: " + numbers[0]);
        System.out.println("첫번째 과일: " + fruits[0]);

        //참조의 개념
        String str1 = "Java";
        String str2 = str1; //주소 복사(같은 객체 참조)
        String str3 = "Java";
        String str4 = new String("Java");//주소값이 달라짐

        System.out.println("\n=== 참조 비교===");
        System.out.println("str1: " + str1);
        System.out.println("str2: " + str2);
        System.out.println("str1 == str2: " + (str1 == str2));
        // 같은 객체를 참조하므로 true
        System.out.println("str1 == str3: " + (str1 == str3));
        System.out.println("str1 == str4: " + (str1 == str4)); //(주소를 비교)
        System.out.println("str1 == str4: " + (str1.equals(str4))); //(값을 비교)





    }
}
