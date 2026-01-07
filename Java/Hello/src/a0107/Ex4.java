package a0107;

public class Ex4 {
  public static void main(String[] args) {
    // 휘발유 8.86리터
    // 주행거리 182.736km
    // 연비 = 주행거리 / 사용량
    
    double fuel = 8.86;
    double distance = 182.736;
    double c = energy(distance, fuel);
    System.out.printf("연비 : %.2f km/L",c );
  }

  private static double energy(double distance, double fuel) {
    return distance / fuel;
  }

 
}

