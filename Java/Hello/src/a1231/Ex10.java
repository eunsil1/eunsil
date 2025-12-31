package a1231;

public class Ex10 {
    public static void main(String[] args) {
        // 18세 이상이면서 면허를 보유한 경우 : "운전 가능합니다"
        // 18세 이상이지만 면허가 없는 경우 : "면허가 필요합니다"
        // 18세 미만인 경우 :"미성년자는 운전할 수 없습니다"

        int age = 18;
        boolean lisence = true;

        if (age >= 18) {
            if(lisence){
                System.out.println("운전 가능합니다");
        }
        }if (age >= 18) {
            if(lisence != true ){
                System.out.println("면허가 필요합니다");
        }
        }else{
            System.out.println("미성년자는 운전할 수 없습니다");
    }
}
}
