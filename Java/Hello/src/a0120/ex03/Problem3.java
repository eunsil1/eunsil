package a0120.ex03;

public class Problem3 {
  public static void main(String[] args) {

    // 1. 단어 개수 세기
    String text1 = "안녕하세요 자바입니다";
    System.out.println("단어 개수: " + StringUtil.countWords(text1));

    // 2. 문자열 뒤집기
    String text2 = "자바뒤집기";
    System.out.println("뒤집기: " + StringUtil.reverseString(text2));

    // 3. 최댓값 찾기
    int[] arr = {10, 5, 20, 8, 15};
    System.out.println("최댓값: " + StringUtil.findMax(arr));

    // 4. 값 포함 여부 확인
    System.out.println("15 포함: " + StringUtil.contains(arr,15));
    System.out.println("100 포함: " + StringUtil.contains(arr,100));
  }
}
