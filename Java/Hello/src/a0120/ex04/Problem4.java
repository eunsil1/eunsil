package a0120.ex04;

import java.util.ArrayList;

public class Problem4 {
  public static void main(String[] args) {
    ArrayList<Book> list = new ArrayList<>();

    BookManager.addBook(list, "자바의 정석", "남궁성", 30000);
    BookManager.addBook(list, "혼자 공부하는 자바", "신용권", 28000);
    BookManager.addBook(list, "이것이 자바다", "신용권", 32000);

    System.out.println("=== 전체 도서 ===");
    for(Book b : list){
      System.out.println(b);
    }

    System.out.println("\n=== 신용권 저자 도서 ===");
    ArrayList<Book> authorBooks = BookManager.findBooksByAuthor(list,"신용권");
    for(Book b : authorBooks){
      System.out.println(b);
    }

    System.out.println("\n=== 총 가격 ===");
    int total = BookManager.getTotalPrice(list);
    System.out.println("총 가격: " + total + "원");

    System.out.println("\n=== 도서 삭제 ===");
    boolean removed = BookManager.removeBook(list,"자바의 정석");
    System.out.println("삭제 결과: " + removed);

    System.out.println("\n=== 삭제 후 전체 도서 ===");
    for(Book b : list){
      System.out.println(b);
    }
  }
}
