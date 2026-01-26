package a0123.List;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;

public class Set1 {
  public static void main(String[] args) {
    HashSet<String> set = new HashSet<>();

    // 요소 추가
    set.add("사과");
    set.add("바나나");
    set.add("오렌지");
    set.add("사과");  // 중복 추가 시도 (무시됨)

    for(String item : set){
      System.out.println(item);
    }

    System.out.println(set.size()); // 3

    // 요소 확인
    boolean hasApple = set.contains("사과"); // true
    System.out.println(hasApple);

    // 요소 삭제
    set.remove("바나나");

    // 반복문으로 순회
    for (String item : set) {
      System.out.println(item);
    }
    
    System.out.println();

    System.out.println("순서있는 입/출력");
    LinkedHashSet<String> set1 = new LinkedHashSet<>();
    set1.add("사과");
    set1.add("바나나");
    set1.add("오렌지");

    // 삽입 순서대로 출력됨
    for (String item : set1) {
        System.out.println(item);
    }
    
    System.out.println();

    System.out.println("정렬된 순서로 출력");
    TreeSet<Integer> set3 = new TreeSet<>();
    set3.add(5);
    set3.add(2);
    set3.add(8);
    set3.add(1);

    // 정렬된 순서로 출력: 1, 2, 5, 8
    for (Integer num : set3) {
        System.out.println(num);
    }

  }
}
