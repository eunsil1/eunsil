package a0105;

public class Array1 {
  public static void main(String[] args) {
    // 방법1 선언 후 초기화
    // 배열 선언
    int[] numbers; // int numbers[];
    // 배열 생성 및 초기화
    numbers = new int[5]; //크기가 5인 배열을 생성
    //값을 할당하는 방법
    numbers[0] = 10;
    numbers[1] = 20;
    numbers[2] = 30;
    numbers[3] = 40;
    numbers[4] = 50;

    System.out.println(numbers[3]);

    // 방법2 선언과 동시에 초기화
    int[] nums = {10,20,30,40,50};
    // 또는
    // int[] nums1 = new int[]{10,20,30,40,50};
    System.out.println(nums[0]);
    
    for(int i = 0; i < 5; i++){
      System.out.println(nums[i]);
      // nums[0] = 10
      // nums[1] = 20
      // nums[2] = 30
      // nums[3] = 40
      // nums[4] = 50
    }

    for (int i = 0; i < nums.length; i++) {
      System.out.println(nums[i]);
    }
    // nums.length 5 -> 배열의 갯수
    
  }
}
