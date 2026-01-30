package a0128.booksystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class BookManager {
  private ArrayList<Book> books;
  private String filename;
  
  public BookManager(){
    this.books = new ArrayList<>();
    this.filename = "c:/Users/tj/memo/book.txt";
  }

  private void ensureDirectory() {
    File file = new File(filename);
    File parentDir = file.getParentFile(); 
    if (parentDir != null && !parentDir.exists()) { 
      parentDir.mkdirs();
      System.out.println("디렉토리가 생성되었습니다." + parentDir.getPath());
      
  }
}

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    BookManager manager = new BookManager();

    while (true) {
            System.out.println("\n=== 도서 관리 시스템 ===");
            System.out.println("1. 도서 추가");
            System.out.println("2. 도서 삭제");
            System.out.println("3. 도서 검색");
            System.out.println("4. 도서 수정");
            System.out.println("5. 도서 목록 보기");
            System.out.println("6. 파일로 저장");
            System.out.println("7. 파일에서 불러오기");
            System.out.println("0. 종료");
            System.out.print(">> ");

            int choice;
            try {
              choice = sc.nextInt();
              sc.nextLine();
            } catch (Exception e) {
              System.out.println("숫자만 입력하세요.");
              sc.nextLine();
              continue;
            }

            switch (choice) {
              case 1:
                System.out.print("책 제목: ");
                String title = sc.nextLine();
                System.out.print("저자: ");
                String author = sc.nextLine();
                System.out.print("ISBN: ");
                String isbn = sc.nextLine();
                System.out.print("가격: ");
                try {
                  double firstPrice = sc.nextDouble();
                  sc.nextLine();
                  manager.creatBook(title, author, isbn, firstPrice);
                } catch (Exception e) {
                  System.out.println("숫자만 입력 가능합니다.");
                  sc.nextLine();
                }
                break;

              case 2:
                System.out.println("도서 제거");
                System.out.println("책 이름을 입력");
                System.out.print(">> ");
                String deleteBook = sc.nextLine();
                manager.deleteBook(deleteBook);
                break;

              case 3:
                System.out.println("도서 검색");
                System.out.println("책 이름을 입력");
                System.out.print(">> ");
                String searchBook = sc.nextLine();
                manager.findBook(searchBook);
                break;
              
              case 4:
                System.out.println("도서 정보 수정");
                System.out.println("책 이름을 입력");
                System.out.print(">>");
                String updateTitle = sc.nextLine();
                manager.updateBook(updateTitle);
                break;

              case 5:
                manager.printAllBooks();
                break;
              
              case 6:
                manager.saveToFile();
                break;
              
              case 7:
                manager.loadfromFile();
                break;
              case 0:
                System.out.println("종료합니다.");
                sc.close();
                return;
              default:
              
                break;
            }

  }
}

 

  private void loadfromFile() {
    File file = new File(filename); 
    if(!file.exists()){
      System.out.println("파일이 없습니다. 새로 시작합니다.");
      return;
    }

    try (BufferedReader br = new BufferedReader(new FileReader(filename))){
      
      String line;
      while ((line = br.readLine()) != null) {
        line = line.trim(); 
        if (!line.isEmpty()) { 
          Book book = Book.fromFileString(line);  
          if(book != null){
            books.add(book);
          }
        }
      }
      System.out.println("파일 불러오기 완료! (" + books.size() + "권)");
    }catch(IOException e){
      System.out.println("파일 읽기 오류: " + e.getMessage());
    }
  }

  private void saveToFile() {
    ensureDirectory();
    try(BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
      for(Book book : books){
        bw.write(book.toFileString());
        bw.newLine(); 
      }
      System.out.println("파일 저장 완료: " + filename);
    } catch (IOException e) {
      System.out.println("파일 저장 오류: " + e.getMessage());
    }
  }

  

  private void printAllBooks() {
    if(books.isEmpty()){
      System.out.println("등록된 도서가 없습니다.");
      return;
    }
    for(Book book : books){
      System.out.println(book);
    }
  }

  private void updateBook(String updateTitle) {
    Scanner sc = new Scanner(System.in);
    Book book = findBookBytitle(updateTitle);
    if(book == null){
      System.out.println("찾는 도서가 없습니다.");
      return;
    }
    System.out.println("책 내용 수정");
    System.out.print("책 제목: ");
    String newTitle = sc.nextLine();
    if (!newTitle.isEmpty()) {
      book.setTitle(newTitle);
    }
    System.out.print("저자: ");
    String newAuthor = sc.nextLine();
    if (!newAuthor.isEmpty()) {
      book.setAuthor(newAuthor);
    }
    System.out.print("ISBN: ");
    String newIsbn = sc.nextLine();
    if(!newIsbn.isEmpty()){
      book.setIsbn(newIsbn);
    }
    System.out.print("가격: ");
    String priceInput = sc.nextLine();
    if(!priceInput.isEmpty()){
      try {
        double newPrice = Double.parseDouble(priceInput);
        book.setPrice(newPrice);
      } catch (NumberFormatException e) {
        System.out.println("가격 입력이 잘못되어 수정이 불가합니다.");
      }
    }
  }

  private void findBook(String searchBook) {
    Book book = findBookBytitle(searchBook);
    if (book != null) {
      System.out.println("[제목: " + book.getTitle() + ", 저자: " + book.getAuthor() + ", ISBN: " + book.getIsbn() + ", 가격: " + book.getPrice());
      return;
    }
    System.out.println("찾는 도서가 없습니다.");
  }

  private void deleteBook(String deleteBook) {
    Book book = findBookBytitle(deleteBook);
    if (book != null) {
      books.remove(book);
      System.out.println(deleteBook + "를 제거 하였습니다.");
      return;
    }
    System.out.println("찾는 도서가 없습니다.");
  }

  private void creatBook(String title, String author, String isbn, double firstPrice) {
    if (findBookBytitle(title) != null) {
      System.out.println("이미 존재하는 책입니다.");
      return;
    }
    books.add(new Book(title, author, isbn, firstPrice));
    // saveToFile();
    System.out.println("도서가 추가 되었습니다.");
  }

  private Book findBookBytitle(String title) {
    for (Book book : books) {
      if (book.getTitle().equals(title)) {
        return book;
      }
    }
    return null;
  }
}
