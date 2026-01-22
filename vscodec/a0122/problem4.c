#include <stdio.h>

// 배열에서 짝수와 홀수 분리하기
int main(){
// 예시입력
// 2 5 8 11 14 17

// 예시출력
// 짝수 : 2 8 14
// 홀수 : 5 11 17
int arr[6];
int i;
int even[6], odd[6]; // 짝수와 홀수를 저장할 배열
int evenCount = 0, oddCount = 0; // 짝수와 홀수의 개수

// 6개 정수 입력
for(i = 0; i < 6; i++){
  scanf("%d", &arr[i]);
}

// 짝수와 홀수 분리
for(i = 0; i < 6; i++){
  if(arr[i] % 2 == 0){ // 배열을 2로 나눈 나머지 0이면 짝수
    even[evenCount] = arr[i];
    evenCount++;
  }else{ // 홀수인 경우
    odd[oddCount] = arr[i];
    oddCount++;
  }
}

// 출력
printf("짝수: ");
if(evenCount == 0){
  printf("없음\n");
}else{
  for(i = 0; i < evenCount; i++){
    printf("%d", even[i]);
    if(i < evenCount - 1){ //마지막이 아니면 공백 출력
      printf(" ");
    }
  }
  printf("\n");
}

printf("홀수: ");
if (oddCount == 0){
  printf("없음\n");
}
else{
  for (i = 0; i < oddCount; i++){
    printf("%d", odd[i]);
    if (i < oddCount - 1)
    { // 마지막이 아니면 공백 출력
      printf(" ");
    }
  }
  printf("\n");
}
return 0;

}