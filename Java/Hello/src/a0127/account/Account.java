package a0127.account;

public class Account {
  private String accountNumber;
  private String ownerName;
  private int balance;

  public Account(String accountNumber, String ownerName, int balance) {
    this.accountNumber = accountNumber;
    this.ownerName = ownerName;
    this.balance = balance;
  }

  @Override
  public String toString() {
    return "Account [accountNumber=" + accountNumber + ", ownerName=" + ownerName + ", balance=" + balance + "]";
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public String getOwnerName() {
    return ownerName;
  }

  public int getBalance() {
    return balance;
  }

  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }

  public void setOwnerName(String ownerName) {
    this.ownerName = ownerName;
  }

  public void setBalance(int balance) {
    this.balance = balance;
  }

  
  
}
