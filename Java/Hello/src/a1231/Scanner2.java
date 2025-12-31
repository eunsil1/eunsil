package a1231;

import java.util.Scanner; //1. Import 문장

public class Scanner2 {
    public static void main(String[] args) {
        // 2. Scanner 객체 생성 sc(변수값은 마음대로 정해도됨), (System.in) - 키보드 입력
        Scanner sc = new Scanner(System.in);

        System.out.print("이름을 입력하세요: ");
        String name = sc.next(); //문자열 입력(한 단어)
        // 한줄 전체 입력 (공백포함) sc.nextline();
        // char ch = sc.next().charAt(0); -> 문자 1개 입력받기

        System.out.print("나이를 입력하세요: ");
        int age = sc.nextInt(); //정수 입력
        //Double avg = sc.nextDouble(); -> 실수 입력

        System.out.println(name + "님의 나이는 " + age + "세 입니다.");
        
        //3. 사용이 끝난 후에는 닫아주는 것이 좋습니다.
        sc.close();

    }
}
