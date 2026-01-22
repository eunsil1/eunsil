#include <stdio.h>

// 배열의 최대값과 최소값 찾기
int main(){
  int arr[6];
  int i;
  int max, min;
  
  // 12 7 25 9 18 3
  // 6개의 정수 입력받기
  for(i = 0; i < 6; i++){
    scanf("%d",&arr[i]);
  }
  
  // 첫번째 값을 최대값과 최소값의 초기값으로 설정
  max = arr[0]; // 12
  min = arr[0]; // 12

  for(i = 1; i < 6; i++){
    if(arr[i] > max){
      max = arr[i];
    }
    if(arr[i] < min){
      min = arr[i];
    }
  }

  // 결과 출력
  printf("최대값: %d\n",max);
  printf("최소값: %d\n",min);
  return 0;
}