package a0112.Static;

public class Card {
  
  // static -> 공용변수 (모든 카드의 공통) - 클래스 변수, 객체 생성 필요 없음
  static int width = 60;
  static int height = 80;
  // 모든 카드의 모양은 동일함 -> 공유데이터

  //멤버변수(인스턴스 변수) , 카드마다 다름, 객체 생성 필요함
  String shape; //카드 무늬(하트, 스페이드 등등)
  int num; //숫자(1~13)

  public Card(String shape, int num) {
    this.shape = shape;
    this.num = num;
  }




}
