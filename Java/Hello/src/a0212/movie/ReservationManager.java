package a0212.movie;

import java.util.ArrayList;
import java.util.Scanner;

public class ReservationManager {
  private ArrayList<Movie> movies; //영화 목록
  private ArrayList<User> users; //사용자 목록
  private Scanner sc; //사용자 입력을 위한 스캐너
  private static Ticket ticket; //티켓 객체
  private int discountRate = 0; //할인율

  public ReservationManager(){
    movies = new ArrayList<>(); //영화목록 초기화
    users = new ArrayList<>(); //사용자 목록 초기화
    sc = new Scanner(System.in); //스캐너 초기화
  }

  public void addMovie(Movie movie) {
    movies.add(movie);
    //영화추가
  }

  public void showMovies() {
    System.out.println("\n 현재 상영 중인 영화 목록");
    for(Movie movie : movies){
      System.out.println(movie);
      //movie 클래스에 toString() 출력
    }
  }

  public void movieReservation() {
    System.out.print("영화 제목 입력: ");
    String title = sc.nextLine();
    System.out.print("사용자 이름 입력: ");
    String userName = sc.nextLine();
    Movie movie = getMovie(title);
    // 영화 객체 가져오기
    

    if (movie == null) {
      System.out.println("해당 영화가 없습니다.");
      return;
    }
    movie.getTheater().displaySeats();
    System.out.print("좌석 번호 선택: ");
    int seatNumber = Integer.parseInt(sc.nextLine());

    if(bookSeat(userName,title,seatNumber)){
      int price = movie.getPrice();
      int discount = (price * discountRate) / 100;
      int finalPrice = price - discount;
      System.out.println("예매가 완료되었습니다.");
      System.out.println("원가: " + price + "원");
      System.out.println("할인율: " + discountRate + "%");
      System.out.println("할인된 금액: " + discount + "원");
      System.out.println("결제 금액: " + finalPrice + "원");
      
      //사용자에게 결제 금액 누적
      User user = getUser(userName);
      if (user != null) {
        user.addTotalPaid(finalPrice);
      }else{
        System.out.println("이미 예약된 좌석입니다.");
      }
    }
  }

  private boolean bookSeat(String userName, String title, int seatNumber) {
    Movie movie = getMovie(title);
    if (movie == null) {
      System.out.println("해당 영화가 없습니다.");
      return false;
    }
    if (!movie.getTheater().reserveSeat(seatNumber)) {
      //좌석 예약을 시도했는데 실패했다면 안내메세지 띄우고 작업중단
      System.out.println("이미 예약된 좌석이거나 잘못된 좌석입니다.");
      return false;
    }
    User user = getUser(userName);
    //유저가 존재하는지 확인
    if(user == null){
      //유저가 없으면 새로 생성
      user = new User(userName);
      users.add(user);
    }
    user.addReservation(title, seatNumber);
    //유저 예약 추가(영화 제목과 좌석 번호)
    return true;
  }

  public User getUser(String userName) {
    for(User user : users){
      if (user.getName().equals(userName)) {
        return user;
      }
    }
    return null;
  }

  public Movie getMovie(String title) {
    for(Movie movie : movies){
      if (movie.getTitle().equals(title)) {
        //영화 제목이 같으면 해당 영화 객체를 반환
        return movie;
      }
    }
    return null;
  }

  //예약조회
  public void checkReservation() {
    System.out.print("사용자 이름 입력: ");
    String userName = sc.nextLine();
    User user = getUser(userName);
    if (user != null && !user.getReservedMovies().isEmpty()) {
      // 유저 정보가 존재하고 예약 내역이 있는 경우
      for (int i = 0; i < user.getReservedMovies().size(); i++) {
      System.out.println("예매번호: " + user.getReservationNumbers().get(i) +
          " | 영화: " + user.getReservedMovies().get(i) +
          " | 좌석: " + user.getReservedSeats().get(i));
    }
    System.out.println("총 결제 금액: " + user.getTotalPaid() + "원");
    }else{
      System.out.println("예약된 내역이 없습니다");
    }

  }

  public void cancelReservation() {
    System.out.print("사용자 이름 입력: ");
    String userName = sc.nextLine();
    User user = getUser(userName);

    if (user == null || user.getReservedMovies().isEmpty()) {
      System.out.println(userName + "님은 예약된 내역이 없습니다.");
      return;
    }
    user.showReservations();
    //예약목록 출력
    System.out.print("취소할 예매 번호를 입력하세요: ");
    int reservationNumber = sc.nextInt();
    sc.nextLine();
    int index = user.getReservationNumbers().indexOf(reservationNumber); //내용이 같으면 인덱스 번호 다르면 -1
    if (index == -1) {
      System.out.println("해당 예매번호의 예약이 없습니다.");
      return;
    }
    String movieTitle = user.getReservedMovies().get(index);
    //영화 제목 가져오기
    int seatNumber = user.getReservedSeats().get(index);
    //좌석 번호 가져오기

    Movie movie = getMovie(movieTitle);
    if (movie != null) {
      movie.getTheater().cancelSeat(seatNumber);
      //좌석 취소
      System.out.println("영화 [" + movieTitle + "] 좌석 [" + seatNumber + "] 예약이 취소되었습니다.");
    }
    user.cancelReservation(movieTitle,seatNumber);
    //유저 예약정보 삭제
    
  }

  public void cancelAllReservation() {
    System.out.print("사용자 이름 입력: ");
    String userName = sc.nextLine();
    User user = getUser(userName);

    if (user == null || user.getReservedMovies().isEmpty()) {
      System.out.println(userName + "님은 예약된 내역이 없습니다.");
      return;
    }
    //모든 예약 정보 가져오기
    ArrayList<String> movies = new ArrayList<>(user.getReservedMovies());
    ArrayList<Integer> seats = new ArrayList<>(user.getReservedSeats());
    ArrayList<Integer> numbers = new ArrayList<>(user.getReservationNumbers());

    for(int i = 0; i < movies.size(); i++){
      String movieTitle = movies.get(i);
      int seatNumber = seats.get(i);
      int reservationNumber = numbers.get(i);
      Movie movie = getMovie(movieTitle);
      if (movie != null) {
        movie.getTheater().cancelSeat(seatNumber);
        System.out.println("[" + reservationNumber + "] 영화 [" + movieTitle + "] 좌석 [" + seatNumber + "] 취소됨.");
      }

    }
    //사용자 예약 전체 삭제
    user.clearReservations();
    System.out.println("\n" + userName + "님의 모든 예약이 취소되었습니다.");
  }

  public void printTicket() {
    System.out.println("\n=== 티켓 출력 ===");
    System.out.println("예매 번호를 입력하세요: ");
    int reservationNum = -1;

    try {
      reservationNum = Integer.parseInt(sc.nextLine());
    } catch (Exception e) {
      System.out.println("숫자를 입력하세요.");
    }

    if (reservationNum != -1) {
      //ticket 객체가 null이면 생성
      if (ticket == null) {
        ticket = new Ticket(this);
        //this는 현재 ReservationManager 객체
      }
      ticket.printTicket(reservationNum);
    }
  }

  public String getReservationDetails(int reservationNum) {
    for(User user : users){
      if (user.getReservationNumbers().contains(reservationNum)) {
        int index = user.getReservationNumbers().indexOf(reservationNum);
        return "예매번호: " + user.getReservationNumbers().get(index) +
          " | 영화: " + user.getReservedMovies().get(index) +
          " | 좌석: " + user.getReservedSeats().get(index);
      }
    }
    return null;
    //예약 번호 존재하지 않음
  }

  public void deleteMovie(Scanner sc) {
    System.out.print("삭제할 영화 제목을 입력하세요: ");
    String title = sc.nextLine();
    Movie movie = getMovie(title);
    if (movie != null) {
      //먼저 사용자들의 해당 영화 예약을 모두 취소
      for(User user : users){
        ArrayList<String> reservedMovies = user.getReservedMovies();
        if (reservedMovies.contains(title)) {
          //예매 정보 인덱스 확보
          ArrayList<Integer> reservationNumbers = new ArrayList<>(user.getReservationNumbers());
          for(int i = 0; i < reservationNumbers.size(); i++){
            if (user.getReservedMovies().get(i).equals(title)) {
              int seatNumber = user.getReservedSeats().get(i);
              movie.getTheater().cancelSeat(seatNumber);
              //좌석 취소
              System.out.println("[" + reservationNumbers.get(i) + "] 예매도 함께 취소되었습니다.");
            }
          }
          //사용자 측 예약 정보에서 삭제
          user.removeReservationsBymovie(title);
        }
      }
      movies.remove(movie);
      System.out.println("[" + title + "] 영화가 삭제되었습니다.");
    }else{
      System.out.println("해당 영화가 존재하지 않습니다.");
    }

  }

  public void modifyMovieInfo(Scanner sc) {
    System.out.print("수정할 영화 제목을 입력하세요: ");
    String title = sc.nextLine();
    Movie movie = getMovie(title);
    if (movie == null) {
      System.out.println("해당 영화가 없습니다.");
      return;
    }

    System.out.println("현재 정보: " + movie.getTitle() + ", " + movie.getShowtime() + ", " + movie.getPrice() + "원");
    System.out.println("수정할 정보를 입력하세요. #미 입력 시 기존 정보 유지됩니다. 또한 모든 예매는 취소 됩니다.");

    for (User user : users) {
      ArrayList<String> reservedMovies = user.getReservedMovies();
      if (reservedMovies.contains(title)) {
        // 예매 정보 인덱스 확보
        ArrayList<Integer> reservationNumbers = new ArrayList<>(user.getReservationNumbers());
        for (int i = 0; i < reservationNumbers.size(); i++) {
          if (user.getReservedMovies().get(i).equals(title)) {
            int seatNumber = user.getReservedSeats().get(i);
            movie.getTheater().cancelSeat(seatNumber);
            // 좌석 취소
            System.out.println("[" + reservationNumbers.get(i) + "] 예매도 함께 취소되었습니다.");
          }
        }
        // 사용자 측 예약 정보에서 삭제
        user.removeReservationsBymovie(title);
      }
    }

    System.out.print("새 제목: ");
    String newTime = sc.nextLine();
    if (newTime.isEmpty()) {
      newTime = movie.getShowtime();
      // 미 입력시 원상태로
    }
    System.out.println("새 가격: ");
    String priceInput = sc.nextLine();
    int newPrice = 0;
    try {
      if (priceInput.trim().isEmpty()) {
        //공란을 입력 시 기존 가격 유지
        newPrice = movie.getPrice();
      }else{
        newPrice = Integer.parseInt(priceInput);
      }
    } catch (Exception e) {
      System.out.println("잘못된 입력입니다. 가격은 숫자여야합니다.");
    }

    System.out.print("새 좌석 수: ");
    String seatInput = sc.nextLine();
    int newSeats = 0;
    try {
      if (seatInput.trim().isEmpty()) {
        // 공란 입력 시 기존 좌석 수 유지
        newSeats = movie.getTheater().getAvailableSeats();
      } else {
        newSeats = Integer.parseInt(seatInput);
      }

      if (newSeats == 0) {
        newSeats = movie.getTheater().getAvailableSeats();
        // 미 입력시 원상태로
      }
    } catch (Exception e) {
      System.out.println("잘못된 입력입니다. 좌석 수는 숫자여야 합니다.");
      return;
    }

    //새로운 Movie 객체로 대체
    Movie updatMovie = new Movie(title, newTime, newPrice, newSeats);
    movies.remove(movie);
    movies.add(updatMovie);
    System.out.println("영화 정보가 수정되었습니다.");
  }

  public void setDiscountRate(Scanner sc) {
    System.out.println("현재 할인율" + discountRate + "%");
    System.out.println("할인율(%)을 입력하세요: ");
    try {
      discountRate = Integer.parseInt(sc.nextLine());
      System.out.println("할인율이 " + discountRate + "%로 설정되었습니다.");
    } catch (Exception e) {
      System.out.println("잘못된 입력입니다.");
    }
  }

  public int getDiscountRate(){
    return discountRate;
  }
}


