package a0127.account;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AccountManager {
  private ArrayList<Account> accounts;
  private String filename;


  public AccountManager() {
    this.accounts = new ArrayList<>();
    this.filename = "c:/Users/tj/memo/account.txt";
  }

  private void ensureDirectory(){
    File file = new File(filename);
    File parentDir = file.getParentFile();
    if (parentDir != null && !parentDir.exists()) { 
      parentDir.mkdirs();
      System.out.println("디렉토리가 생성되었습니다." + parentDir.getPath());
    }
  }

  public static void main(String[] args) {
  Scanner sc = new Scanner(System.in);
  AccountManager accountManager = new AccountManager();

  while(true){
    System.out.println("=== 은행 계좌 관리 시스템 ===");
    System.out.println("1. 계좌 생성");
    System.out.println("2. 입금");
    System.out.println("3. 출금");
    System.out.println("4. 계좌 조회");
    System.out.println("5. 전체 계좌 조회");
    System.out.println("6. 계좌 삭제");
    System.out.println("7. 파일 저장");
    System.out.println("8. 파일 불러오기");
    System.out.println("9. 종료");
    System.out.print("선택 > ");

    int choice = sc.nextInt();
    sc.nextLine();

    switch (choice) {
      case 1:
        System.out.print("계좌번호 입력: ");
        String accountNumber = sc.nextLine();
        System.out.print("예금주명 입력: ");
        String ownerName = sc.nextLine();
        System.out.print("초기 잔액 입력: ");
        int balance = sc.nextInt();
        accountManager.addAccount(accountNumber,ownerName,balance);
        break;
      case 2:
        System.out.print("계좌번호 입력: ");
        sc.nextLine();
        System.out.print("입력할 금액 입력: ");
        sc.nextInt();

        accountManager.deposit(accountNumber,balance);
        break;
      default:
        break;
    }
  }

}

  private void deposit(String accountNumber, int balance) {
    for(Account acc : accounts){
      if (acc.getAccountNumber().equals(accountNumber)) {
        acc.setBalance(acc.getBalance() + balance);
        System.out.println("입금 완료! 현재 잔액: " + acc.getBalance());
        return;
      }
    }
    System.out.println("계좌를 찾을 수 없습니다.");
  }

  private void addAccount(String accountNumber, String ownerName, int balance) {
    for(Account acc : accounts){
      if (acc.getAccountNumber().equals(accountNumber)) {
        System.out.println("이미 존재하는 계좌번호 입니다.");
        return;
      }
    }
    accounts.add(new Account(accountNumber, ownerName, balance));
    System.out.println("계좌가 생성되었습니다.");
  }

}
