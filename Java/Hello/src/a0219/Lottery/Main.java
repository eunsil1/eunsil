package a0219.Lottery;

public class Main {
  public static void main(String[] args) {
    //환영인사 및 회원 가입 진행 여부
    Membership m = Membership.getInstance();
    m.MembershipGuide();
    //로또 구매
    Purchase p = Purchase.getInstance();
    //로또 구매 여부를 묻고 로또를 구매한다.
    p.purchaseQuestion();
    
    Loading l = new Loading();
    l.loading();

    //로또 번호를 추첨한다.
    Lottery lt = new Lottery();

    //랜덤으로 로또번호를 생성한다.
    lt.randomLotteryNum();
    
    //당첨된 로또번호를 출력한다.
    lt.lotteryNum();

    //당첨 결과를 출력한다.
    lt.result();
  }
}
