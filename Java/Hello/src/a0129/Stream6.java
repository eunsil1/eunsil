package a0129;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Stream6 {
  public static void main(String[] args) {
    List<Integer> numbers = Arrays.asList(3, 1, 4, 1, 5, 9);

    // 최소값
    Optional<Integer> max = numbers.stream()
    .min(Integer::compareTo);

    System.out.println(max);
  }
}
