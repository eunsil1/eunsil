package a0115;

import java.util.ArrayList;
import java.util.Arrays;

public class ArrayList2 {
  public static void main(String[] args) {
    // 배열을 arrayList로 받아오기
    String [] array = {"사과","바나나","오렌지"};
    ArrayList<String> list = new ArrayList<>(Arrays.asList(array));
    System.out.println("배열로부터 생성: " + list);
  }
}
