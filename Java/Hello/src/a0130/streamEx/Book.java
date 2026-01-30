package a0130.streamEx;

public class Book {
  private Author author;
  private int year;
  private int price;
  private String title;
  
  public Book(Author author, int year, int price, String title) {
    this.author = author;
    this.year = year;
    this.price = price;
    this.title = title;
  }

  public Author getAuthor() {
    return author;
  }

  public int getYear() {
    return year;
  }

  public int getPrice() {
    return price;
  }

  public String getTitle() {
    return title;
  }

  @Override
  public String toString() {
    return "[title=" + title 
    + ", author=" + author.getName() 
    + ", country=" + author.getCountry() 
    + ", year=" + year 
    + ", price=" + price + "]";

  
}
}