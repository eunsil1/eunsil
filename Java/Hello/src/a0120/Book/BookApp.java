package a0120.Book;

import java.util.ArrayList;

public class BookApp {
  public static void main(String[] args) {
    ArrayList<Book> list = new ArrayList<>();
    list.add(new Book("자바의 정석", "남궁성"));
    list.add(new Book("혼자 공부하는 자바", "신용권"));
    list.add(new Book("이것이 자바다", "신용권"));

    printAll(list);
  }

  private static void printAll(ArrayList<Book> list) {
    for(Book b : list){
      System.out.println(b);

  }
}
}
