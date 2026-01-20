#include <stdio.h>
int main(){
  int myNum = 15;
  float myFloatNum = 5.99;
  char myLetter = 'D';
  printf("%d\n",myNum);
  printf("%.2f\n",myFloatNum);
  printf("%c\n",myLetter);

  printf("내가 좋아하는 숫자는 : %d", myNum);

  return 0;
  // %d -> int 형식지정자
}