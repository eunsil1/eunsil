package a0211.set;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Set2 {
  public static void main(String[] args) {
    List<String> items = new ArrayList<>();

    // 중복된 값이 있는 리스트
    items.add("apple");
    items.add("banana");
    items.add("orange");
    items.add("apple");
    items.add("grape");

    // hashSet을 사용해 중복제거
    Set<String> uniqueItems = new HashSet<>(items);
    System.out.println("중복을 제거한 리스트: " + uniqueItems);
  }
}
