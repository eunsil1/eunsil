package a0211.set;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

//컬랙션
//1. 데이터를 효율적으로 저장
//2. 배열보다 유연하게 크기변경 가능
//3. 자료구조 : List, Map, Set
//4. ArrayList<String> - 제네릭 지원으로 타입의 안정성 제공
//5. 다양한 구현체 제공 : 성능과 특징에 맞게 선택가능

//리스트 (순서대로 저장, 중복허용)
//ArrayList 배열기반, 검색빠름, 삽입, 삭제 느림
//LinkedList 연결리스트 기반, 검색느림, 삽입, 삭제 빠름
//Vector 동기화 기능 지원, ArrayList보다 느림 스레드 안전성
//Stack : LIFO(후입선출) push() 삽입, pop() 삭제

//Queue : FIFO(선입선출)
//Map(Key - value, 키 중복)
//HashMap  - 키 순서 보장 X, 빠른검색속도
//LinkedHashMap  입력순서 유지
//TreeMap  정렬된 상태 유지(키 기준)

//Set : 중복허용X, 순서X
//HashSet : 해시기반, 요소순서 보장안함, 중복 불가
//LinkedHashSet : HashSet과 동일, 입력 순서 유지
//TreeSet : 정렬된 순서 유지(오름차순)

public class Set1 {
  public static void main(String[] args) {
    //객체선언
    Set<String> set = new HashSet<String>();
    //데이터
    set.add("apple");
    set.add("banana");
    set.add("pyopyo");
    set.add("kiwi");
    System.out.println();
    for(String str : set){
      System.out.println(str);
    }
    set.remove("apple");
    set.add("orange");
    set.add("orange");
    // 중복된 요소 저장 안함
    System.out.println();

    Iterator<String> iterSet = set.iterator();
    while (iterSet.hasNext()) {
      System.out.print(iterSet.next() + " ");
    }
    System.out.println();
    System.out.println(set);

    System.out.println(set.contains("orange")); //검색기능

    // 전체데이터 제거
    set.clear();
    System.out.println(set);
    

  }
}
