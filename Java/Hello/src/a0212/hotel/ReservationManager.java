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
    String hotel = sc.nextLine();
    System.out.print("사용자 이름 입력: ");
    String userName = sc.nextLine();
    Hotel hotel = getHotel(name);
  }
}
