#include <stdio.h>

int main(){
  int menu;
  printf("메뉴를 선택하세요 (1~3) : ");
  scanf("%d",&menu);
  switch (menu)
  {
  case 1:
    printf("햄버거를 선택하셨습니다.\n");
    break;

  case 2:
    printf("피자를 선택하셨습니다.\n");
    break;
  
  case 3:
    printf("치킨을 선택하셨습니다.\n");
    break;
  
  default:
    printf("잘못된 선택입니다.\n");
    break;
  }
  return 0;

}