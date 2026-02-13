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
      if (!room.equals("X")) {
        count++;
      }
    }
    return count;
  }

  public void displayRooms(){
    for(int i = 0; i < rooms.size(); i++){
      System.out.print(rooms.get(i) + "\t");
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

    int index = roomNumber - 1;
    if(rooms.get(index).equals("X")){
      return false;
    }else{
      rooms.set(index, "X");
      return true;
    }
  }

  public boolean cancelRoom(int roomNumber){
    if (roomNumber < 1 || roomNumber > rooms.size()) {
      return false;
    }
    int index = roomNumber - 1;
    if (rooms.get(index).equals("X")) {
      rooms.set(index, String.valueOf(roomNumber));
      return true;
    }else{
      return false;
    }
  }
}
