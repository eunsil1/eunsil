package a0210.casting4;

abstract class PaymentMethod {
  protected String methodName;
  protected double amount;
  
  
  public PaymentMethod(String methodName, double amount) {
    this.methodName = methodName;
    this.amount = amount;
  }

  abstract double calculateFee();
}
