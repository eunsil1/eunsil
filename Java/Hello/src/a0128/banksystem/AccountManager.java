package a0128.banksystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class AccountManager {
  private ArrayList<Account> accounts;
  private String filename;
  private AccountManager(){
    this.accounts = new ArrayList<>();
    this.filename = "c:/Users/tj/memo/account.txt";
  }

  //출력 디렉토리(폴더) 확인 및 생성
    private void ensureDirectory(){
    File file = new File(filename);
    File parentDir = file.getParentFile(); // c:/Users/tj/memo/
    if (parentDir != null && !parentDir.exists()) { //폴더가 없으면
      parentDir.mkdirs();//폴더를 만들어라
      System.out.println("디렉토리가 생성되었습니다." + parentDir.getPath());
      //만들어진 폴더를 화면에 보여주세요
    }
  }
  
  public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
        AccountManager manager = new AccountManager();
        
        while (true) {
            System.out.println("\n=== 은행 계좌 관리 시스템 ===");
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
            
            int choice;
            try {
                choice = sc.nextInt();
                sc.nextLine(); // 버퍼 비우기
            } catch (Exception e) {
                System.out.println("숫자만 입력하세요.");
                sc.nextLine(); // 잘못된 입력 제거
                continue;
            }

            switch (choice) {
              case 1:
                System.out.print("계좌번호 입력: ");
                String accountNumber = sc.nextLine();
                System.out.print("예금주명: ");
                String ownerName = sc.nextLine();
                System.out.print("초기잔액 입력: ");
                try {
                  int initialBalance = sc.nextInt(); //초기입금액
                  sc.nextLine();
                  manager.createAccount(accountNumber, ownerName, initialBalance);
                } catch (Exception e) {
                  System.out.println("잔액은 숫자로 입력하세요");
                  sc.nextLine();
                }
                break;
              case 2:
                System.out.print("계좌번호 입력: ");
                String depositAccount = sc.nextLine();  
                System.out.print("입금할 금액 입력: ");

                try {
                  int depositAmount = sc.nextInt();
                  sc.nextLine();
                  manager.deposit(depositAccount, depositAmount);
                } catch (Exception e) {
                  System.out.println("금액은 숫자로 입력하세요");
                  sc.nextLine();
                }
                break;
              case 3:
                System.out.print("계좌번호 입력: ");
                String withdrawAccount = sc.nextLine();
                System.out.print("출금할 금액 입력: ");

                try {
                  int withdrawAmount = sc.nextInt();
                  sc.nextLine();
                  manager.withdraw(withdrawAccount, withdrawAmount);
                } catch (Exception e) {
                  System.out.println("금액은 숫자로 입력하세요");
                  sc.nextLine();
                }
                break;
              case 4:
                System.out.print("조회할 계좌번호 입력: ");
                String searchAccount = sc.nextLine();
                manager.findAccount(searchAccount);
                break;
              case 5:
                manager.printAll();
                break;
              case 6:
                System.out.print("삭제할 계좌번호 입력: ");
                String deleteAccount = sc.nextLine();
                manager.deleteAccount(deleteAccount);
                break;
              case 7:
                manager.saveToFile();
                break;
              case 8:
                manager.loadFromFile();
                break;
              case 9:
                System.out.println("프로그램을 종료합니다.");
                sc.close();
                return;
              default:
                System.out.println("1~9번 중에 선택하세요.");
                break;
            }
  }
}

  

  private void loadFromFile() {
    File file = new File(filename); //파일의 위치정보
    if(!file.exists()){
      System.out.println("파일이 없습니다. 새로 시작합니다.");
      return;
    }

    try (BufferedReader br = new BufferedReader(new FileReader(filename))){
      
      String line;
      while ((line = br.readLine()) != null) {
        line = line.trim(); //공백 삭제후 line에 입력
        if (!line.isEmpty()) { //line(계좌정보) 비어있지 않으면
          Account account = Account.fromFileString(line); //123-456|john|10000    
          if(account != null){
            accounts.add(account);
          }
        }
      }
      System.out.println("파일 불러오기 완료! " + accounts.size() + "명");
    }catch(IOException e){
      System.out.println("파일 읽기 오류: " + e.getMessage());
    }
  }

  //파일 저장
  private void saveToFile() {
    ensureDirectory();
    try(BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
      for(Account account : accounts){
        bw.write(account.toFileString());
        bw.newLine(); //줄바꿈
      }
      System.out.println("파일 저장 완료: " + filename);
    } catch (IOException e) {
      System.out.println("파일 저장 오류: " + e.getMessage());
    }
  }

  //삭제
  private void deleteAccount(String deleteAccount) {
    Account account = findAccountByNumber(deleteAccount);
    if (account != null) {
      accounts.remove(account);
      System.out.println("계좌가 삭제 되었습니다.");
      saveToFile();
    } else {
      System.out.println("계좌를 찾을 수 없습니다.");
    }
  }

  //전체 계좌 조회
  private void printAll() {
    if(accounts.isEmpty()){
      System.out.println("등록된 계좌가 없습니다.");
      return;
    }
    System.out.println("=== 전체 계좌 목록 ===");
    for(int i = 0; i < accounts.size(); i++){
      System.out.println((i + 1) + " . " + accounts.get(i));
    }
  }

  //계좌조회
  private void findAccount(String searchAccount) {
    Account account = findAccountByNumber(searchAccount);
    if(account != null){
      System.out.println("=== 계좌 정보 ===");
      System.out.println("계좌번호: " + account.getAccountNumber());
      System.out.println("예금주명: " + account.getOwnerName());
      System.out.println("잔   액: " + account.getBalance() + "원");
    }else{
      System.out.println("계좌를 찾을 수 없습니다.");
    }
  }

  //출금
  private void withdraw(String withdrawAccount, int withdrawAmount) {
    Account account = findAccountByNumber(withdrawAccount);
    if (account != null) {
      if(account.withdraw(withdrawAmount)){
        System.out.println("출금 완료! 현재 잔액은: " + account.getBalance() + "원");
        // 출금완료된 현재 잔액
        saveToFile();
      }else{
        System.out.println("잔액이 부족합니다." + account.getBalance() + "원");
        // 출금이 안된 현재 잔액
      }
    }else{
      System.out.println("계좌를 찾을 수 없습니다.");
    }
  }

  private Account findAccountByNumber(String accountNumber) {
      for(Account account : accounts){
        if (account.getAccountNumber().equals(accountNumber)) {
          return account;
        }
      }
      return null;
    }

  private void createAccount(String accountNumber, String ownerName, int initialBalance) {
    if (findAccountByNumber(accountNumber) != null) {
      System.out.println("이미 존재하는 계좌번호");
      return;
    }
    accounts.add(new Account(accountNumber, ownerName, initialBalance));
    saveToFile();
    System.out.println("계좌 생성이 되었습니다.");
    
  }

  //입금
  private void deposit(String depositAccount, int depositAmount) {
    Account account = findAccountByNumber(depositAccount);
    if(account != null){
      account.deposit(depositAmount); 
      System.out.println("입금 완료! 현재 잔액 " + account.getBalance() + "원");
      saveToFile();
    }else{
      System.out.println("찾는 계좌가 없습니다.");
    }
  }
}