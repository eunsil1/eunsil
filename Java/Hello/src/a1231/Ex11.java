package a1231;

public class Ex11 {
    public static void main(String[] args) {
        
        // 1. 65세 이상은 무료
        // 2. 학생(초등학생~대학생) : 50% 할인 (기본요금 10000원)
        // 3. 일반 성인 : 10000원
        // 4. 7세 미만 : 무료

        int age = 19;
        boolean student = true;
        int price = 10000;

        if(age >= 65){
            System.out.println("65세 이상: 무료");
        }else if(age >= 7 && age <= 20){
            if(student){
                System.out.println("학생(초등학생~대학생): " + (price /2) + "원");
            }
        }else if(age >= 21 && age < 65){
            System.out.println("일반 성인 : " +  price + "원");
        }else if(age < 7){
            System.out.println("7세 미만 : 무료");
    }
}
}