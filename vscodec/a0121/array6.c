#include <stdio.h>

int main(){
  int arr[5] = {1, 2, 3, 4, 5};
  int i;

  printf("원본: ");
  for(i = 0; i < 5; i++){
    printf("%d ", arr[i]);
    
  }
  printf("\n");

  printf("역순: ");
  for(i = 4; i >= 0; i--){
    printf("%d ", arr[i]);
  }
  printf("\n");
    
  return 0;
}