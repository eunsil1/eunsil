#include <stdio.h>

int main(){
  int arr[10];
  int i;
  int sum = 0;
  float average;
  int count = 0;

  for(i = 0; i < 10; i++){
    scanf("%d",&arr[i]);
  }

  for (i = 0; i < 10; i++){
    sum += arr[i];
  }

  average = (float)sum / 10;

  for (i = 0; i < 10; i++)
  {
    if (arr[i] >= average){
      count++;
    }
  }

  printf("평균: %.2f\n", average);
  printf("평균 이상인 값의 개수: %d\n", count);
  return 0;
}