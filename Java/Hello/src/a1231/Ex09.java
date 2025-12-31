package a1231;

public class Ex09 {
    public static void main(String[] args) {
        // 아이디가 "admin"
        // 비밀번호가 "1234" 일때만 로그인 성공
        // 그 외의 경우 로그인 실패

        String id = "";
        int pw = 1234;

        if (id == "admin" && pw == 1234) {
            System.out.println("로그인 성공");
        }else{
            System.out.println("로그인 실패");
        }
}
}