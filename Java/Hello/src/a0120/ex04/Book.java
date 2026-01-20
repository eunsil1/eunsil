package a0120.ex04;

public class Book {
  String title;
  String author;
  int price;
  
  public Book(String title, String author, int price) {
    this.title = title;
    this.author = author;
    this.price = price;
  }

  @Override
  public String toString() {
    return "제목: " + title + ", 저자: " + author + ", 가격: " + price + "원";
  }

  
}
