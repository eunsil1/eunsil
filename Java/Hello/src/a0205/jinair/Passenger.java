package a0205.jinair;

import java.time.LocalDate;
import java.time.Period;

public class Passenger {
  private String name; //이름
  private int birthDate; //생년월일(6자리:YYMMDD) - 831016
  private String pw; //비밀번호
  private String seat; //좌석번호
  
  public Passenger(String name, int birthDate) {
    this.name = name;
    this.birthDate = birthDate;
    
  }

  public Passenger(String name, int birthDate, String pw) {
    this.name = name;
    this.birthDate = birthDate;
    this.pw = pw;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(int birthDate) {
    this.birthDate = birthDate;
  }

  public String getPw() {
    return pw;
  }

  public void setPw(String pw) {
    this.pw = pw;
  }

  public String getSeat() {
    return seat;
  }

  public void setSeat(String seat) {
    this.seat = seat;
  }

  // 15세 이상만 국제선 예약가능
  public boolean man15(Passenger p){ //831016
    int y = p.birthDate / 10000; // 831016 -> 83만 남음
    int m = (p.birthDate % 10000) / 100; // 10000으로 나눈 나머지 1016 / 100 -> 10만 남음
    int d = p.birthDate % 100; // 16
    if (y > 0 && y <= 26) {
      y = y + 2000; //2000년대 이후
    }else{
      y += 1900; //1900년대
    }
    // y = (y > 0 && y <= 26) ? y + 2000 : y += 1900 - 삼항 연산자로 써도됨

    LocalDate birthDate2 = LocalDate.of(y,m,d); //생년월일 // 1983 10 16
    LocalDate currentDate = LocalDate.now(); //현재 - 오늘의 년월일 2026/02/05
    int age = Period.between(birthDate2, currentDate).getYears();
    // 생년월일과 오늘의 년월일을 비교해서 년도만 빼서 연령을 구한다.
  
    return age > 15;
  }
  
  //Passenger p = new Passenger("hong", 831026);
}
