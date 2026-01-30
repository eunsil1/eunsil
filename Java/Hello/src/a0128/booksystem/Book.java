package a0128.booksystem;

public class Book {
  int id;
  String title;
  String author;
  String isbn;
  double price;

  public Book(String title, String author, String isbn, double price) {
    this.title = title;
    this.author = author;
    this.isbn = isbn;
    this.price = price;
  }

  @Override
  public String toString() {
    return "[제목: " + title + ", 저자: " + author + ", ISBN: " + isbn + ", 가격: " + price + "]";
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public String toFileString() {
    return id + "|" + title + "|" + author + "|" + isbn + "|" + price;
  }

  public static Book fromFileString(String line) {
    try {
      String[] parts = line.split("\\|",4); 
      if (parts.length == 4) {
        String title = parts[0].trim(); 
        String author = parts[1].trim(); 
        String isbn = parts[2].trim();
        double price = Double.parseDouble(parts[3].trim()); 
        return new Book(title, author, isbn, price);  
      }
    } catch (NumberFormatException e) {
      return null;
    }
    return null;
  }

  
  
  

  
  
  
}
