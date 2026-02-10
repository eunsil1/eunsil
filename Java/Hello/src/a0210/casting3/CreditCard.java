package a0210.casting3;

class CreditCard extends PaymentMethod{
  private static final double FEE_RATE = 0.03;

  CreditCard(double amount) {
    super(amount);
  }

  @Override
  double calculateFee() {
    return amount * FEE_RATE;
  }

  
  
}
