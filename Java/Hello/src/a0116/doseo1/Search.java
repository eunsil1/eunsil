package a0116.doseo1;

import java.util.Scanner;

public class Search {
  public static void main(String[] args) {
    
    Scanner sc = new Scanner(System.in);
    LibraryManager manager = new LibraryManager();
    // 생성과 동시에 더미데이터도 생성메서드에 있기 때문에 생성됨
    boolean flag = true;
    // while문 조건식으로 사용할 변수
    while(flag) {
      System.out.println("\n 도서 검색 시스템에 오신 것을 환영합니다.");
      System.out.println("1.대출 가능한 도서 보기");
      System.out.println("2.도서 대출하기");
      System.out.println("3.대출한 도서 보기");
      System.out.println("4.도서 추가하기");
      System.out.println("5.도서 삭제하기");
      System.out.println("6.도서 정보 수정하기");
      System.out.println("7.도서 내용 보기");
      System.out.println("8.종료");
      System.out.print("원하는 작업을 선택하세요 >>");

      int choice = sc.nextInt();
      sc.nextLine(); //초기화
      switch (choice) {

        case 1:
          System.out.println("대출 가능한 도서");
          manager.allLibrary();
          // 대출 상태가 아닌 책들만 출력
          break;

        case 2:
          System.out.println("도서 대출하기");
          System.out.print("대출하려는 도서 제목 입력 : ");
          String libraryName = sc.nextLine(); //도서명 입력
          if (manager.bookLocations(libraryName)) {
            System.out.println("도서가 성공적으로 대출되었습니다.");
          }else{
            System.out.println("도서가 존재하지 않거나 대출 불가능합니다.");
          }
          break;

        case 3:
          System.out.println("대출한 도서 보기");
          manager.bookLocations();
          break;

        case 4:
          System.out.println("도서 추가하기");
          System.out.print("추가 도서이름: ");
          String newTitle = sc.nextLine();
          System.out.print("추가 도서저자 : ");
          String newAuthor = sc.nextLine();
          System.out.print("도서 위치 : ");
          String newLocation = sc.nextLine();
          System.out.print("도서의 ISBN : ");
          String newIsbn = sc.nextLine();
          manager.addLibrary(newTitle,newAuthor,newLocation,newIsbn);
          System.out.println("도서 추가 완료");
          break;

        case 5:
          System.out.print("삭제시작");
          System.out.print("삭제하는 도서 이름을 입력하세요: ");
          String dname = sc.nextLine();
          if(dname.equals("")){
            System.out.println("삭제 하려는 도서 다시 입력");
            dname = sc.nextLine();
          }
          manager.delLibrary(dname);
          System.out.println("삭제 완료");
          break;

        case 6:
          System.out.print("수정시작");
          System.out.print("수정하려는 도서 이름을 입력하세요: ");
          String uname = sc.nextLine();
          if (uname.equals("")) {
            System.out.println("수정 하려는 도서 다시 입력");
            uname = sc.nextLine();
          }
          manager.updateLibrary(uname);
          System.out.println("수정 완료");
          break;

        case 7:
          System.out.println("조회시작\n 도서이름입력: ");
          String sname = sc.nextLine();
          if (sname.equals("")) {
            System.out.println("수정 하려는 도서 다시 입력");
            sname = sc.nextLine();
          }
          manager.showLibrary(sname);
          System.out.println("수정 완료");
          break;

        case 8:
          System.out.println("프로그램 종료합니다.");
          flag = false;
          break;
          
        default:
          break;
      }
    }
    
  }
}
