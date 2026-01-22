#include <stdio.h>
#include <string.h>

// 문자열에서 특정 문자 개수 세기
int main(){
  char str[101];
  char ch;
  int i;
  int count = 0;
  int len;

  printf("전체 문자열을 입력하세요 > ");
  // 문자열 입력받기
  fgets(str,101,stdin);
  // fgets 는 \n도 포함되므로 제거
  len = strlen(str);
  if(len > 0 && str[len-1] == '\n'){
    str[len-1] = '\0';
    len--;
  }
  //Data Structure
  // t 

  printf("\n");
  printf("찾는 문자를 입력하세요 > ");
  // 찾을 문자열 입력하기
  scanf(" %c",&ch); // 공백 문자 주의
  // 앞의 공백은 '공백문자들을 모두 건너 뛰어라'
  // (스페이스, 엔터 \n, 탭 \t) 전부 포함

  // 문자열(Data Structure)에서 특정문자(t) 개수 세기
  for(i = 0; i < len; i++){
    if(str[i] == ch){
      count++;
    }
  }

  // 결과 출력
  printf("%c가 나타나는 횟수: %d\n",ch,count);
  return 0;
}