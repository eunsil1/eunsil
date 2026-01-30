package a0130.stream;

// 거래
public class Transaction {
  private Trader trader;
  private int year;
  private int value;

  public Transaction(Trader trader, int year, int value) {
    this.trader = trader;
    this.year = year;
    this.value = value;
  }

  public Trader getTrader() {
    return trader;
  }

  public int getYear() {
    return year;
  }

  public int getValue() {
    return value;
  }

  @Override
  public String toString() {
    return "[name=" + trader.getName() + ", city=" + trader.getCity() + ", year=" + year + ", value=" + value + "]";
  }

  

  
}
