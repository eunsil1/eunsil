package a0212.hotel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.Buffer;

public class ReservationTicket {

  public void updateHotelList(ReservationManager manager) {
    File file = new File("c:\\hotelTicket\\hotelList.txt");

    if (!file.exists()) {
      System.out.println("hotelList.txt 파일이 존재하지 않습니다.");
      return;
    }
    try{BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
    String line;
    System.out.println("======================================");
    while((line = bufferedReader.readLine()) != null) {
      line = line.trim();
      if (line.isEmpty()) continue;        
      
      String[] hotels = line.split("/");
      if (hotels.length != 4) {
        System.out.println("잘못된 형식의 호텔 정보: " + line);
      }
      try {
        String hotel = data[0];
        String genre = data[1];
        int rows = Integer.parseInt(data[2]);
        int totalRooms = Integer.parseInt(data[3]);
        if (reservationManager.getHotel(hotel) != null) {
          System.out.println("중복된 호텔 [" + hotel + "] 은(는) 추가되지 않습니다.");
          continue;
        }
        // Hotel hotel = new Hotel(hotel, genre, rows, totalRooms);
        // reservationManager.addHotel(hotel);
        System.out.println("호텔 추가됨: " + hotel.getName());
      }catch (NumberFormatException e) {
          System.out.println("객실 수 또는 행 수가 잘못된 숫자입니다." + line);
        }
      } catch (Exception e) {
        System.out.println("파일 읽기 오류");
      }
    }
  }
  
  }