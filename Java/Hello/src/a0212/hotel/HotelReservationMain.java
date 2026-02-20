package a0212.hotel;

import java.util.Scanner;

public class HotelReservationMain {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    ReservationManager manager = new ReservationManager();
    ReservationTicket ticket = new ReservationTicket();

    manager.addHotel(new Hotel("그랜드 호텔", "서울 강남구", 150000, 50));
    manager.addHotel(new Hotel("리조트 파라다이스", "제주 서귀포시", 200000, 30));
    manager.addHotel(new Hotel("비즈니스 호텔", "부산 해운대구", 120000, 40));
    manager.addHotel(new Hotel("리조트 오션뷰", "강원 속초시", 180000, 25));
    manager.addHotel(new Hotel("럭셔리 호텔", "서울 중구", 250000, 60));
    manager.addHotel(new Hotel("컨벤션 호텔", "인천 송도", 160000, 45));
    manager.addHotel(new Hotel("리조트 힐스", "경주시", 140000, 35));
    manager.addHotel(new Hotel("비치 호텔", "전남 여수시", 170000, 30));
    manager.addHotel(new Hotel("스파 리조트", "충남 태안군", 190000, 20));
    manager.addHotel(new Hotel("시티 호텔", "대구 중구", 130000, 50));
    manager.addHotel(new Hotel("리조트 그린", "강원 평창군", 220000, 25));
    manager.addHotel(new Hotel("비즈니스 센터", "서울 마포구", 110000, 40));
    manager.addHotel(new Hotel("오션 리조트", "부산 기장군", 200000, 30));
    manager.addHotel(new Hotel("힐스 호텔", "경기 가평군", 150000, 35));
    manager.addHotel(new Hotel("리조트 스카이", "전북 무주군", 180000, 25));

    while (true) {
      System.out.println("\n=== 호텔 예매 시스템 ===");
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

  private static void adminLogin(ReservationManager manager, ReservationTicket ticket, Scanner sc) {
    System.out.println("운영자 비밀번호를 입력하세요: ");
    String password = sc.nextLine();
    if (!password.equals("admin123")) {
      System.out.println("비밀번호가 틀렸습니다.");
      return;
    }
  

  while(true) {
    System.out.println("\n=== 운영자 메뉴 ===");
    System.out.println("1. 호텔 삭제");
    System.out.println("2. 호텔 목록 갱신");
    System.out.println("3. 호텔 정보 수정");
    System.out.println("4. 호텔 추가");
    System.out.println("5. 할인율 설정");
    System.out.println("0. 뒤로가기");
    String choice = sc.nextLine();

    switch (choice) {
      case "1":
        manager.showHotels();
        manager.deleteHotel(sc);
        break;
      case "2":
        // ticket.updateHotelList();
        System.out.println("호텔 목록이 갱신되었습니다.");
        break;
      case "3":
        manager.showHotels();
        // manager.modifyHotelInfo(sc);
        break;
      case "4":
        // manager.setDiscountRate(sc);
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
