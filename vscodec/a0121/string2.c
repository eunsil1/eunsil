#include <stdio.h>

int main()
{
  char name[20];
  printf("이름을 입력하세요: ");
  scanf("%s", name); // 공백전까지만 입력됨
  // fgets(name, sizeof(name), stdin);
  printf("입력한 이름: %s\n",name);

  return 0;
}