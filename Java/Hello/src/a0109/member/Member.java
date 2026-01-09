package a0109.member;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Member {
  private String name;
  private String email;
  private LocalDate joinDate; //LocalDate 날짜타입

  //getter 메서드
  public String getName() {
    return name;
  }

  //setter 메서드
  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public LocalDate getJoinDate() {
    return joinDate;
  }

  public void setJoinDate(LocalDate joinDate) {
    this.joinDate = joinDate;
  }

  // 생성자
  public Member(String name, String email, LocalDate joinDate) {
    this.name = name;
    this.email = email;
    this.joinDate = joinDate;
  }

  public void printMemberInfo() {
    System.out.println("\n====회원정보====");
    System.out.println("이름: " + name);
    System.out.println("이메일: " + email);
    System.out.println("가입일: " + joinDate);
    System.out.println("가입기간: " + getJoinDays() + "일 (" + getJoinMonths() + "개월)");
    System.out.println("회원등급: " + getMemberGrade());
  }

  
  public String getMemberGrade() {
    long days = getJoinDays();
    if (days >= 365) return "VIP";
    else if (days >= 180) return "Gold";
    else if (days >= 90) return "Silver";   
    else return "Bronze"; 
  }

  public long getJoinDays() {
    return ChronoUnit.DAYS.between(joinDate, LocalDate.now());
  }
  // joinDate부터 LocalDate.now() 현재일까지 며칠이 지났는지 계산, 가입기간 일 수

  // 가입기간 개월 수
  public long getJoinMonths() {
    return ChronoUnit.MONTHS.between(joinDate, LocalDate.now());
  }
}
