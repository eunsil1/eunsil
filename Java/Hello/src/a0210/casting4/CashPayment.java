package a0210.casting4;

class CashPayment extends PaymentMethod {

  CashPayment(double amount) {
    super("현금", amount);
  }

  @Override
  double calculateFee() {
    return 0;
  }
}
