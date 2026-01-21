#include <stdio.h>
// 배열 합계와 평균
int main(){
  int arr[5] = {10, 5, 30, 20, 8};
  int i;
  int max = arr[0];
  for(i = 0; i < 5; i++){
    if(arr[i] > max){
      max = arr[i];
    }
  }
  printf("최댓값: %d\n",max);
  return 0;
}