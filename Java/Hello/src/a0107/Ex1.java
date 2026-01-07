package a0107;

public class Ex1 {
  public static void main(String[] args) {
    // 정육면체의 부피 : v
    // 정육면체 한변의 길이 : n
    // v = n3(세제곱)
    int n = 3;

    int v = volum(n);
    System.out.printf("한 변의 길이가 %d인 정육면체의 부피: %d",n,v);
    
  }

  private static int volum(int n) {
    return n * n * n;
  }
}
