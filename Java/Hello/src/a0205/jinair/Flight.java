package a0205.jinair;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Flight {
  // 목적지, 비행시간, 금액
  private String destination;
  private String time;
  private int price;
  private DecimalFormat priceFormat = new DecimalFormat("#,###원");
  private boolean internationalFlight; //국제선 여부
  private ArrayList<String> seats;

  public Flight(String destination, String time, int price, boolean internationalFlight) {
    this.destination = destination;
    this.time = time;
    this.price = price;
    this.internationalFlight = internationalFlight;
    seats = new ArrayList<String>();
    for(int i = 1; i <= 20; i++){
      seats.add(i+""); //숫자를 문자열로 넣기위해서 ""한거임
    }
  }

  public String getDestination() {
    return destination;
  }

  public void setDestination(String destination) {
    this.destination = destination;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public DecimalFormat getPriceFormat() {
    return priceFormat;
  }

  public void setPriceFormat(DecimalFormat priceFormat) {
    this.priceFormat = priceFormat;
  }

  public boolean getInternationalFlight() {
    return internationalFlight;
  }

  public void setInternationalFlight(boolean internationalFlight) {
    this.internationalFlight = internationalFlight;
  }

  public ArrayList<String> getSeats() {
    return seats;
  }

  public void setSeats(ArrayList<String> seats) {
    this.seats = seats;
  }

  @Override
  public String toString() {
    String priceComma = priceFormat.format(price);
    return ". " + "목적지: " +  destination + ", 출발시간: " + time + ", 가격: " + priceComma ;
  }
  // 좌석정보 출력(좌석을 4개씩 출력하여 가로로 배열)
  public void seatToString(){
    for(int i = 0; i < seats.size()-3; i +=4){
      System.out.printf("|    [%2s]\t\t[%2s][%2s]\t\t[%2s]    |\n",seats.get(i),seats.get(i+1),seats.get(i+2),seats.get(i+3));
    }
  }
  // 4개씩 그룹화하여 출력
  // i += 4로 4칸씩 건너뜀
  // seats.size()-3 까지 반복(마지막 4개처리)
  /*
   * | [ 1] [ 2][ 3] [ 4] |
   * | [ 5] [ 6][ 7] [ 8] |
   * | [ 9] [10][11] [12] |
   * | [13] [14][15] [16] |
   * | [17] [18][19] [20] |
   */
  


}
