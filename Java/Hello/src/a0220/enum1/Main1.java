package a0220.enum1;

public class Main1 {
  public static void main(String[] args) {
    Role role = Role.ADMIN;

    if (role == Role.ADMIN) {
      System.out.println("관리자입니다.");
    }else{
      System.out.println("일반 사용자입니다.");
    }
  }
}
