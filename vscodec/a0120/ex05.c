#include <stdio.h>
int main(){
  int age;
  float height;

  printf("나이와 키를 입력하세요: ");
  scanf("%d %f", &age, &height);
  printf("나이값: %d, 키: %.1f\n", age, height);
  return 0;
}