#include <stdio.h>
#include <string.h>

int main()
{
  char name[20];
  printf("이름을 입력하세요: ");
  // scanf("%s", name); // 공백전까지만 입력됨
  fgets(name, 20, stdin); // 공백 포함해서 출력
  name[strlen(name) - 1] = '\0';
  printf("입력한 이름: %s\n",name);

  return 0;
}