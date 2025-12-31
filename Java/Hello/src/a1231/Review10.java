package a1231;

public class Review10 {
    public static void main(String[] args) {
        //로그인 조건
        String id = "admin";
        String pw = "1234";

        boolean login = id.equals("admin")  && pw.equals("1234");
        System.out.println(login);     
    }
}
