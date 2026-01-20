#include <stdio.h>

int main()
{
  int a = 10;

  printf("초기값: %d\n", a);
  printf("a++: %d\n", a++); // 출력 후 증가
  printf("현재값: %d\n", a);
  printf("++a: %d\n", ++a); // 증가 후 출력
  printf("현재값: %d\n", a);

  return 0;
}