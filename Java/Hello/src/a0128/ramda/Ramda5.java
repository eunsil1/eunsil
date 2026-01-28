package a0128.ramda;

import java.util.Arrays;
import java.util.List;

public class Ramda5 {
  public static void main(String[] args) {
    List<String> names1 = Arrays.asList("apple","banana","orange");
    names1.sort((s1,s2)-> s2.compareTo(s1)); //내림차순
    // names1.sort((s1, s2) -> s1.compareTo(s2)); 오름차순 a,b,c,d
    System.out.println("내림차순: " + names1);
    System.out.println();
    
    // 람다
    names1.sort(String::compareTo);
    System.out.println("오름차순: " + names1);

    // names1.sort((a, b) -> b.compareTo(a));
    // System.out.println("내림차순 : " + names1);
  }
}
