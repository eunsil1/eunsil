package a0108;

public class Ex2 {
  public static void main(String[] args) {
    Account acc = new Account();
    acc.deposit(1000);
    acc.withddraw(300);
    System.out.println("잔액: " + acc.balance);
    
  }
}

class Account{

  int balance; //잔액

  void deposit(int money){ //입금
    balance += money;
  }

  void withddraw(int money){ //출금
    balance -= money;

  }
}
