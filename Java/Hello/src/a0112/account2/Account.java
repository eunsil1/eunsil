package a0112.account2;

public class Account {
  // 1. 변수 생성
  String owner; //예금주 
  int balance; //잔액

  // 2. 생성자 생성
  public Account(String owner, int balance) {
    this.owner = owner;
    this.balance = balance;
  }

  // 5. 메서드 생성
  public void deposit(int money) {
    balance += money;
    System.out.printf("[%s] %d원 입금 -> 잔액: %d원\n",owner,money,balance);
  }

  public boolean withdraw(int money) {
    if (balance < money) {
      System.out.printf("[%s] 출금실패(잔액부족)\n",owner );
      return false;
    }

    balance -= money;
    System.out.printf("[%s] %d원 출금 -> 잔액: %d원\n",owner,money,balance);
    return true;
  }

  public boolean transfer(Account target, int money) {
    if(balance < money){
      System.out.printf("(%s => %s) 송금실패(잔액부족)\n",owner,target.owner);
      return false;
    }
    this.balance -= money;
    target.balance += money;
    System.out.printf("(%s => %s) %d 송금완료\n", owner, target.owner,money);
    System.out.printf("[%s , 잔액 : %d원]\n",owner,balance);
    System.out.printf("[%s , 잔액 : %d원]\n",target.owner,target.balance);
    return true;
  }
  

  
}
