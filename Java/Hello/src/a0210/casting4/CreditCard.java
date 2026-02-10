package a0210.casting4;

class CreditCard extends PaymentMethod {
  CreditCard(double amount){
    super("신용카드", amount);
  }

  @Override
  double calculateFee() {
    return amount * 0.03;
  }

  
}
