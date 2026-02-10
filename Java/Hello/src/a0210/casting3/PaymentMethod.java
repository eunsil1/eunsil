package a0210.casting3;

abstract class PaymentMethod {
  protected double amount;
  abstract double calculateFee();

  PaymentMethod(double amount) {
    this.amount = amount;
  }

  double getTotalAmount(){
    return amount + calculateFee();
  }

}

