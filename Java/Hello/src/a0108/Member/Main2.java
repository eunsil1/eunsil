package a0108.Member;

import java.util.Scanner;

public class Main2 {
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
  
    //회원 3명 저장할 배열
    Member[] members = new Member[3];

    for(int i = 0; i < members.length; i++){    
      System.out.println("==== 회원가입 " + (i + 1) + "번 ====");

      System.out.print("아이디입력: ");
      String id = sc.nextLine(); // 문자 한줄 입력

      System.out.print("패스워드입력: ");
      String pw = sc.nextLine();

      System.out.print("이름입력: ");
      String name = sc.nextLine();
      members[i] = new Member(id, pw, name);
    }
    //회원 목록 출력
    System.out.println("\n===전체 회원 목록===");
    for(int i = 0; i < members.length; i++){
      members[i].showInfo();
    }
    //향상된 for문 -> for each
    // for(Member m : members){
    //   m.showInfo();
    // } -> 회원 목록 출력 코드랑 같음
    sc.close();
  }
}
