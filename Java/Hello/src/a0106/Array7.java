package a0106;

public class Array7 {
  public static void main(String[] args) {
    int[][] scores = {
        { 90, 85, 88 }, // 1번 학생
        { 70, 92, 95 }, // 2번 학생
        { 88, 90, 77 } // 3번 학생
    };

    // 1번 학생의 점수 : 90, 85, 88
    // 2번 학생의 점수 : 70, 92, 95
    // 3번 학생의 점수 : 88, 90, 77

    for(int i = 0; i < scores.length; i++){
      System.out.print((i + 1) + "번 학생의 점수 : ");
      for(int j = 0; j < scores[i].length; j++){
        System.out.print(scores[i][j] + " ");
      } System.out.println();
    }

  }
}
