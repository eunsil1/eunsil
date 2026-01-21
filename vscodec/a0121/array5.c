#include <stdio.h>
int main(){
int arr[5] = {10, 5, 30, 20, 8};
int i, search, found = 0;
printf("검색할 값: ");
scanf("%d", &search);

for (i = 0; i < 5; i++)
{
  if (arr[i] == search)
  {
    printf("%d는 %d번째 위치해 있습니다.", search, i);
    found = 1;
    break;
  }
}
// if(fount == 0){
if (!found)
{
  printf("%d를 찾을수 없습니다.\n", search);
}
return 0;}
