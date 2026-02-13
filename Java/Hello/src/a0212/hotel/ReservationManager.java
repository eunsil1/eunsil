package a0212.hotel;

import java.util.ArrayList;
import java.util.Scanner;

public class ReservationManager {
  private ArrayList<Hotel> hotels;
  private ArrayList<User> users;
  private Scanner sc;
  private static ReservationTicket ticket;
  private int discountRate = 0;

  public ReservationManager(){
    hotels = new ArrayList<>();
    // rooms = new ArrayList<>();
    sc = new Scanner(System.in);
  }

  public void showHoels() {
    System.out.println("\n 호텔 목록");
    for(Hotel hotel : hotels){
      System.out.println(hotel);
    }
  }

  public void addHotel(Hotel hotel) {
    hotels.add(hotel);
  }

  public void hotelReservation() {
    System.out.print("호텔명 입력: ");
    String name = sc.nextLine();
    System.out.print("사용자 이름 입력: ");
    String userName = sc.nextLine();
    Hotel hotel = getHotel(name);

    if (hotel == null) {
      System.out.println("해당 호텔이 없습니다.");
      return;
    }
    hotel.getRoom().displayRooms();
    System.out.print("객실 번호 선택: ");
    int roomNumber = Integer.parseInt(sc.nextLine());

    if (bookRoom(userName,name,roomNumber)) {
      
    }
  }

  private boolean bookRoom(String userName, String name, int roomNumber) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'bookRoom'");
  }

  public Hotel getHotel(String name) {
    for(Hotel hotel : hotels){
      if (hotel.getName().equals(name)) {
        return hotel;
      }
    }
    return null;
  }
}
