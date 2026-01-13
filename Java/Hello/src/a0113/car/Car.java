package a0113.car;

public class Car {
  String model;
  String color;

  public Car(){ //기본 생성자
    this("기본모델","흰색");
  }

  public Car(String model, String color) { //String model에는 "기본모델"이 들어가고 String color에는 "흰색"이 들어감
    this.model = model; //위 this와 다름
    this.color = color;
  }

}
