package a0211.sort;

import java.util.Arrays;

public class Ex01 {
  public static void main(String[] args) {
    int[] array = {50, 34, 17, 22, 11, 80};
  SelectionSort2(array);
  System.out.println("Sort Array: " + Arrays.toString(array));
  }

  private static void SelectionSort2(int[] array) {
    int n = array.length;
    for(int i = 0; i < n - 1; i++){
      int minIndex = i;
      for(int j = i + 1; j < n; j++){
        if (array[j] < array[minIndex]) {
          minIndex = j;
        }
      }
      int temp = array[minIndex];
      array[minIndex] = array[i];
      array[i] = temp;
    }
  }
  
}
