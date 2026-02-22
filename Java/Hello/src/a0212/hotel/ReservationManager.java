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

  public void checkReservation() {
    System.out.print("사용자 이름 입력: ");
    String userName = sc.nextLine();
    User user = getUser(userName);
    if (user != null && !user.getReservedHotels().isEmpty()) {
      for(int i = 0; i < user.getReservedHotels().size(); i++){
        System.out.println("예약번호: " +  user.getReservationNumbers().get(i) + 
        " | 호텔: " + user.getReservedHotels().get(i) + 
        " | 객실: " + user.getReservedRooms().get(i));
      }
    System.out.println("총 결제 금액: " + user.getTotalPaid() + "원");
    }else{
      System.out.println("예약된 내역이 없습니다.");
    }
  }

  public void cancelReservation() {
    System.out.print("사용자 이름 입력: ");
    String userName = sc.nextLine();
    User user = getUser(userName);

    if (user == null || user.getReservedHotels().isEmpty()) {
      System.out.println(userName + "님은 예약된 내역이 없습니다.");
      return;
    }
    user.showReservations();
    System.out.print("취소할 예약 번호를 입력하세요: ");
    int reservationNumber = sc.nextInt();
    sc.nextLine();
    int index = user.getReservationNumbers().indexOf(reservationNumber);
    if (index == -1) {
      System.out.println("해당 예약번호의 예약이 없습니다.");
      return;
    }
    String hotelName = user.getReservedHotels().get(index);
    int roomNumber = user.getReservedRooms().get(index);

    Hotel hotel = getHotel(hotelName);
    if (hotel != null) {
      hotel.getRoom().cancelRoom(roomNumber);
      System.out.println("호텔 [" + hotelName + "] 객실 [" + roomNumber + "] 예약이 취소되었습니다.");
    }
    user.cancelReservation(hotelName,roomNumber);
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
        System.out.println("[" + reservationNumber + "] 호텔 [" + hotelName + "] 객실 [" +  roomNumber + "] 취소됨");
      }
    }

    user.clearReservations();
    System.out.println("\n" + userName + "님의 모든 예약이 취소 되었습니다.");
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
        ticket = new ReservationTicket(this);
      }
      ticket.printTicket(reservationNum);
    }
  }

  public String getReservationDetails(int reservationNum) {
    for(User user : users){
      if (user.getReservationNumbers().contains(reservationNum)) {
        int index = user.getReservationNumbers().indexOf(reservationNum);
        return "예약번호: " + user.getReservationNumbers().get(index) + 
        " | 호텔: " + user.getReservedHotels().get(index) + 
        " | 객실: " + user.getReservedRooms().get(index);
      }
    }
    return null;
  }

  public void deleteHotel(Scanner sc) {
    System.out.print("삭제할 호텔 이름을 입력하세요: ");
    String hotelName = sc.nextLine();
    Hotel hotel = getHotel(hotelName);
    if (hotel != null) {
      for(User user : users){
        ArrayList<String> reservedHotels = user.getReservedHotels();
        if (reservedHotels.contains(hotelName)) {
          ArrayList<Integer> reservationNumbers = new ArrayList<>(user.getReservationNumbers());
          for(int i = 0; i < reservationNumbers.size(); i++){
            if (user.getReservedHotels().get(i).equals(hotelName)) {
              int roomNumber = user.getReservedRooms().get(i);
              hotel.getRoom().cancelRoom(roomNumber);
              System.out.println("[" + reservationNumbers.get(i) + "] 예매도 함께 취소되었습니다.");
            }
          }
          user.removeReservationsBymovie(hotelName);
        }
      }
      hotels.remove(hotel);
      System.out.println("[" + hotelName + "] 호텔이 삭제되었습니다.");
    }else{
      System.out.println("해당 호텔이 존재하지 않습니다.");
    }
  }

  public void modifyHotelInfo(Scanner sc) {
    System.out.print("수정할 호텔 이름을 입력하세요: ");
    String hotelName = sc.nextLine();
    Hotel hotel = getHotel(hotelName);
    if (hotel == null) {
      System.out.println("해당 호텔이 없습니다.");
      return;
    }

    System.out.println("현재 정보: " + hotel.getName() + ", " + hotel.getLocation() + ", " + hotel.getPrice() + "원");
    System.out.println("수정할 정보를 입력하세요. 미 입력시 기존 정보 유지됩니다. 또한 모든 예약은 취소 됩니다.");

    for(User user : users){
      ArrayList<String> reservedHotels = user.getReservedHotels();
      if (reservedHotels.contains(hotelName)) {
        ArrayList<Integer> reservationNumber = new ArrayList<>(user.getReservationNumbers());
        for(int i = 0; i < reservationNumber.size(); i++){
          if (user.getReservedHotels().get(i).equals(hotelName)) {
            int roomNumber = user.getReservedRooms().get(i);
            hotel.getRoom().cancelRoom(roomNumber);
            System.out.println("[" + reservationNumber.get(i) + "] 예약도 함께 취소되었습니다.");
          }
        }
        user.removeReservationsBymovie(hotelName);
      }
    }

    System.out.print("새 호텔 이름: ");
    String newName = sc.nextLine();
    if (newName.isEmpty()) {
      newName = hotel.getName();
    }
    System.out.print("새 가격: ");
    String priceInput = sc.nextLine();
    int newPrice = 0;
    try {
      if (priceInput.trim().isEmpty()) {
        newPrice = hotel.getPrice();
      }else{
        newPrice = Integer.parseInt(priceInput);
      }
    } catch (Exception e) {
      System.out.println("잘못된 입력입니다. 가격은 숫자여야합니다.");
    }

    System.out.print("새 객실 수: ");
    String seatInput = sc.nextLine();
    int newRooms = 0;
    try {
      if (seatInput.trim().isEmpty()) {
        newRooms = hotel.getRoom().getavailableRooms();
      }else{
        newRooms = Integer.parseInt(seatInput);
      }

      if (newRooms == 0) {
        newRooms = hotel.getRoom().getavailableRooms();
      }
    } catch (Exception e) {
      System.out.println("잘못된 입력입니다. 좌석 수는 숫자여야 합니다.");
      return;
    }

    Hotel updateHotel = new Hotel(newName, seatInput, newPrice, newRooms);
    hotels.remove(hotel);
    hotels.add(updateHotel);
    System.out.println("호텔 정보가 수정되었습니다.");
  }

  public void setDiscountRate(Scanner sc) {
    System.out.println("현재 할인율: " + discountRate + "%");
    System.out.println("할인율(%)을 입력하세요: ");
    try {
      discountRate = Integer.parseInt(sc.nextLine());
      System.out.println("할인율이 " + discountRate + "%로 설정되었습니다.");
    } catch (Exception e) {
      System.out.println("잘못된 입력입니다.");
    }
  }

  public void addHotel(Scanner sc) {
    System.out.print("호텔 이름: ");
    String name = sc.nextLine();

    System.out.print("호텔 주소: ");
    String address = sc.nextLine();

    System.out.print("가격: ");
    int price = sc.nextInt();

    System.out.print("객실 수: ");
    int roomCount = sc.nextInt();
    sc.nextLine();

    hotels.add(new Hotel(name, address, price, roomCount));

    System.out.println("호텔이 성공적으로 추가되었습니다.");
  }

  

  


}
