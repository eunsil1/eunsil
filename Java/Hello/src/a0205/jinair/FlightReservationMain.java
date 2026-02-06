package a0205.jinair;

import java.util.Scanner;

public class FlightReservationMain {
  public static void main(String[] args) {
    FlightManager fm = new FlightManager();
    // FlightManager 객체생성과 동시에 더미 데이터 들어갈 예정(항공편정보)
    Scanner sc = new Scanner(System.in);
    FileC fc = new FileC();
    System.out.println(fm.airplane);
    System.out.println("==============JavaAir 에 오신걸 환영합니다.==============");


    Outter:while (true) {
      System.out.println("1. 항공편 목록\n2. 항공편 예매\n3. 예약 조회\n4. 티켓 저장\n5. 항공편 업로드 \n0. 종료\n");
      System.out.print("메뉴입력> ");
      String menuStr = sc.next();
      sc.nextLine(); //버퍼지우기
      int menu = -1;
      try {
        menu = Integer.parseInt(menuStr);
      } catch (NumberFormatException e) {
        menu = 9;
      }
      switch (menu) {
        case 1:
          fm.displayFlightList(menuStr);
          break;
        
        case 2:
          try {
            fm.bookFlight();
          } catch (InterruptedException e) {
            e.printStackTrace(); //자바가 에러메세지 출력하고 처리
          }
          break;
        
        case 3:
          fm.checkReservation();
          break;
        
        case 4:
          fm.ticketSave();
          break;

        case 5:
          fc.upload();
          break;

        default:
          break;
      }
    }
  }
}
