#include <stdio.h>

int main()
{
  int arr[8];
  int even[8], odd[8];
  int evenCount = 0, oddCount = 0;
  int i;

  for (i = 0; i < 8; i++)
  {
    scanf("%d", &arr[i]);
  }

  for (i = 0; i < 8; i++)
  {
    if (arr[i] % 2 == 0)
    {
      even[evenCount++] = arr[i];
    }
    else
    {
      odd[oddCount++] = arr[i];
    }
  }

  printf("짝수: ");
  if (evenCount == 0)
  {
    printf("없음\n");
  }
  else
  {
    for (i = 0; i < evenCount; i++)
    {
      printf("%d ", even[i]);
    }
    printf("\n");
  }

  printf("홀수: ");
  if (oddCount == 0)
  {
    printf("없음\n");
  }
  else
  {
    for (i = 0; i < oddCount; i++)
    {
      printf("%d ", odd[i]);
    }
    printf("\n");
  }

  return 0;
}