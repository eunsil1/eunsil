package a0204.exam;

public class Exam01 {
  public static void main(String[] args) {
    
 
  // 1. 배열의 최댓값과 최솟값을 찾아 출력하세요.
  // 2. 배열의 평균값을 계산하여 출력하세요. (소수점 둘째 자리까지)
  // 3. 배열에서 짝수만 필터링하여 새로운 배열에 저장하고 출력하세요.
  // 4. 배열을 오름차순으로 정렬하여 출력하세요.

  int[] arr = { 3, 7, 2, 9, 1, 5, 8, 4, 6 };
  // 배열에 최대값 최소값찾기
  int max = arr[0];
  int min = arr[0];
  for(int i = 0;i<arr.length;i++){
    if (arr[i] > max) {
      max = arr[i];
    }
    if (arr[i] < min) {
      min = arr[i];
    }
  }System.out.println("최대값: "+max);System.out.println("최소값: "+min);

  // 2. 평균값 계산
  int sum = 0;
  for(int num : arr){
    sum += num;
  }
  double average = (double) sum / arr.length;
  System.out.printf("평균값: %.2f\n", average);

  // 3. 짝수만 필터링
  int evenCount = 0;
  for(int num : arr){
    if(num % 2 == 0){ //짝수일때 evenCount에 1씩 증가 - 총 배열 수
      evenCount++;
    }
  }
  int[] evenArr = new int[evenCount];
  int index = 0;
  for(int num : arr){
    if(num % 2 == 0){
      evenArr[index++] = num;
    }
  }
  System.out.print("짝수 배열: [");
  for(int i = 0; i < evenArr.length; i++){
    System.out.print(evenArr[i]);
    if(i < evenArr.length - 1){
      System.out.print(", ");
    }
  }
  System.out.println("]");

  // 4. 오름차순 정렬(버블정렬)
  int[] sortedArr = arr.clone(); //원본 배열 복사
  for(int i = 0; i < sortedArr.length; i++){
    for(int j = 0; j < sortedArr.length -1 -i; j++){
      if(sortedArr[j] > sortedArr[j] + 1){
        int temp = sortedArr[j];
        sortedArr[j] = sortedArr[j+1];
        sortedArr[j+1] = temp;
      }
    }
  }
  // 정렬된 값을 출력
  System.out.print("정렬된 배열: [");
  for (int i = 0; i < sortedArr.length; i++) {
    System.out.print(sortedArr[i]);
    if (i < sortedArr.length - 1) {
      System.out.print(", ");
    }
  }
  System.out.println("]");
}
}
