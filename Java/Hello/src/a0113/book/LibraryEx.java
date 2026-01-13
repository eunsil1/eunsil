package a0113.book;

public class LibraryEx {
  public static void main(String[] args) {
    Ebook ebook = new Ebook("이것이 자바다","신용권","2023-1113",5.6,"PDF");
    PrintBook printBook = new PrintBook("첨만나리액트", "이인재", "2079-0124", 464, 0.8);

    System.out.println("Ebook Information: ");
    ebook.displayInfo();
    System.out.println();
    System.out.println("PrintBook Information: ");
    printBook.displayInfo();
  }
}
