package a0113.book;

public class Ebook extends Book {

  private double filesize; //e북 파일의 크기(MB)
  private String format; //e북 파일 형식

  public Ebook(String title, String author, String iSBN, double filesize, String format) {
    super(title, author, iSBN);
    this.filesize = filesize;
    this.format = format;
  }

  public double getFilesize() {
    return filesize;
  }

  public void setFilesize(double filesize) {
    this.filesize = filesize;
  }

  public String getFormat() {
    return format;
  }

  public void setFormat(String format) {
    this.format = format;
  }

  @Override
  public void displayInfo() {
    super.displayInfo();
    System.out.println("File Size: " + filesize + "MB");
    System.out.println("Format: " + format);
  }

  
}
