package a0203.book;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Book {
  private static Book instance; //싱글톤 인스턴스
  private Book(){}; //private 생성자

  public static Book getInstance() {
    if (instance == null) {
      instance = new Book();
    }
    return instance;
  }
  // 객체를 하나만 만들어서 오류를 줄인다.

  ArrayList<String> bookList;      // 도서 제목
  ArrayList<Integer> bookPrice;   // 대여 가격 (1일 기준)
  Map<String, Integer> menu;       // 도서 목록 맵

  public void getMenu(){
    menu = new LinkedHashMap<String, Integer>(); //순서가 있으나 느림
    bookList = new ArrayList<>();
    bookPrice = new ArrayList<>();
    
    // 도서 제목 추가
    bookList.add("자바의 정석");
    bookList.add("이것이 자바다");
    bookList.add("Do it! 자바 프로그래밍");
    bookList.add("Head First Java");
    bookList.add("Effective Java");
    bookList.add("Clean Code");

    // 대여 가격 추가 (1일 기준)
    bookPrice.add(1000);
    bookPrice.add(1200);
    bookPrice.add(1000);
    bookPrice.add(1500);
    bookPrice.add(1800);
    bookPrice.add(1300);

    for(int i = 0; i < bookList.size(); i++){
      menu.put(bookList.get(i),bookPrice.get(i));
    }

    // 메뉴 출력
    DecimalFormat f = new DecimalFormat("0,000원");
    StringBuffer st = new StringBuffer();
    st.append("\n\n")
        .append("+---------------------------------------------------+\n")
        .append("+------------------------도서목록----------------------+\n")
        .append("|            Book                   price           |\n");
    System.out.println(st.toString());

    int s = 1;
    for (Entry<String, Integer> get : menu.entrySet()) {
      System.out.printf(": [%d] %-25s\t %s          :\n", s, get.getKey(), f.format(get.getValue()));
      s++;
    }
    System.out.println("+----------------------------------------------------+\n");
    
  }
}
