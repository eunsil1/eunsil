package a0211.sort;

import java.util.Arrays;

public class SelectionSort {
  public static void main(String[] args) {
    int[] array = {63,34,25,17,22,11,90};
    SelectionSort1(array);
    System.out.println("Sort Array: "+Arrays.toString(array));
  }

  private static void SelectionSort1(int[] array) {
    int n = array.length;
    for(int i = 0; i < n - 1; i++){
      int minIndex = i;
      for(int j = i +1; j < n; j++){
        if (array[j] < array[minIndex]) {
          minIndex = j;
        }
      }
      int temp = array[minIndex];
      array[minIndex] = array[i];
      array[i] = temp;
    }
  }

  /*
   * i = 0 (최소값 11을 찾고 자리 변경)
   * 
   * 11이 최소값이므로 63과 교환
   * 
   * 결과: {11, 34, 25, 17, 22, 63, 90}
   * 
   * i = 1 (최소값 17을 찾고 자리 변경)
   * 
   * 17이 최소값이므로 34와 교환
   * 
   * 결과: {11, 17, 25, 34, 22, 63, 90}
   * 
   * i = 2 (최소값 22를 찾고 자리 변경)
   * 
   * 22가 최소값이므로 25와 교환
   * 
   * 결과: {11, 17, 22, 34, 25, 63, 90}
   * 
   * i = 3 (최소값 25를 찾고 자리 변경)
   * 
   * 25가 최소값이므로 34와 교환
   * 
   * 결과: {11, 17, 22, 25, 34, 63, 90}
   * 
   * i = 4 (최소값 34를 찾음)
   * 
   * 현재 위치가 올바르므로 변경 없음
   * 
   * i = 5 (최소값 63을 찾음)
   * 
   * 현재 위치가 올바르므로 변경 없음
   * 
   * i = 6 (마지막 원소이므로 정렬 완료)
   */
}
