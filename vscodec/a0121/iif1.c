#include <stdio.h>

int main(){
  int age, score;

  printf("나이와 점수를 입력하세요: ");
  scanf("%d %d", &age, &score);
  if (age >= 18 && score >= 60){
    printf("합격");
  }else{
    printf("불합격");
  }
  return 0;
}