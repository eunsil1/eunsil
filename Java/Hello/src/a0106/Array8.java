package a0106;

public class Array8 {
  public static void main(String[] args) {
    // 모든 요소의 합
    int[][] arr = {
        { 10, 20 },
        { 30, 40 },
        { 50, 60 }
    };
    int sum = 0;

    for(int i = 0; i < arr.length; i++){
      for(int j = 0; j < arr[i].length; j++){
        sum += arr[i][j];
      }
    }
    System.out.println("합 : " + sum);
    
  }
}
