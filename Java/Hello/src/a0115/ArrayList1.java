package a0115;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class ArrayList1 {
  public static void main(String[] args) {
    // 컬렉션
    // <데이터형식> -> 제네릭(참조형만 가능), int는 참조형이 아니므로 안됨, Integer로 씀
    ArrayList<Integer> arrList = new ArrayList<Integer>();
    // add() 메서드를 이용한 요소저장
    arrList.add(40);
    arrList.add(20);
    arrList.add(30);
    arrList.add(10);

    // for문과 get()메서드를 이용하여 요소 출력
    // 배열에서는 arrList,length가 갯수이면 -> 리스트에서는 arrList.size()가 갯수
    // 값을 추가할땐 add()를 쓰고, 값을 불러올땐 get()을 씀
    for(int i = 0; i < arrList.size(); i++){
      System.out.print(arrList.get(i) + " ");
    }

    System.out.println();
    // remove() 메소드를 이용한 요소제거
    arrList.remove(1); //0,1,2,3 실제로는 2번째 데이터가 지워짐
    
    // Enhanced for문 - 향상된 for문
    for(int e : arrList){ // for(변수명 : 객체명)
      System.out.print(e + " "); 
    }
    System.out.println();

    // 컬렉션에서 정렬제공
    Collections.sort(arrList);

    // iterator() 메소드와 get() 메소드를 이용한 요소의 출력
    Iterator<Integer> iter = arrList.iterator();
    while (iter.hasNext()) {
      System.out.print(iter.next() + " ");
    }
    System.out.println();
    // arrayList 수정가능
    // set() 메서드를 이용한 요소의 변경(수정)
    arrList.set(0,20);
    for (int e : arrList) { 
      System.out.print(e + " ");
    }
    System.out.println();
    // size() 메서드를 이요한 요소의 총 갯수
    System.out.println("리스트의 크기 : " + arrList.size());
  }
}
