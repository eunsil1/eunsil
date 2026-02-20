package a0212.hotel;

import java.util.ArrayList;
import java.util.Scanner;

public class ReservationManager {
  private ArrayList<Hotel> hotels;
  private ArrayList<User> users;
  private Scanner sc;
  private static Ticket ticket;
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
    String hotelName = sc.nextLine();
    System.out.print("사용자 이름 입력: ");
    String userName = sc.nextLine();
    Hotel hotel = getHotel(hotelName);

    if (hotel == null) {
      System.out.println("해당 호텔이 없습니다.");
      return;
    }

    hotel.getRoom().displayRooms();
    System.out.print("객실 번호 선택: ");
    int roomNumber = Integer.parseInt(sc.nextLine());

    if (bookRoom(userName,hotelName,roomNumber)) {
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

  public void cancelReservation() {
    System.out.print("예약 번호 입력: ");
    int reservationNumber = sc.nextInt();
    sc.nextLine();
    boolean found = false;
    for (User user : users) {
      int index = user.getReservationNumbers().indexOf(reservationNumber);
      if (index != -1) {
        String hotelName = user.getReservedHotels().get(index);
        int roomNumber = user.getReservedRooms().get(index);
        Hotel hotel = getHotel(hotelName);
        if (hotel != null) {
          hotel.getRoom().cancelRoom(roomNumber);
          user.cancelReservation(hotelName, roomNumber);
          System.out.println("예약이 취소되었습니다.");
        }
        found = true;
        break;
      }
    }
    if (!found) {
      System.out.println("해당 예약 번호가 존재하지 않습니다.");
    }
  }

  public void cancelAllReservation() {
    System.out.print("사용자 이름 입력: ");
    String userName = sc.nextLine();
    User user = getUser(userName);

    if (user == null || user.getReservedHotels().isEmpty()) {
      System.out.println(userName + "님은 예약된 내역이 없습니다.");
      return;
    }
    
    ArrayList<String> hotels = new ArrayList<>(user.getReservedHotels());
    ArrayList<Integer> rooms = new ArrayList<>(user.getReservedRooms());
    ArrayList<Integer> numbers = new ArrayList<>(user.getReservationNumbers());

    for(int i = 0; i < hotels.size(); i++){
      String hotelName = hotels.get(i);
      int roomNumber = rooms.get(i);
      int reservationNumber = numbers.get(i);
      Hotel hotel = getHotel(hotelName);
      if (hotel != null) {
        hotel.getRoom().cancelRoom(roomNumber);
        user.cancelReservation(hotelName, roomNumber);
        System.out.println("예약번호 " + reservationNumber + "이(가) 취소되었습니다.");
      }
    }
    user.clearReservations();
    System.out.println("\n" + userName + "님의 모든 예약이 취소되었습니다.");
  }

  public void printTicket() {
    System.out.println("\n=== 티켓 출력 ===");
    System.out.println("예약 번호를 입력하세요: ");
    int reservationNum = -1;

    try {
      reservationNum = Integer.parseInt(sc.nextLine());
    } catch (Exception e) {
      System.out.println("숫자를 입력하세요.");
    }

    if (reservationNum != -1) {
      if (ticket == null) {
        ticket = new Ticket(this);
      }
      ticket.printTicket(reservationNum);
    }
  }

  public String getReservationDetails(int reservationNum) {
    for(User user : users){
      if (user.getReservationNumbers().indexOf(reservationNum) != -1) {
        int index = user.getReservationNumbers().indexOf(reservationNum);
        return "예약번호: " + user.getReservationNumbers().get(index) + " | 호텔명: " + user.getReservedHotels().get(index) + " | 객실 번호: " + user.getReservedRooms().get(index);
      }
    }
    return null;
  }

  public void deleteHotel(Scanner sc) {
    System.out.print("삭제할 호텔명을 입력하세요: ");
    String hotelName = sc.nextLine();
    Hotel hotel = getHotel(hotelName);
    if (hotel != null) {
      for(User user : users){
        ArrayList<String> reservationHotels = user.getReservedHotels();
        if (reservationHotels.contains(hotelName)) {
          ArrayList<Integer> reservationNumbers = new ArrayList<>(user.getReservationNumbers());
          for(int i = 0; i < reservationNumbers.size(); i++){
            if (user.getReservedHotels().get(i).equals(hotelName)) {
              int roomNumber = user.getReservedRooms().get(i);
              hotel.getRoom().cancelRoom(roomNumber);
              System.out.println(reservationNumbers.get(i) + "이(가) 취소되었습니다.");
            }
          }
          user.removeRservationsByHotel(hotelName);
        }
      }
    }
  }
}
