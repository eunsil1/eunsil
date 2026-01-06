package a0106;

public class Array11 {
  public static void main(String[] args) {
    // 1~9까지 저장하기
    int[][] arr = new int [3][3]; //크기만 지정 가능
    //┌─────┬─────┬─────┐
    //│  1  │  2  │  3  │
    //├─────┼─────┼─────┤
    //│  4  │  5  │  6  │
    //├─────┼─────┼─────┤
    //│  7  │  8  │  9  │
    //└─────┴─────┴─────┘


    //┌─────┬─────┬─────┐
    //│  0  │  0  │  0  │
    //├─────┼─────┼─────┤
    //│  0  │  0  │  0  │
    //├─────┼─────┼─────┤
    //│  0  │  0  │  0  │
    //└─────┴─────┴─────┘

    int num = 1; //숫자 증가용 변수
    for(int i = 0; i < arr.length; i++){
      for(int j = 0; j < arr[i].length; j++){
        arr[i][j] = num++;
      }
    }

    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr[i].length; j++) {
        System.out.print(arr[i][j] + " ");
      }
      System.out.println(); //3개 출력 후 줄바뀜
    }
  }
}
