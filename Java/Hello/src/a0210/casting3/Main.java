package a0210.casting3;

public class Main {
  public static void main(String[] args) {
    PaymentMethod payment = new CreditCard(100000);
    System.out.println("수수로: " + (int)payment.calculateFee());
    System.out.println("총액: " + (int)payment.getTotalAmount());
  }
}
