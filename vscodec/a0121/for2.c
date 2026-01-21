#include <stdio.h>

int main(){
  int dan, i;
  printf("단을 입력하세요: ");
  scanf("%d",&dan);

  for (i = 1; i <= 9; i++){
    printf("%d X %d = %d\n", dan, i, (dan*i));
  }
  
  return 0;
}