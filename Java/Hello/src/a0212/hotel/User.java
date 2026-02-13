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

  public static int getReservationCounter() {
    return reservationCounter;
  }

  public int getTotalPaid() {
    return totalPaid;
  }

  

  

  

  
}
