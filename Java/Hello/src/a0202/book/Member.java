package a0202.book;

import java.util.Map;

public class Member {
  private int memberNum;
  private int balance;
  private Map<String,Integer> rentalList;
  
  public Member(int memberNum) {
    this.memberNum = memberNum;
    this.balance = 30000;
  }

  public int getMemberNum() {
    return memberNum;
  }

  public void setMemberNum(int memberNum) {
    this.memberNum = memberNum;
  }

  public int getBalance() {
    return balance;
  }

  public void setBalance(int balance) {
    this.balance = balance;
  }

  public Map<String, Integer> getRentalList() {
    return rentalList;
  }

  public void setRentalList(Map<String, Integer> rentalList) {
    this.rentalList = rentalList;
  }

  public String getRentalName(){
    return "회원 " + memberNum;
  }

  
  
}
