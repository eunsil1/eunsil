package a0105;

public class Array5 {
  public static void main(String[] args) {
    // 배열 {70, 85, 90, 60, 75}
    // 평균을 구하고 
    // 평균점수 이상인 점수만 구하시오

    int[] score = { 70, 85, 90, 66, 75 };
    int sum = 0;
    for(int i = 0; i < score.length; i++){
      sum += score[i];//sum = sum + score[i];
    }
    System.out.println(sum); //386이라는 합계가 나옴
    double avg = (double) sum / score.length; // 계산을 실수로 하려면 (double) 형변환, 캐스팅
    System.out.println(avg);
    
    for(int i = 0; i < score.length; i++){
      if (score[i] >= avg) { 
        //배열에 들어있는 값을 순회하면서 평균보다 크면 출력
        System.out.println("평균보다 큰" + i + " " + score[i]);
      }
  }
}
}
