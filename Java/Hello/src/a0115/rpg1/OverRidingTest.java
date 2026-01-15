package a0115.rpg1;

public class OverRidingTest {
  public static void main(String[] args) {
    Archer a = new Archer();
    Archer ma = (Archer) new MasterArcher(); //업캐스팅인데 생략해서 작성 가능
    a.shoot(); //화살쏘기 10
    ma.shoot(); //화살쏘기 30
  }
}

class Archer{
  void shoot(){
    System.out.println("화살쏘기 10");
  }
  }

  class MasterArcher extends Archer{
  void shoot(){
    System.out.println("화살쏘기 30");
  }

}


// 오버라이딩 : 부모클래스의 메소드를 자식클래스에서 재정의 하는 것
// 레퍼러스(참조)변수 ma는 연결 객체를 Archer라 해석하지만 실제론 MasterArcher 객체와 연결되어 있음
// 따라서 ma.shoot()를 호출하면 MasterArcher 클래스의 shoot() 메소드가 호출됨
// 다형성 : 하나의 객체가 여러 가지 타입을 가질 수 있는 것을 의미

