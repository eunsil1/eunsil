#include <stdio.h>

int main(){
 int score = 85;
 if (score >= 60){
  printf("합격\n"); //자동 줄바꿈이 없어서 줄바꿈 하고 싶을때 '\n' 붙여줌
 }else{
  printf("불합격\n");
 }
 return 0;
}
