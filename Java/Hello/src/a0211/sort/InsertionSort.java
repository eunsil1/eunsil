package a0211.sort;

import java.util.Arrays;

public class InsertionSort {
  public static void main(String[] args) {
    int[] array = {63, 34, 25, 17, 22, 11, 90};
    InsertionSort1(array);
    System.out.println("Sort Array: "+ Arrays.toString(array));
  }

  private static void InsertionSort1(int[] array) {
    int n = array.length; //7
    for(int i = 1; i < n; i++){
      int key = array[i];
      int j = 1 - 1;
      while (j >= 0 && array[j] > key) {
        array[j + 1] = array[j];
        j--;
      }
      array[j + 1] = key;
    }
    //while 안에서 j위치의 값을 i 위치로 이동 시키는 것을 j가 0이 될 동안 반복함
    /*
     * i = 1, key = 34
     * 
     * 63이 34보다 크므로 오른쪽으로 이동
     * 
     * 결과: {34, 63, 25, 17, 22, 11, 90}
     * 
     * i = 2, key = 25
     * 
     * 63이 25보다 크므로 오른쪽으로 이동
     * 
     * 34가 25보다 크므로 오른쪽으로 이동
     * 
     * 결과: {25, 34, 63, 17, 22, 11, 90}
     * 
     * i = 3, key = 17
     * 
     * 63, 34, 25가 17보다 크므로 오른쪽으로 이동
     * 
     * 결과: {17, 25, 34, 63, 22, 11, 90}
     * 
     * i = 4, key = 22
     * 
     * 63, 34, 25가 22보다 크므로 오른쪽으로 이동
     * 
     * 결과: {17, 22, 25, 34, 63, 11, 90}
     * 
     * i = 5, key = 11
     * 
     * 모든 원소가 11보다 크므로 모두 오른쪽으로 이동
     * 
     * 결과: {11, 17, 22, 25, 34, 63, 90}
     * 
     * i = 6, key = 90
     * 
     * 90은 가장 큰 값이므로 이동할 필요 없음
     */
  }
}
