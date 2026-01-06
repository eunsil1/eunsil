package a0106;

public class Array6 {
  public static void main(String[] args) {
    //int[] arr6 = {1,2,3,4,5};
    //배열 안에 배열이 들어있는 구조
    // [{1,2,3},{3,4,5},{6,7,8}] -> 2차원 배열
    // 표, 엑셀시트, 좌석배치, 성적표
    //int[][] arr = new int[3][4];
    //arr[0][0]  arr[0][1]  arr[0][2]
    //arr[1][0]  arr[1][1]  arr[1][2]
    //arr[2][0]  arr[2][1]  arr[2][2]
    //값 직접 넣기
      // int[][] arr = {
      // {1,2,3},
      // {4,5,6},
      // {7,8,9}
      // };
      // 값을 하나씩 넣기
      int[][] arr = new int[2][3];
      arr[0][0] = 10;
      arr[0][1] = 20;
      arr[0][2] = 30;

      arr[1][0] = 40;
      arr[1][1] = 50;
      arr[1][2] = 60;
      // int[][] arr = {
      // {10,20,30},
      // {40,50,60},
      // };
      System.out.println(arr[0][0]);
      System.out.println(arr[0][1]);
      System.out.println(arr[0][2]);
      System.out.println(arr[1][0]);
      System.out.println(arr[1][1]);
      System.out.println(arr[1][2]);

      // 이중 for문
      for(int i = 0; i < arr.length; i++){ //행
        for(int j = 0; j < arr[i].length; j++){ //열
          System.out.print(arr[i][j]+ " ");
        }
        System.out.println(); //줄바꿈
      }


  }
}
