package a0210.casting;

public class Main2 {
  public static void main(String[] args) {
    Vehicle v = new Car();
    v.start();
    v.stop();
    // v.drive(); -> 부모 타입으로 다루면 공통기능만 보장
    Car c = (Car) v; //다운캐스팅
    c.drive();

    // if (v instanceof Car) {
    //   ((Car) v).drive();
    // }
  }
}

class Vehicle{
  void start(){
    System.out.println("차량 시작");
  }

  void stop(){
    System.out.println("차량 정지");
  }
}

class Car extends Vehicle{
  void start(){
    System.out.println("자동차 시동");
  }

  void drive(){
    System.out.println("자동차 주행");
  }
}

