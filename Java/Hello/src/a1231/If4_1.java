package a1231;

public class If4_1 {
    public static void main(String[] args) {
        int a = 80;
        int b = 90;
        int c = 70;
        int max = 0;

        if (a >= b && a >= c) {
            max = a;
        }else if(b >= a && b >= c){
            max = b;
        }else{
            max = c;
        }
        System.out.println("가장 큰 값 : " + max);
    }
}
