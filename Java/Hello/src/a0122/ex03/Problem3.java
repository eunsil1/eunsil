package a0122.ex03;

import java.util.ArrayList;

public class Problem3 {
  public static void main(String[] args) {
    ArrayList<Integer> list = new ArrayList<>();

    NumberManager.addNumber(list,10);
    NumberManager.addNumber(list,5);
    NumberManager.addNumber(list,20);
    NumberManager.addNumber(list,8);
    NumberManager.addNumber(list,15);

    System.out.println("최댓값: " + NumberManager.findMax(list));
    System.out.println("최솟값: " + NumberManager.findMin(list));
    System.out.println("합계: " + NumberManager.getSum(list));
  }
}
