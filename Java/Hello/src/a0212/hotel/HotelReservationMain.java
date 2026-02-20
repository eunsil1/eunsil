package a0212.hotel;

import java.util.Scanner;

public class HotelReservationMain {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    ReservationManager manager = new ReservationManager();
    ReservationTicket ticket = new ReservationTicket(manager);

    manager.addHotel(new Hotel("그랜드 호텔","서울 강남구",150000,50));
    manager.addHotel(new Hotel("리조트 파라다이스", "제주 서귀포시", 200000, 30));

    while (true) {
      System.out.println("\n=== 호텔 예매 시스템 ===");
      System.out.println("1. 사용자 로그인");
      System.out.println("2. 운영자 로그인");
      System.out.println("0. 종료");
      System.out.print("선택: ");
      String choice = sc.nextLine();

      switch (choice) {
        case "1":
          userMenu(manager,sc);
          break;
      
        default:
          break;
      }
    }
  }

  private static void userMenu(ReservationManager manager, Scanner sc) {
    System.out.println("\n=== 호텔 예매 시스템 ===");
    System.out.println("1. 예약 가능한 호텔 정보");
    System.out.println("2. 호텔 예약");
    System.out.println("3. 예약 조회");
    System.out.println("4. 예약 취소");
    System.out.println("5. 예약 일괄 취소");
    System.out.println("6. 예약 티켓 출력");
    System.out.println("0. 뒤로가기");
    System.out.print("선택: ");
    int choice = sc.nextInt();
    sc.nextLine();

    switch (choice) {
      case 1:
        manager.showHotels();
        break;
      case 2:
        manager.showHotels();
        manager.hotelReservation();
        break;
      default:
        break;
    }
  }
}
