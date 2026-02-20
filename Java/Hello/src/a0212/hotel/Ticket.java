package a0212.hotel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class Ticket {
  private ReservationManager reservationManager;

  public Ticket(ReservationManager reservationManager){
    this.reservationManager = reservationManager;
  }

  public void printTicket(int reservationNum){
    try{
      File dir = new File("c:\\hotelTicket");
      if (!dir.exists()) {
        dir.mkdir();
      }
      File file = new File(dir, "ticket_" + reservationNum + ".txt");
      boolean isNewFile = file.createNewFile();
      try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
        if (file.canWrite()) {
          if (!isNewFile) {
            bufferedWriter.newLine();
          }
          String ticketInfo = reservationManager.getReservationDetails(reservationNum);
          if (ticketInfo == null) {
            System.out.println("예매 정보를 찾을 수 없습니다.");
            return;
          }
          bufferedWriter.write(ticketInfo);
          bufferedWriter.flush();
          System.out.println("티켓 출력 성공");
        }else{
          System.out.println("티켓 출력 실패: 권한문제");
        }
      } 
    }catch (Exception e) {
        System.out.println("티켓 출력 실패: " + e.getMessage());
      } 
  }
}
