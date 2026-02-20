package a0212.hotel;

import java.util.ArrayList;
import java.util.Scanner;

public class ReservationManager {
  private ArrayList<Hotel> hotels;
  private ArrayList<User> users;
  private Scanner sc;
  private static ReservationTicket ticket;
  private int discountRate = 0;

  public ReservationManager() {
    hotels = new ArrayList<>();
    users = new ArrayList<>();
    sc = new Scanner(System.in);
  }

  public void addHotel(Hotel hotel) {
    hotels.add(hotel);
  }

  public void showHotels() {
    System.out.println("\n=== 현재 예약 가능한 호텔 목록 ===");
    for(Hotel hotel : hotels){
      System.out.println(hotel);
    }
  }

  public void hotelReservation() {
    System.out.print("호텔 이름 입력: ");
    String name = sc.nextLine();
    System.out.print("사용자 이름 입력: ");
    String userName = sc.nextLine();
    Hotel hotel = getHotel(name); 

    if (hotel == null) {
      System.out.println("해당 호텔이 없습니다.");
      return;
    }
    hotel.getRoom().displayRooms();
    System.out.println("객실 번호 선택: ");
    int roomNumber = Integer.parseInt(sc.nextLine());
    if (bookRoom(userName, name, roomNumber)) {
      
    }
  }

  private boolean bookRoom(String userName, String name, int roomNumber) {
    Hotel hotel = getHotel(name);
    if (hotel == null) {
      System.out.println("해당 호텔이 없습니다.");
      return false;
    }
    if (!hotel.getRoom().reserveRoom(roomNumber)) {
      System.out.println("이미 예약된 좌석이거나 잘못된 좌석입니다.");
      return false;
    }
    User user = getUser(userName);
    if (user == null) {
      user = new User(userName);
      users.add(user);
    }
    user.addReservation(name, roomNumber);
    return true;
  }

  public User getUser(String userName) {
    for(User user : users){
      if (user.getName().equals(userName)) {
        return user;
      }
    }
    return null;
  }

  public Hotel getHotel(String name) {
    for (Hotel hotel : hotels) {
      if (hotel.getName().trim().equalsIgnoreCase(name.trim())) {
        return hotel;
      }
    }
    return null;
  }

  

  


}
