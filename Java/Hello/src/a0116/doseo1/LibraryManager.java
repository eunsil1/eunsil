package a0116.doseo1;

import java.util.ArrayList;
import java.util.Scanner;

public class LibraryManager {
  private ArrayList<Library> librarys;
  // Library를 객체로 리스트로 만듬
  private ArrayList<Library> bookLocation;
  // Library를 객체로 대여한 객체를 만듬

  public LibraryManager(){
    librarys = new ArrayList<>();
    bookLocation = new ArrayList<>();
    librarys.add(new Library("this is java","shin","Section A","979-11-691-229-8"));
    librarys.add(new Library("First Encounter with React", "Lee Inje", "Section B", "979-11-6921-169-7"));
    librarys.add(new Library("The Principles of Web Standards", "Ko Kyunghee", "Section C", "979-11-6303-622-7"));
    // 더미데이터

    


  }

  public void allLibrary() {
    System.out.println("대출가능한 도서보기");
    for(int i = 0; i < librarys.size(); i++){
      Library library = librarys.get(i);
      if (library.isAvailable()) { //true 저장되어있는것만
        System.out.println(library);
      }
    }
  }

  public boolean bookLocations(String libraryName) {
    for(Library library : librarys){
      if (library.getTitle().equalsIgnoreCase(libraryName) && library.isAvailable()) {
        library.book(); //대출처리
        bookLocation.add(library); //대출된 도서 목록 bookLocation(arrayList) 추가
        return true;
      }
    }
    return false;
  }

  // 대출 도서보기
  public void bookLocations() {
    System.out.println("대출한 도서 보기");
    for(Library location : bookLocation){
      System.out.println(location);
    }
  }

  public void addLibrary(String newTitle, String newAuthor, String newLocation, String newIsbn) {
    //Library library = new Library(newTitle, newAuthor, newLocation, newIsbn);
    //librarys.add(library);
    librarys.add(new Library(newTitle, newAuthor, newLocation, newIsbn));
  }

  public void delLibrary(String dname) {
    boolean result = false;
    for(Library library : librarys){
      if (library.getTitle().equalsIgnoreCase(dname)) {
        if (library.isAvailable()) { //대여중이 아닐때
          librarys.remove(library);
          result = true;
          break;
        }else{
          result = false;
          break;
        }
      }
    }
    if (result) {
      System.out.println("삭제됨");
    }else{
      System.out.println("삭제 안됨");
    }
  }

  public void updateLibrary(String uname) {
    Scanner sc = new Scanner(System.in);
    boolean found = false;

    for (Library a : librarys) {
      if (a.getTitle().equals(uname)) {

        System.out.print("뭘 수정할건데?\n"
            + "1.도서 이름\t2.도서 저자\t3.도서 위치\t4.도서 ISBN\n>> ");
        int menu = sc.nextInt();
        sc.nextLine(); // 버퍼 제거

        switch (menu) {
          case 1:
            System.out.print("수정할 이름: ");
            a.setTitle(sc.nextLine());
            break;
          case 2:
            System.out.print("수정할 저자: ");
            a.setAuthor(sc.nextLine());
            break;
          case 3:
            System.out.print("수정할 위치: ");
            a.setLocation(sc.nextLine());
            break;
          case 4:
            System.out.print("수정할 ISBN: ");
            a.setIsbn(sc.nextLine());
            break;
          default:
            System.out.println("1~4번 중에 입력하세요");
            return;
        }

        found = true;
        break; // 찾았으면 반복 종료
      }
    }

    if (!found) {
      System.out.println("찾는 도서가 없어서 업데이트할 수 없습니다.");
    }
  }

  public void showLibrary(String sname) {
    for(Library library : librarys){
      if (library.getTitle().equalsIgnoreCase(sname)) {
        System.out.println(library);
      }
    }
  }
}
