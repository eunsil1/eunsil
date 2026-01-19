package a0119.Book;

import java.util.ArrayList;

public class BookApp {
  public static void main(String[] args) {
    ArrayList<Book> list = new ArrayList<>();
    list.add(new Book("'자바의 정석'", "'남궁성'"));
    list.add(new Book("'혼자 공부하는 자바'", "'신용권'"));
    list.add(new Book("'이것이 자바다'", "'신용권'"));

    printAll(list);

    System.out.println("=== 수정 ===");
    updateBook(list,"'자바의 정석'","'남궁성(개정판)'");
    printAll(list);

    System.out.println("=== 삭제 ===");
    deleteBook(list,"'혼자 공부하는 자바'");
    printAll(list);

    System.out.println("=== 검색 ===");
    Book b = findBook(list,"'이것이 자바다'");
    System.out.println(b);
  }

  

  





 static Book findBook(ArrayList<Book> list, String title) {
  for(Book b : list){
    if (b.title.equals(title)) {
      return b;
    }
  }
  return null;
  }


 static boolean deleteBook(ArrayList<Book> list, String title) {
  for(int i = 0; i < list.size(); i++){
    if (list.get(i).title.equals(title)) {
      list.remove(i);
      return true;
    }
  }
  return false;
  }






 static boolean updateBook(ArrayList<Book> list, String title, String newAuthor) {
    for(Book b : list){
      if (b.title.equals(title)) {
        b.author = newAuthor;
        return true;
      }
    }
    return false;
  }

 static void printAll(ArrayList<Book> list) {
    for(Book b : list){
      System.out.println(b);
    }
  }

 }