#include <stdio.h>
int main(){
  char grade;

  printf("등급을 입력하세요: ");
  scanf(" %c", &grade);
  printf("등급: %c\n", grade);

  return 0;
}