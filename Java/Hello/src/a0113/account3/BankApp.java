package a0113.account3;

import java.util.Scanner;

public class BankApp {

  // Account acc = new Account("123-123", "이순신", 10000);
  // 객체를 하나만 만든다.
  // Account 객체를 100개를 관리하고 싶다.
  private static Account[] accountsArray = new Account[100];
  // Account 객체 100를 관리하는 배열을 만든다.
  private static Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    boolean run = true;
    while (run) {
      System.out.println("-------------------------------------------------------------------");
      System.out.println("1.계좌생성 | 2.계좌목록 | 3. 예금 | 4. 출금 | 5.계좌이체 | 6. 종료");
      System.out.println("-------------------------------------------------------------------");
      System.out.print("선택> ");
      int selNum = 0;
      try {
        selNum = Integer.parseInt(scanner.nextLine());
      } catch (Exception e) {

      }
      // a 라고 문자를 입력해도
      // 에외처리 - 에러발생해도 프로그램 계속실행 - while

      switch (selNum) {
        case 1:
          createAccount();
          break;
        case 2:
          accountList();
          break;
        case 3:
          deposit();
        break;
        case 4:
        withdraw();
        break;
        case 5:
        transfer();
        break;
        case 6:
          System.out.println("프로그램 종료");
          run = false;
          break;
      }

    }
  }

  private static void transfer() {
    System.out.println("-----------");
    System.out.println("계좌이체");
    System.out.println("-----------");
    System.out.print("출금 계좌번호: ");
    String ano = scanner.nextLine();
    System.out.print("입금 계좌번호: ");
    String send = scanner.nextLine();
    Account account = findAccount(ano);
    Account sendaccount = findAccount(send);
    if (account == null || sendaccount == null) {
      System.out.println("결과 : 계좌가 없습니다.");
      return;
    }
    System.out.print("이체금액: ");
    int money = 0;
    try {
      money = Integer.parseInt(scanner.nextLine());
      if (money <= 0) {
        System.out.println("이체 금액은 1원 이상이어야 합니다.");
        return;
      }
      if (account.getBalance() <= money) {
        // 잔액보다 찾는 금액이 많으면 잔액부족
        System.out.println("잔액부족입니다.");
        return;
      }
      account.setBalance(account.getBalance() - money); // 보낸사람 객체에서 잔액 - 이체금액
      sendaccount.setBalance(sendaccount.getBalance() + money); // 받는사람 객체에서 잔액 + 이체금액
      System.out.println("이체성공");
    } catch (NumberFormatException e) {// 숫자 에러(문자가 들어왔을때)
      System.out.println("잘못된입력 입니다. 숫자를 입력해 주세요");
    }
    
  }

  private static void withdraw() {
    System.out.println("-----------");
    System.out.println("출금");
    System.out.println("-----------");
    System.out.print("계좌번호: ");
    String ano = scanner.nextLine();
    Account account = findAccount(ano);
    if (account == null) {
      System.out.println("결과 : 계좌가 없습니다.");
      return;
    }
    System.out.print("출금액 : ");
    try {
      int money = Integer.parseInt(scanner.nextLine());
      if (money <= 0) {
        System.out.println("출금액은 1원 이상이어야 합니다.");
        return;
      }
      if (account.getBalance() <= money) {
        // 잔액보다 찾는 금액이 많으면 잔액부족
        System.out.println("잔액부족입니다.");
        return;
      }
      account.setBalance(account.getBalance() - money);
      System.out.println("결과: 출금완료 현재 잔액: " + account.getBalance() + "원");
    } catch (NumberFormatException e) {// 숫자 에러(문자가 들어왔을때)
      System.out.println("잘못된입력 입니다. 숫자를 입력해 주세요");
    }
  }


  private static void deposit() { //3. 예금
    System.out.println("-----------");
    System.out.println("예금");
    System.out.println("-----------");
    System.out.print("계좌번호: ");
    String ano = scanner.nextLine();
    Account account = findAccount(ano);
    if (account == null) {
      System.out.println("결과 : 계좌가 없습니다.");
      return;
    }
    System.out.print("예금액 : ");
    try {
      int money = Integer.parseInt(scanner.nextLine());
      if(money <= 0){
        System.out.println("예금액은 1원 이상이어야 합니다.");
        return;
      }
      account.setBalance(account.getBalance() + money);
    } catch (NumberFormatException e) {//숫자 에러(문자가 들어왔을때)
      System.out.println("잘못된입력 입니다. 숫자를 입력해 주세요");
    }
  }

  private static void accountList() {
    System.out.println("-------------------");
    System.out.println("계좌목록");
    System.out.println("-------------------");
    for (int j = 0; j < accountsArray.length; j++){
      if (accountsArray[j] != null) {
        System.out.printf("%s %4s %d\n", accountsArray[j].getAno(), accountsArray[j].getOwner(),
            accountsArray[j].getBalance());
      }
    }

  }

  private static void createAccount() {
    System.out.println("------------------");
    System.out.println("계좌생성");
    System.out.println("------------------");
    System.out.print("계좌입력 : ");
    String ano = scanner.nextLine();
    if (findAccount(ano) != null) {
      System.out.println("이미 존재하는 계좌번호 입니다.");
      return;
    }
    System.out.print("계좌주: ");
    String owner = scanner.nextLine();
    System.out.print("초기입금액: ");
    int balance = 0; // 잔액 0 초기
    try {
      balance = Integer.parseInt(scanner.nextLine());
      if (balance < 0) {
        System.out.println("초기입금액은 0원 이상이여야 합니다.");
        return;
      }
      Account newAccount = new Account(ano, owner, balance);
      for (int i = 0; i < accountsArray.length; i++) {
        if (accountsArray[i] == null) {
          accountsArray[i] = newAccount;
          System.out.println("결과 계좌가 생성되었습니다.");
          break;
        }

      }

    } catch (Exception e) {
      System.out.println("잘못된 입력입니다. 숫자를 입력하세요");
    }

  }

  private static Account findAccount(String ano) {//계좌번호 유무 찾는 메서드
    Account account = null;
    // 반환 될 값 초기화
    for (int k = 0; k < accountsArray.length; k++) {
      if (accountsArray[k] != null) {
        String dbAno = accountsArray[k].getAno();
        if (dbAno.equals(ano)) {
          account = accountsArray[k];
          break;
        }
      }
    }
    return account;

  }

}
