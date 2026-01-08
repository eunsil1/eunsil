package a0108.Member;
//회원
public class Member {
  String id;
  String password;
  String name;
  
  // 생성자
  public Member(String id, String password, String name) {
    this.id = id;
    this.password = password;
    this.name = name;
  }
  //회원 정보 출력 메서드
  public void showInfo(){
    System.out.println("아이디: " + id);
    System.out.println("이름: " + name);
  }
  
}
