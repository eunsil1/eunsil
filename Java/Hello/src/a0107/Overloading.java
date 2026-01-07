package a0107;

public class Overloading {
  public static void main(String[] args) {
    
    int result1 = add(10,20); 
    System.out.println(result1);

    int result2 = add(10, 20, 30);
    System.out.println(result2);

    double result3 = add(10.5 , 20.3);
    System.out.println(result3);

    double result4 = add(10, 20.3);
    System.out.println(result4);
  }
  
  //실수 2개를 더하는 메서드(매개변수 타입 다름)
  private static double add(double d, double e) {
    return d + e;
  }

  //정수와 실수를 더하는 메서드
  private static double add(int d, double e) {
    return d + e;
  }

  // 정수 2개를 더하는 메서드
  private static int add(int i, int j) {
    return i + j;
  }

  // 정수 3개를 더하는 메서드(매개변수 개수 다름)
  private static int add(int i, int j, int k) {
    return i + j + k;
  }
}
