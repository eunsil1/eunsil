package a0129;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.IntStream;

public class Stream7 {
  public static void main(String[] args) {
    // IntStream
    IntStream numbers = IntStream.of(1, 2, 3, 4, 5);
    int sum = numbers.sum();
    // OptionalDouble average = numbers.average();
    System.out.println(sum);
    // System.out.println(average);

    IntStream numbers1 = IntStream.of(1, 2, 3, 4, 5);
    OptionalDouble average = numbers1.average();
    System.out.println(average);

    // 일반 스트림에서 합계/평균
    List<Integer> numbers3 = Arrays.asList(1, 2, 3, 4, 5);
    int sum2 = numbers3.stream()
    .mapToInt(Integer::intValue)
    // 일반 스트림에는 sum() 못씀
    // mapToInt - 숫자 스트림으로 변환됨
    .sum();

    System.out.println(sum2);
  }
  }

