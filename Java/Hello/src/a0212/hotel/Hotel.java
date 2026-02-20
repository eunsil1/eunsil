package a0212.hotel;

import java.text.DecimalFormat;

public class Hotel {
  private String name;
  private String location;
  private int price;
  private Room room;
  private DecimalFormat priceFormat = new DecimalFormat("#,###원");
  
  public Hotel(String name, String location, int price, int roomCount) {
    this.name = name;
    this.location = location;
    this.price = price;
    this.room = new Room(roomCount);
  }

  public String getName() {
    return name;
  }

  public String getLocation() {
    return location;
  }

  public int getPrice() {
    return price;
  }

  public Room getRoom() {
    return room;
  }

  public DecimalFormat getPriceFormat() {
    return priceFormat;
  }

  @Override
  public String toString() {
    String formattedpriceFormat = priceFormat.format(price);
    int availableRooms = room.getAvailableRooms();
    return "호텔 이름: {" + name + "} | 위치: {" + location + "} | 가격: {" + formattedpriceFormat + "} | 남은 방: {" + availableRooms + "}개";
  }

  

  

 
}
