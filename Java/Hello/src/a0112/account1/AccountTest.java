package a0112.account1;

public class AccountTest {
  public static void main(String[] args) {
    Account a = new Account("123-45", 10000);
    Account b = new Account("567-89", 10000);
    
    while (a.transfer(b,3000)) {
      ; //아무것도 안한다.
    }
    System.out.println(a.toStr());
    System.out.println(b.toStr());
  }
}

class Account{
  String num; //계좌번호
  int balance; //잔액

  public Account(String num, int balance) {
    this.num = num;
    this.balance = balance;
  }

  public boolean transfer(Account b, int i) {
    if(balance < i){ //잔액이 송금액보다 작으면 거짓을 리턴
      return false;
    } 
    balance = balance - i; //a의 잔액이 3000원씩 감소
    b.balance = b.balance + i; //b의 잔액이 3000원씩 증가
    return true;
  }

  public String toStr() {
    return String.format( "num : %s, balance: %d", num, balance);
  }
  
}