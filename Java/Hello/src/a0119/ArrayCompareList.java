package a0119;

import java.util.ArrayList;
import java.util.Arrays;

public class ArrayCompareList {
  public static void main(String[] args) {
    // array 크기 고정 3개
    int [] arr = new int[3];
    arr[0] = 10;
    arr[1] = 20;
    arr[2] = 30;

    for(int i = 0; i < arr.length; i++){
      System.out.println(arr[i]);
    }

    // 향상 for문
    for(int n : arr){
      System.out.println(n);
    }

    // array 삭제 불가
    // arr[1] = 0
    // 새 배열 생성 후 복사해서 함(번거로움)

    // 크기 자동 증가
    // 컬렉션<제네릭>
    ArrayList<Integer> list = new ArrayList<>();
    list.add(10);
    list.add(20);
    list.add(30);

    for (int i = 0; i < list.size(); i++) {
      System.out.println(list.get(i));
    }
    System.out.println();

    // 향상 for문
    for (int n : list) {
      System.out.println(n);
    }
    System.out.println();

    // 통째로 출력 가능
    System.out.println(list);

    // list.remove(1); //인덱스로 삭제 가능
    // list.remove("사과"); //값으로 삭제 가능
    // 자동으로 앞으로 당겨짐

    // array 수정
    // arr[1] = 99;

    // ArrayList 수정
    // list.set(1, 99);

    // arr검색
    for(int i = 0; i < arr.length; i++){
      if(arr[i] == 20){
        System.out.println("찾음");
      }else{
        System.out.println("못찾음");
      }
    }

    // ArrayList 검색
    if(list.contains(20)){
      System.out.println("있음");
    }

    // 인덱스 값을 반환 (없으면 -1)
    int index = list.indexOf(10);

    // array -> ArrayList로 변환
    String[] arr1 = {"사과","배"};
    ArrayList<String> list1 = new ArrayList<>(Arrays.asList(arr1));

  }
}
