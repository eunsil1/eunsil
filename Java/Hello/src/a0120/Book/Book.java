package a0120.Book;

public class Book {
  String title;
  String author;

  public Book(String title, String author) {
    this.title = title;
    this.author = author;
  }

  @Override
  public String toString() {
    return "Book {title=" + title + ", author=" + author + "}";
  }

  

  
}
