package a0212.hotel;

import java.util.ArrayList;

public class Room {
  private ArrayList<String> rooms;

  public Room(int roomCount) {
    rooms = new ArrayList<>();
    for(int i = 0; i < roomCount; i++){
      rooms.add((i+1) + "");
    }
  }

  public int getavailableRooms() {
    int count = 0;
    for(String room : rooms){
      if (!room.equals("X")) {
        count++;
      }
    }
    return count;
  }

  public void displayRooms() {
    System.out.println("\n객실 위치 (예약된 좌석: X)");
    for(int i = 0; i < rooms.size(); i++){
      System.out.printf("%2s",rooms.get(i));
      if ((i+1) % 10 == 0) {
        System.out.println();
      }
    }
  }

  public boolean reserveRoom(int roomNumber) {
    if (roomNumber > 0 && roomNumber <= rooms.size() && !rooms.get(roomNumber -1).equals("X")) {
      rooms.set(roomNumber -1, "X");
      return true;
    }
    return false;
  }
  




}
