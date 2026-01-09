package a0109.member;

import java.time.LocalDate;

public class MemberMain {
  public static void main(String[] args) {
    System.out.println("=====회원 정보 출력 예제=====");
      // LocalDate.of(년,월,일) - 2023년 1월 1일이라는 날짜 객체 생성
    Member member1 = new Member("이영희", "lee@example.com", 
      LocalDate.of(2023,1, 1)); //vip 등급
    Member member2 = new Member("박민수", "park@example.com",
        LocalDate.of(2024, 6, 1)); // Bronze 등급
    Member member3 = new Member("김철수", "kim@example.com",
        LocalDate.of(2023, 7, 1)); // Gold 등급
    Member member4 = new Member("최지영", "choi@example.com",
        LocalDate.of(2024, 3, 1)); // Silver 등급
    
        // 회원정보 출력 
        member1.printMemberInfo();
        System.out.println();

        member2.printMemberInfo();
        System.out.println();

        member3.printMemberInfo();
        System.out.println();

        member4.printMemberInfo();
        System.out.println();
  }
}
