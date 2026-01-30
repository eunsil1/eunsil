package a0129;

import java.util.Arrays;
import java.util.List;

public class Stream5 {
  public static void main(String[] args) {
    // 합계 구하기
    List<Integer> numbers = Arrays.asList(1,2,3,4,5);
    int sum = numbers.stream()
    .reduce(0,(a,b) -> a + b);
    // 0 초기값
    // a : 지금까지 누적값
    // 초기값 = 0
    // 0 + 1 = 1
    // 1 + 2 = 3
    // 3 + 3 = 6
    // 6 + 4 = 10
    // 10 + 5 = 15

    // 결과: 15
    System.out.println(sum);

    // 최대값
    int max = numbers.stream()
              .reduce(Integer.MIN_VALUE, Integer::max);
    // 초기값 Integer.Min_VALUE int 가장 작은값 -21억
    // (a, b) -> Integer.max(a, b) == Integer::max

    // 문자열 연결
    List<String> words = Arrays.asList("Hello", "World", "Java");
    String combined = words.stream()
                      .reduce("",(a,b) -> a + " " + b);
    System.out.println(combined);
  }
}
