package a0107;

public class Ex2 {
  public static void main(String[] args) {
    // 1인분 무게는 180
    // 1g당 칼로리 5.179
    // 3인분의 칼로리를 구하시오

    int n = 3;
    double x = calc(n);
    System.out.printf("삼겹살 %d인분의 칼로리: %.2f kcal",n,x);
  }

  private static double calc(int n) {
    int totalGram = n * 180; //1인분당 180
    double totalKcal = totalGram * 5.179; //1g당 5.179 kcal
    return totalKcal;
  }
  
  }


