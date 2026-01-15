package a0115;

import java.util.ArrayList;

public class ArrayList3 {
  public static void main(String[] args) {

    ArrayList<String> fruits = new ArrayList<>();

    // 삽입, 추가
    fruits.add("사과");
    fruits.add("바나나");
    fruits.add("오렌지");
    

    // 인덱스 요소 읽기
    String fruit1 = fruits.get(0);
    String fruit2 = fruits.get(1);
    String fruit3 = fruits.get(2);

    System.out.println("첫 번째 과일: " + fruit1);
    System.out.println("두 번째 과일: " + fruit2);
    System.out.println("세 번째 과일: " + fruit3);
    

    for(int i = 0; i < fruits.size(); i++){
      System.out.println((i+1) + "번째: " + fruits.get(i));
    }
    System.out.println();
    // 향상된 for문으로 순회
    for(String fruit : fruits){
      System.out.println(fruit);
    }

    // 인덱스 1번요소 바나나 -> 포도로 바꿔보시오
    fruits.set(1, "포도");

    System.out.println("수정 후: " + fruits);
    // 출력: [사과, 포도, 오렌지]

    // 포도 삭제
    fruits.remove(1);
    // fruits.remove("포도"); 값으로 삭제 가능
    // fruits.clear(); 전체 삭제
    System.out.println("삭제 후: " +  fruits);
    
}
}
