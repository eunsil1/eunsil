package a0212.hotel;

import java.util.ArrayList;
import java.util.Scanner;

public class ReservationManager {
  private ArrayList<Hotel> hotels;
  private ArrayList<User> users;
  private Scanner sc;
  private int discountRate = 0;

  public ReservationManager(){
    hotels = new ArrayList<>();
    users = new ArrayList<>();
    sc = new Scanner(System.in);
  }

  public void addHotel(Hotel hotel) {
    hotels.add(hotel);
  }

  public void showHotels() {
    System.out.println("\n호텔 목록");
    for(Hotel hotel : hotels){
      System.out.println(hotel);
    }
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
      int price = hotel.getPrice();
      int discount = (price * discountRate) / 100;
      int finalPrice = price - discount;

      System.out.println("예약이 완료되었습니다.");
      System.out.println("원가: " + price + "원");
      System.out.println("할인율: " + discountRate + "%");
      System.out.println("할인된 금액: " + discount + "원");
      System.out.println("결제 금액: " + finalPrice + "원");

      User user = getUser(userName);
      if (user != null) {
        user.addTotalPaid(finalPrice);
      }else{
        System.out.println("이미 예약된 객실입니다.");
      }
    }
  }

  private boolean bookRoom(String userName, String name, int roomNumber) {
    Hotel hotel = getHotel(name);
    if (hotel == null) {
      System.out.println("해당 호텔이 없습니다.");
      return false;
    }
    if (!hotel.getRoom().reserveRoom(roomNumber)) {
      System.out.println("이미 예약된 객실이거나 잘못된 객실입니다.");
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
    for(Hotel hotel : hotels){
      if (hotel.getName().equals(name)) {
        return hotel;
      }
    }
    return null;
  }

  public void checkReservation() {
    System.out.print("사용자 이름 입력: ");
    String userName = sc.nextLine();
    User user = getUser(userName);
    if (user != null && !user.getReservedHotels().isEmpty()) {
      for(int i = 0; i < user.getReservedHotels().size(); i++){
        System.out.println("예약번호: " + user.getReservationNumbers().get(i) + " | 호텔명: " + user.getReservedHotels().get(i) + " | 객실 번호: " + user.getReservedRooms().get(i));
      }
      System.out.println("총 결제 금액: " + user.getTotalPaid() + "원");
    }else{
      System.out.println("예약된 내역이 없습니다.");
    }
  }
}
