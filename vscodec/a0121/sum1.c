#include <stdio.h>

int main()
{
  int n, i, sum = 0;
  printf("정수를 입력하세요 : ");
  scanf("%d", &n);
  for (i = 1; i <= n; i++)
  {
    sum += i;
  }
  printf("1부터 %d 까지의 합 : %d", n, sum);
  return 0;
}