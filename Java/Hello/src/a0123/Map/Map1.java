package a0123.Map;

import java.util.HashMap;
import java.util.Map;

public class Map1 {
  public static void main(String[] args) {
    HashMap<String,Integer> scores = new HashMap<>();

    // 요소추가
    scores.put("홍길동", 85);
    scores.put("김철수", 90);
    scores.put("이영희", 88);
    
    // 모든 키를 순회
    for(String name : scores.keySet()){ //"홍길동 김철수 이영희" 키들만 set으로 반환
      System.out.println(name + ": " + scores.get(name));
      // scores.get(name) - 해당 점수 조회
    }

    System.out.println();

    // 모든 값 순회 - 점수만 나옴
    for(Integer score : scores.values()){
      System.out.println(score);
    }

    // 키-값 쌍 순회
    for (Map.Entry<String, Integer> entry : scores.entrySet()) {
    System.out.println(entry.getKey() + ": " + entry.getValue());
    // entry key -> String value -> Integer
    // entry.getKey() -> "홍길동","김철수","이영희"
    // entry.getValue() -> 85, 90, 88
    }
  }
}
