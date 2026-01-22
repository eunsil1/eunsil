package a0122.ex02;

public class Problem2 {
  public static void main(String[] args) {
    
    Product p1 = new Product("노트북",1000000,2);
    Product p2 = new Product("마우스",15000,5);

    System.out.println(p1);
    System.out.println("총 가격: " + p1.getTotalPrice() + "원");
    System.out.println(p2);
    System.out.println("총 가격: " + p2.getTotalPrice() + "원");
  }
}
