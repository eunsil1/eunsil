#include <stdio.h>
int main(){
  int myNum = 15;
  myNum = 10; //재할당
  printf("%d\n", myNum);

  int myOtherNum = 23;
  myNum = myOtherNum;
  printf("%d\n", myNum);
  int x = 5;
  int y = 10;
  int sum = x + y;
  printf("%d\n", myNum);

  int a = 5, b = 6, c = 50;
  printf("%d\n", a+b+c);
  int d,e,f;
  d = e = f = 50;
  printf("%d\n", d+e+f);
  return 0;
}