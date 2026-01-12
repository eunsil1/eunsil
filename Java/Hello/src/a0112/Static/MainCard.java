package a0112.Static;

public class MainCard {
  public static void main(String[] args) {
     
    Card c1 = new Card("하트",7); 
    Card c2 = new Card("스페이드", 1);
    System.out.println("카드모양: " + c1.shape + " - 카드숫자 : " + c1.num);
    System.out.println("카드모양: " + c2.shape + " - 카드숫자 : " + c2.num);

    System.out.println("카드넓이: " + Card.width + " - 카드높이 : " + Card.height);
    // 공용변수(클래스변수, static(정적변수)) 이므로 객체를 만들지 않고 출력 가능

    System.out.println("카드넓이: " + c1.width + " - 카드높이 : " + c1.height);
    // 가능은 하나 별로 권장하지 않는 방법

    // static 변수 변경
    Card.width = 70;
    Card.height = 100;
    System.out.println("===카드크기 변경후");
    // System.out.println(클래스 명. 클래스 변수명);
    System.out.println("카드넓이: " + Card.width + " - 카드높이 : " + Card.height);
  }
}
