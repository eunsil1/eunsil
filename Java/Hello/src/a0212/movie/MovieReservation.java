package a0212.movie;

import java.util.Scanner;

//프로그램 진입점
public class MovieReservation {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    ReservationManager manager = new ReservationManager();
    Ticket ticket = new Ticket(manager); //티켓 객체 생성

    //영화 추가
    manager.addMovie(new Movie("Inception","18:00",12000,50));
    manager.addMovie(new Movie("Interstellar", "20:30", 15000, 50));

    while (true) {
      System.out.println("\n=== 영화 예매 시스템 ===");
      System.out.println("1. 사용자 로그인");
      System.out.println("2. 운영자 로그인");
      System.out.println("0. 종료");
      System.out.print("선택: ");
      String choice = sc.nextLine();

      switch (choice) {
        case "1":
          userMenu(manager, sc);
          break;
        case "2":
          adminLogin(manager, ticket, sc);
          break;
        case "0":
          System.out.println("시스템을 종료합니다.");
          return;
        default:
          System.out.println("잘못된 선택입니다.");
      }
    }
  }

  private static void adminLogin(ReservationManager reservationManager, Ticket ticket, Scanner sc) {
    System.out.println("운영자 비밀번호를 입력하세요: ");
    String password = sc.nextLine();
    //예시
    if (!password.equals("admin123")) {
      System.out.println("비밀번호가 틀렸습니다.");
      return;
    }

    while (true) {
      System.out.println("\n=== 운영자 메뉴 ===");
      System.out.println("1. 영화 삭제");
      System.out.println("2. 상영 영화 갱신");
      System.out.println("3. 영화 정보 수정");
      System.out.println("4. 할인율 설정");
      System.out.println("0. 뒤로가기");
      System.out.print("선택: ");
      String choice = sc.nextLine();

      switch (choice) {
        case "1":
          reservationManager.showMovies();
          reservationManager.deleteMovie(sc);
          break;
        case "2":
          ticket.updateMovieList();
          System.out.println("영화 목록이 갱신되었습니다.");
          break;
        case "3":
          reservationManager.showMovies();
          reservationManager.modifyMovieInfo(sc);
          break;
        case "4":
          reservationManager.setDiscountRate(sc);
          break;
        case "0":
          return;
        default:
          break;
      }
    }
  }

  private static void userMenu(ReservationManager manager, Scanner sc) {
    End:while (true) {
      System.out.println("\n=== 영화관 예매 시스템 ===");
      System.out.println("1. 상영중인 영화 정보");
      System.out.println("2. 영화 예매");
      System.out.println("3. 예약 조회");
      System.out.println("4. 예약 취소");
      System.out.println("5. 예약 일괄 취소");
      System.out.println("6. 티켓 출력");
      System.out.println("0. 뒤로가기");
      System.out.print("선택: ");
      int choice = sc.nextInt(); 
      sc.nextLine(); //개행 문자 처리
      
      switch (choice) {
        case 1:
          manager.showMovies();
          break;
        case 2:
          manager.showMovies();
          manager.movieReservation();
          break;
        case 3:
          manager.checkReservation();
          break;
        case 4:
          manager.cancelReservation();
          break;
        case 5:
          manager.cancelAllReservation();
          break;
        case 6:
          manager.printTicket();
          break;
        case 0:
          break End;
        default:
          System.out.println("잘못된 입력입니다. 다시 선택해주세요");
      }
    }
  }
}
