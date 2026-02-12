package a0212.movie;

import java.util.ArrayList;

public class User {
  private String name;
  private ArrayList<Integer> reservationNumbers;
  // 예매번호 저장
  private ArrayList<String> reservedMovies;
  // 예매한 영화 제목 저장
  private ArrayList<Integer> reservedSeats;
  // 예매한 좌석 번호 저장
  private static int reservationCounter = 1;
  // 모든 사용자가 공유하는 예매번호
  private int totalPaid = 0;
  // 누적 결제 금액

  public User(String name) {
    this.name = name;
    this.reservationNumbers = new ArrayList<>();
    this.reservedMovies = new ArrayList<>();
    this.reservedSeats = new ArrayList<>();
  }

  public String getName() {
    return name;
  }

  public ArrayList<Integer> getReservationNumbers() {
    return reservationNumbers;
  }

  public ArrayList<String> getReservedMovies() {
    return reservedMovies;
  }

  public ArrayList<Integer> getReservedSeats() {
    return reservedSeats;
  }

  public static int getReservationCounter() {
    return reservationCounter;
  }

  public int getTotalPaid() {
    return totalPaid;
  }

  public void addReservation(String title, int seatNumber) {
    reservedMovies.add(title);
    //예매한 영화 제목 저장
    reservedSeats.add(seatNumber);
    //예매한 좌석 번호 저장
    reservationNumbers.add(reservationCounter++);
    //예매번호 부여 후 증가
  }

  public void addTotalPaid(int amount) {
    totalPaid += amount;

  }

  public void showReservations() {
    System.out.println("\n 예약 내역");
    for (int i = 0; i < reservedMovies.size(); i++) {
      System.out.println("예매번호: " + reservationNumbers.get(i) +
          " | 영화: " + reservedMovies.get(i) +
          " | 좌석: " + reservedSeats.get(i));
    }
  }

  public void cancelReservation(String movieTitle, int seatNumber) {
    int index = reservedMovies.indexOf(movieTitle);
    // 영화제목으로 인덱스 찾기
    if (index != -1 && reservedSeats.get(index) == seatNumber) {
      //예매한 영화제목과 좌석번호가 일치하는 경우
      reservedMovies.remove(index);
      reservedSeats.remove(index);
      reservationNumbers.remove(index);
    }
  }

  public void clearReservations() {
    //예약 내역 초기화
    reservedMovies.clear(); //list안에 있는 내용 전체 삭제
    //예매한 영화 제목 초기화
    reservedSeats.clear();
    //예매한 좌석 번호 초기화
    reservationNumbers.clear();
    //예매번호 초기화
  }



  

  
}
