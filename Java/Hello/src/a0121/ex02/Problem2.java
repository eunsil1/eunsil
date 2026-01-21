package a0121.ex02;

public class Problem2 {
  public static void main(String[] args) {
    Car c1 = new Car("현대","소나타",2020);
    Car c2 = new Car("기아","K5",2022);

    System.out.println(c1);
    System.out.println("나이: " + c1.getAge(2024) + "년");
    System.out.println(c2);
    System.out.println("나이: " + c2.getAge(2024) + "년");

  }
}
