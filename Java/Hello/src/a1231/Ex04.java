package a1231;

public class Ex04 {
    public static void main(String[] args) {
        // age 나이
        // height 키
        // parent 보호자 동반여부
        // hearchDease 심장질환
        // take 탑승가능 여부

        int age = 6;
        int height = 120;

        boolean parent = false;
        boolean hearchDease = false;
        boolean take = ((age >= 6 && height >= 120) || (height >= 120 && parent == true)) && hearchDease == false;
        System.out.println(take);

        // 신장이 120이상이면서 나이가 6세 이상 이거나 보호자가 동반되고 심장질환이 없을때만 탑승 가능
    }
}
