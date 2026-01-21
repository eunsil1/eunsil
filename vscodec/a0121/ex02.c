#include <stdio.h>

int main(){
 int score;
 printf("점수를 입력하세요 : ");
 scanf("%d", &score);
 if(score >= 90){
  printf("A등급\n");
 }else if(score >= 80){
  printf("B등급\n");
 }
 else if (score >= 70)
 {
   printf("C등급\n");
 }
 else if (score >= 60)
 {
   printf("D등급\n");
 }
 else
 {
   printf("F등급\n");
 }
 return 0;
}
