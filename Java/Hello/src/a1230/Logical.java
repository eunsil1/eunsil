package a1230;

public class Logical {
    public static void main(String[] args) {
        boolean a = true;
        boolean b = false;
        System.out.println("=== 논리 연산자 ===");
        System.out.println("a= " + a + ", b= " + b);

        // AND 연산 (&&), 모두 다 참이면 참(하나라도 거짓이면 거짓)
        System.out.println("\nAND 연산(&&):");
        System.out.println("a && a: " + (a && a)); //true
        System.out.println("a && b: " + (a && b)); //false
        System.out.println("b && a: " + (b && a)); //false
        System.out.println("b && b: " + (b && b)); //false

        // OR 연산 (||)(Shift + \), 둘 중 하나라도 참이면 참
        System.out.println("\nOR 연산(||):");
        System.out.println("a || a: " + (a || a)); //true
        System.out.println("a || b: " + (a || b)); //true
        System.out.println("b || a: " + (b || a)); //true
        System.out.println("b || b: " + (b || b)); //false

        // NOT 연산 (!)
        System.out.println("\nNOT 연산 (!):");
        System.out.println("!a: " + (!a));  // false
        System.out.println("!b: " + (!b));  // true
        System.out.println("!!a: " + (!!a));  // true (이중 부정)

        // 복합 논리 연산
        System.out.println("\n=== 복합 논리 연산 ===");
        boolean x = true;
        boolean y = true;
        boolean z = false;
        
        System.out.println("x = " + x + ", y = " + y + ", z = " + z);
        System.out.println("x && y && z: " + (x && y && z));      // false
        System.out.println("x || y || z: " + (x || y || z));      // true
        System.out.println("(x && y) || z: " + ((x && y) || z));  // true
        System.out.println("x && (y || z): " + (x && (y || z)));  // true

        // 실전 예제
        // 3,4,5 봄 6,7,8 여름 9,10,11 가을 12,1,2 겨울

        int season = 1;

        if (season >= 3 && season <= 5) {
            System.out.println("봄입니다.");
        }else if(season >= 6 && season <= 8){
            System.out.println("여름입니다.");
        }else if(season >= 9 && season <= 11){
            System.out.println("가을입니다.");
        }else if(season == 12 || season == 1 || season == 2){
            System.out.println("겨울입니다.");
        }else{
            System.out.println("입력이 잘못되었습니다.");
        }
    }
}