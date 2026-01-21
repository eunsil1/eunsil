package a0121.ex02;

public class Car {
  String brand;
  String model;
  int year;

  public Car() {
  }

  public Car(String brand, String model, int year) {
    this.brand = brand;
    this.model = model;
    this.year = year;
  }

  @Override
  public String toString() {
    return "브랜드: " + brand + ", 모델: " + model + ", 제조년도: " + year + "년";
  }

  public int getAge(int currentYear) {
    return currentYear - year;

  }

  
  
}
