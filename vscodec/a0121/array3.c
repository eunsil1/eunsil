#include <stdio.h>
// 배열 합계와 평균
int main(){
  int arr[5] = {10, 20, 30, 40, 53};
  int i, sum = 0;
  for(i = 0; i < 5; i++){
    sum += arr[i]; 
  }
  printf("합계: %d\n",sum);
  printf("평균: %.2f\n", (float)sum / 5);
  return 0;
}