#include <stdio.h>

int main(){
  int age, score;

  printf("나이와 점수를 입력하세요: ");
  scanf("%d %d", &age, &score);
  if (age >= 18){
    if (score >= 60){
      printf("합격\n");
    }else{
      printf("나이는 충족하지만 점수 부족\n");
    }
  }else{
    printf("나이가 부족합니다");
  }
  return 0;
}