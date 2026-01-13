package a0113.book;

public class PrintBook extends Book{
  private int pageCount; //추가된 필드 : 페이지 수
  private double weight; //추가된 필드 : 무게

  public PrintBook(String title, String author, String iSBN, int pageCount, double weight) {
    super(title, author, iSBN);
    this.pageCount = pageCount;
    this.weight = weight;
  }

  public int getPageCount() {
    return pageCount;
  }

  public void setPageCount(int pageCount) {
    this.pageCount = pageCount;
  }

  public double getWeight() {
    return weight;
  }

  public void setWeight(double weight) {
    this.weight = weight;
  }

  @Override
  public void displayInfo() {
    super.displayInfo();
    System.out.println("Page Count: " + pageCount);
    System.out.println("Weight: " + weight + "kg");
  }

  
  

}
