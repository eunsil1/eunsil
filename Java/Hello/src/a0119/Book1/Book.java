package a0119.Book1;

public class Book {
  String title;
  String author;
  public Object name;

  public Book(String title, String author) {
    this.title = title;
    this.author = author;
  }

  @Override
  public String toString() {
    return "Book{title= '" + title + "' , author='" + author + "'}";
  }

  

  
}
