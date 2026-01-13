package a0113.car;

public class CarMain {
  public static void main(String[] args) {
    // 1. 기본생성자를 사용하여 객체생성
    // 내부적으로 this("기본모델","흰색")이 호출
    Car car1 = new Car();
    System.out.println("===car1(기본생성자)");
    System.out.println("모델: " + car1.model);
    System.out.println("색상: " + car1.color);
    System.out.println();

    Car car2 = new Car("제네시스","검정색");
    System.out.println("===car2(기본생성자)");
    System.out.println("모델: " + car2.model);
    System.out.println("색상: " + car2.color);
    System.out.println();
  }
}
