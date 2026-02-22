package a0212.hotel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReservationTicket {
  private ReservationManager reservationManager;

  public ReservationTicket(ReservationManager reservationManager) {
    this.reservationManager = reservationManager;
  }

  public void printTicket(int reservationNum) {
    try {
      File dir = new File("c:\\hotelReservation");
      if (!dir.exists()) {
        dir.mkdir();
      }
      File file = new File(dir, "ticket_{" + reservationNum + "}.txt");
      boolean isNewFile = file.createNewFile();

      try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
        if (file.canWrite()) {
          if (!isNewFile) {
            bufferedWriter.newLine();
          }
          String ticketInfo = reservationManager.getReservationDetails(reservationNum);
          if (ticketInfo == null) {
            System.out.println("예약 정보를 찾을 수 없습니다.");
            return;
          }
          bufferedWriter.write(ticketInfo);
          bufferedWriter.flush();
          System.out.println("티켓 출력 성공");
        }else{
          System.out.println("티켓 출력 실패: 권한 문제");
        }
      } 
     }catch (Exception e) {
        System.out.println("티켓 출력 실패: " + e.getMessage());
    }
}

  public void updateMovieList() {
    File file = new File("c:\\hotelReservation\\hotelList.txt");

    if (!file.exists()) {
      System.out.println("hotelList.txt 파일이 존재하지 않습니다.");
      return;
    }
    try{BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
      String line;
      System.out.println("===========================================");
      while ((line = bufferedReader.readLine()) != null) {
        line = line.trim();
        if (line.isEmpty()) continue;     
        
        String[] hotels = line.split("/");
        if (hotels.length != 4) {
          System.out.println("잘못된 형식의 호텔 정보: " + line);
        }

        try{
          String hotelName = hotels[0];
          String genre = hotels[1];
          int rows = Integer.parseInt(hotels[2]);
          int totalRooms = Integer.parseInt(hotels[3]);

          if (reservationManager.getHotel(hotelName) != null) {
            System.out.println("중복된 호텔 [" + hotelName + "] 은(는) 추가되지 않습니다.");
            continue;
          }
          Hotel hotel = new Hotel(hotelName, genre, rows, totalRooms);
          reservationManager.addHotel(hotel);
          System.out.println("호텔 추가됨: " + hotel.getName());

        }catch(NumberFormatException e){
          System.out.println("객실 수 또는 행 수가 잘못된 숫자입니다.");
        }
      }
    }catch(IOException e){
      System.out.println("파일 읽기 오류");
    }
  }
}
