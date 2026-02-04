package a0203.book;

import java.util.Map;

public class Member {
  
  private int memberNum;              // 회원 번호
  private int money;                 // 잔액 (초기값 30000원)
  private Map<String, Integer> bookRental;  // 대여 내역 (도서명, 대여일수)

  public Member(int memberNum) {
    this.memberNum = memberNum;
    this.money = 30000; //초기 잔액 설정
  }

  public int getMemberNum() {
    return memberNum;
  }

  public void setMemberNum(int memberNum) {
    this.memberNum = memberNum;
  }

  public int getMoney() {
    return money;
  }

  public void setMoney(int money) {
    this.money = money;
  }

  public Map<String, Integer> getBookRental() {
    return bookRental;
  }

  public void setBookRental(Map<String, Integer> bookRental) {
    this.bookRental = bookRental;
  }

  public String getMemberName(){
    return "회원 " + memberNum;
  }
}
