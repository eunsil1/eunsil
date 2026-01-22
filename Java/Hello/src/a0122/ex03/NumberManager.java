package a0122.ex03;

import java.util.ArrayList;

public class NumberManager {

  public static void addNumber(ArrayList<Integer> list, int num) {
    list.add(num);
  }

  public static int findMax(ArrayList<Integer> list) {
    if (list.isEmpty()) {
      return -1;
    }
    int max = list.get(0);
    for(int i = 0; i < list.size(); i++){
      if(list.get(i) > max){
        max = list.get(i);
      }
    }
    return max;
  }

  public static int findMin(ArrayList<Integer> list) {
    if (list.isEmpty()) {
      return -1;
    }

    int min = list.get(0);
    for(int i = 1; i < list.size(); i++){
      if(list.get(i) < min){
        min = list.get(i);
      }
    }
    return min;
  }

  public static int getSum(ArrayList<Integer> list) {
    int sum = 0;

    for(int num : list){
      sum += num;
    }
    return sum;
  }
  }



  
  
