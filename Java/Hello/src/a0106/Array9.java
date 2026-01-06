package a0106;

public class Array9 {
  public static void main(String[] args) {
    // 행 별 합계 구하기
    int[][] scores = {
      {90,80,70},
      {85,95,88}
    };
    
      for(int i = 0; i < scores.length; i++){
        int sum = 0; //행마다 새로 0으로 초기화
        for(int j = 0; j < scores[i].length; j++){
          sum += scores[i][j];
        }System.out.println("행의 합계 : " + sum); 
      }


  }
}
