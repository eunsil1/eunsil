package a0108;

public class Main1 {
  public static void main(String[] args) {
    // 객체생성(인스턴스화)
    Car myCar = new Car();
    
    // 객체의 필드값 설명 및 메서드 호출
    myCar.model = "테슬라 모델3";
    myCar.color = "화이트";

    myCar.accelerate();
    myCar.stop();
    // 또다른 객체 생성
    Car yourCar = new Car();
    yourCar.model = "아이오닉6";
    yourCar.accelerate();
  }
}

class Car{
// 필드(속성)
  String model;
  String color;
  int speed;

  //메서드
  void accelerate(){
    speed += 10;
    System.out.println(model + "의 속도가 " + speed + "km/h가 되었습니다.");
  }

  void stop(){
    speed = 0;
    System.out.println(model + "이 멈췄습니다.");
  }
}
