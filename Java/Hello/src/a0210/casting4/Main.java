package a0210.casting4;

public class Main {
  public static void main(String[] args) {
    PaymentMethod[] payments = new PaymentMethod[3];
    payments[0] = new CreditCard(100000);
    payments[1] = new CashPayment(50000);
    payments[2] = new CreditCard(200000);

    double totalFee = 0;
    for(PaymentMethod p : payments){
      totalFee += p.calculateFee();
    }
    System.out.println("총 수수료: " + (int)totalFee);
  }
}
