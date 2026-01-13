package a0113.book;

public class Book {
  private String title; //책제목
  private String author; //저자
  private String ISBN; //책번호

  public Book(String title, String author, String iSBN) {
    this.title = title;
    this.author = author;
    ISBN = iSBN;
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
  public String getISBN() {
    return ISBN;
  }
  public void setISBN(String iSBN) {
    ISBN = iSBN;
  }

  //도서정보 출력 메서드
  public void displayInfo(){
    System.out.println("Title: " + title);
    System.out.println("Author: " + author);
    System.out.println("ISBN: " + ISBN);
  }
  

  
}
