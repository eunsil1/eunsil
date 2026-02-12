package a0212.movie;

import java.text.DecimalFormat;

public class Movie {
  private String title;
  private String showtime;
  private int price;
  private Theater theater;
  private DecimalFormat priceFormat = new DecimalFormat("#,###원");
  // 가격 포맷

  public Movie(String title, String showtime, int price, int seatCount) {
    this.title = title;
    this.showtime = showtime;
    this.price = price;
    this.theater = new Theater(seatCount);
  }

  public String getTitle() {
    return title;
  }

  public String getShowtime() {
    return showtime;
  }

  public int getPrice() {
    return price;
  }

  public Theater getTheater() {
    return theater;
  }

  public DecimalFormat getPriceFormat() {
    return priceFormat;
  }

  @Override
  public String toString() {
    String formattedPrice = priceFormat.format(price);
    return "영화 제목: " + title + " | 상영 시간: " + showtime + " | 가격: " + formattedPrice + " | 남은 좌석: " + theater.getAvailableSeats() + "석"; 
  }

  

}
