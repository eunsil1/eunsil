#include <stdio.h>
#include <string.h>

int main()
{
  char src[] = "Hello";

  char dest[20];

  strcpy(dest, src);
  printf("복사된 문자열: %s\n",dest);

  return 0;
}