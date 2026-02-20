package a0212.hotel;

import java.util.ArrayList;

public class Room {
  private ArrayList<String> rooms;

  public Room(int roomCount){
    rooms = new ArrayList<>();
    for(int i = 1; i <= roomCount; i++){
      rooms.add(String.valueOf(i));
    }
  }

  public int getAvailableRooms() {
    int count = 0;
    for(String room : rooms){
      if (!room.equals("X")) count++;
    }
    return count;
  }

  public void displayRooms(){
    for(int i = 0; i < rooms.size(); i++){
      System.out.printf("%-4s", rooms.get(i));
      if ((i + 1) % 10 == 0) {
        System.out.println();
      }
    }
    System.out.println();
  }

  public boolean reserveRoom(int roomNumber){
    if(roomNumber < 1 || roomNumber > rooms.size()){
      return false;
    }

    if (rooms.get(roomNumber - 1).equals("X")) {
      return false;
    }

    rooms.set(roomNumber - 1, "X");
    return true;
  }

  public boolean cancelRoom(int roomNumber) {
    if (roomNumber < 1 || roomNumber > rooms.size()) {
      return false;
    }

    if (!rooms.get(roomNumber - 1).equals("X")) {
      return false; // 예약되지 않은 방
    }

    // 원래 번호로 복원
    rooms.set(roomNumber - 1, String.valueOf(roomNumber));
    return true;
  }

}
