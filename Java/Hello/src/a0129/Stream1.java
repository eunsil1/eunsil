package a0129;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Stream1 {
  public static void main(String[] args) {
    List<Integer> numbers = Arrays.asList(1,2,3,4,5,6);
    List<Integer> evens = numbers.stream()
    .filter(n -> n % 2 == 0)
    .collect(Collectors.toList());

    System.out.println(evens);
    // numbers.stream()
    // .filter(n -> n % 2 ==0)
    // .forEach(System.out::println);

    // List<Integer> evens = numbers.stream()
    // .filter(n -> n % 2 == 0)
    // .toList(); //자바 16이상

    // System.out.println(evens);
  }
}
