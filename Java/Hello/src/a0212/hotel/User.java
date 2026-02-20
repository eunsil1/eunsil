package a0212.hotel;

import java.util.ArrayList;

public class User {
  private String name;
  private ArrayList<Integer> reservationNumbers;
  private ArrayList<String> reservedHotels;
  private ArrayList<Integer> reservedRooms;
  private static int reservationCounter = 1;
  private int totalPaid = 0;
  
  public User(String name) {
    this.name = name;
    this.reservationNumbers = new ArrayList<>();
    this.reservedHotels = new ArrayList<>();
    this.reservedRooms = new ArrayList<>();
  }

  public String getName() {
    return name;
  }

  public ArrayList<Integer> getReservationNumbers() {
    return reservationNumbers;
  }

  public ArrayList<String> getReservedHotels() {
    return reservedHotels;
  }

  public ArrayList<Integer> getReservedRooms() {
    return reservedRooms;
  }

  public void addTotalPaid(int amount) {
    totalPaid += amount;
  }

  public int getTotalPaid() {
    return totalPaid;
  }

  public void addReservation(String name, int roomNumber){
    reservedHotels.add(name);
    reservedRooms.add(roomNumber);
    reservationNumbers.add(reservationCounter);

    System.out.println("예약번호: " + reservationCounter + "번 예약 완료");

    reservationCounter++;
  }

  public void cancelReservation(String hotelName, int roomNumber) {
    int index = reservedHotels.indexOf(hotelName);
    if (index != -1 && reservedRooms.get(index) == roomNumber) {
      reservedHotels.remove(index);
      reservedRooms.remove(index);
      reservationNumbers.remove(index);
    }
  }

  public void clearReservations() {
    reservedHotels.clear();
    reservedRooms.clear();
    reservationNumbers.clear();
  }

  public void removeRservationsByHotel(String hotelName) {
    for(int i = reservedHotels.size() -1; i >=0; i--){
      if (reservedHotels.get(i).equals(hotelName)) {
        reservedHotels.remove(i);
        reservedRooms.remove(i);
        reservationNumbers.remove(i);
      }
    }
  }



  

  

  
}
