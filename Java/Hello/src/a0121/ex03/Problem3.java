package a0121.ex03;

import java.util.ArrayList;

public class Problem3 {
  public static void main(String[] args) {
    ArrayList<Integer> list = new ArrayList<>();

    ScoreManager.addScore(list, 85);
    ScoreManager.addScore(list, 92);
    ScoreManager.addScore(list, 78);
    ScoreManager.addScore(list, 65);
    ScoreManager.addScore(list, 88);

    System.out.println("평균: " + ScoreManager.getAverage(list));
    System.out.println("합격자 수 (60점 이상): " + ScoreManager.countPass(list,60));
    System.out.println("최고 점수: " + ScoreManager.getMaxScore(list));

  }
}
