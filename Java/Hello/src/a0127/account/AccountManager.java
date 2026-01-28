package a0127.account;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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
        String accNum = sc.nextLine();
        System.out.print("입금할 금액 입력: ");
        int money = sc.nextInt();

        accountManager.deposit(accNum,money);
        break;
      case 3:
        System.out.print("계좌번호 입력: ");
        String accNum2 = sc.nextLine();
        System.out.print("출금할 금액 입력: ");
        int money2 = sc.nextInt();

        accountManager.Withdrawal(accNum2,money2);
        break;
      case 4:
        System.out.print("계좌번호 입력: ");
        String accNum3 = sc.nextLine();
        accountManager.searchAccount(accNum3);
        break;
      case 5:
        accountManager.showAllAccounts();
        break;
      case 6:
        System.out.print("삭제할 계좌번호: ");
        String deleteAccountNumber = sc.nextLine();
        accountManager.deleteAccount(deleteAccountNumber);
        break;
      case 7:
        accountManager.saveToFile();
        break;
      case 8:
        accountManager.loadFromFile();
        break;
      default:
        break;
    }
  }

}

  private void loadFromFile() {
    
  }

  private void deleteAccount(String deleteAccountNumber) {
    for(int i = 0; i < accounts.size(); i++){
      Account acc = accounts.get(i);

      if (acc.getAccountNumber().equals(deleteAccountNumber)) {
        accounts.remove(i);
        System.out.println("계좌가 삭제되었습니다.");
        saveToFile();
        return;
      }
    }
    System.out.println("계좌를 찾을 수 없습니다.");
  }

  private void showAllAccounts() {
    if (accounts.isEmpty()) {
      System.out.println("등록된 계좌가 없습니다.");
      return;
    }else{System.out.println("=== 전체 계좌 목록 ===");
    for(Account acc : accounts){
      System.out.println("계좌번호: " + acc.getAccountNumber());
      System.out.println("예금주명: " + acc.getOwnerName());
      System.out.println("잔액: " + acc.getBalance() + "원");
    }
  }
}

  private void searchAccount(String accountNumber) {
    for (Account acc : accounts) {
      if (acc.getAccountNumber().equals(accountNumber)) {
        System.out.println("=== 계좌 정보 ===");
        System.out.println("계좌번호: " + acc.getAccountNumber());
        System.out.println("예금주명: " + acc.getOwnerName());
        System.out.println("잔액: " + acc.getBalance() + "원");
        return;
      }
    }
    System.out.println("계좌를 찾을 수 없습니다.");
  }

  private void Withdrawal(String accNum2, int money2) {
    for (Account acc : accounts) {
      if (acc.getAccountNumber().equals(accNum2)) {
        if (acc.getBalance() < money2) {
        System.out.println("잔액이 부족합니다.");
        return;
      }
      }
      
      acc.setBalance(acc.getBalance() - money2);
      System.out.println("출금 완료! 현재 잔액: " + acc.getBalance() + "원");
      saveToFile();
      return;
    
    }
    System.out.println("계좌를 찾을 수 없습니다.");
  }
  

  private void saveToFile() {
    ensureDirectory();
    try(BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
      for(Account account : accounts){
        bw.write(account.toFileString());
        bw.newLine(); 
      }
      System.out.println("파일 저장 완료: " + filename);
    } catch (IOException e) {
      System.out.println("파일 저장 오류: " + e.getMessage());
    }
 
  }

  private void deposit(String accNum, int money) {
    for(Account acc : accounts){
      if (acc.getAccountNumber().equals(accNum)) {
        acc.setBalance(acc.getBalance() + money);
        System.out.println("입금 완료! 현재 잔액: " + acc.getBalance() + "원");
        saveToFile();
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
