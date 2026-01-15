package a0115;

import java.util.ArrayList;

public class ArrayList4 {
  public static void main(String[] args) {
    ArrayList<String> list = new ArrayList<>();
    list.add("홍길동");
    list.add("김철수");
    list.add("이영희");
    System.out.println("등록된 학생: " + list);

    System.out.println();
    System.out.println("=== 학생 목록 ===");
    System.out.println("1번: " + list.get(0));
    System.out.println("2번: " + list.get(1));
    System.out.println("3번: " + list.get(2));

    System.out.println();
    System.out.println("=== 학생 정보 수정 ===");
    System.out.println("수정 전: " + list);
    list.set(1,"김민수");
    System.out.println("수정 후: " + list);

    System.out.println();
    System.out.println("=== 학생 삭제 ===");
    System.out.println("삭제 전: " + list);
    list.remove(2);
    System.out.println("삭제 후: " + list);
    
    System.out.println();
    System.out.println("최종 학생 목록: " + list);
    System.out.println("총 학생 수: " + list.size());
    }
  }

