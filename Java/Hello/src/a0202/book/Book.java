package a0202.book;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Book {
  private static Book instance;
  private Book(){
  };

  static Book getInstance(){
    if (instance == null) {
      instance = new Book();
    }
    return instance;
  };

  ArrayList<String> bookList;
  ArrayList<Integer> bookPrice;
  Map<String,Integer> menu;

  public void getMenu(){
    menu = new LinkedHashMap<String, Integer>();
    bookList = new ArrayList<>();
    bookPrice = new ArrayList<>();

    bookList.add("자바의 정석");
    bookList.add("이것이 자바다");
    bookList.add("Do it! 자바 프로그래밍");
    bookList.add("Head First Java");
    bookList.add("Effective Java");
    bookList.add("Clean Code");

    bookPrice.add(1000);
    bookPrice.add(1200);
    bookPrice.add(1000);
    bookPrice.add(1500);
    bookPrice.add(1800);
    bookPrice.add(1300);

    for(int i = 0; i < bookList.size(); i++){
      menu.put(bookList.get(i), bookPrice.get(i));
    }

    DecimalFormat f = new DecimalFormat("0,000원");
    StringBuffer st = new StringBuffer();
    st.append("\n\n")
        .append("+---------------------------------------------------+\n")
        .append("+------------------------도서목록----------------------+\n")
        .append("|            Book                   price           |\n");
    System.out.println(st.toString());
    int s = 1;
    for(Entry<String,Integer> get : menu.entrySet()){
      System.out.printf(": [%d] %-20s\t %s          :\n",s,get.getKey(), f.format(get.getValue()));
      s++;
    }
    System.out.println("+----------------------------------------------------+\n");
  }
}
