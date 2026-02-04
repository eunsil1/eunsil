package a0203.optional;

import java.util.Optional;

public class Optional1 {
  public static void main(String[] args) {
    MemberService service = new MemberService();
    // Optional<String> name = service.findNameById(2); //없는 회원
    // System.out.println(name.length()); null 에러
    // 전통방식 - 가능하지만 매번 null 체크를 해야해서 코드가 지저분해짐
    // if(name != null){
    //   System.out.println(name.length());
    // }else{
    //   System.out.println("회원이 존재하지 않습니다.");
    // }
    // 1번 name.ifPresent(n -> System.out.println(n.length()));
    // 2번 
    // 3번 예외를 직접 던지고 싶을때
    String name = service.findNameById(2)
     .orElseThrow(() -> new RuntimeException("회원이 없습니다."));
    System.out.println(name.length());
    System.out.println("프로그램 정상 종료");

  }
}
class MemberService{
  
  public Optional<String> findNameById(int id){
    if(id == 1){
      return Optional.of("홍길동");
    }
    return Optional.empty(); //회원이 없으면 null 반환
  }
}