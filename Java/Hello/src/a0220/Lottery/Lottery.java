package a0220.Lottery;

import java.lang.reflect.Member;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

public class Lottery {
  String randomNum = "";
  int[] randomNumbers = new int[6];
  
  
  //랜덤으로 로또번호가 생성되게 한다.
  //1~45 범위에서 중복없이 6개를 선택

  public void randomLotteryNum() {
    ArrayList<Integer> numbers = new ArrayList<>();

    //1~45까지의 숫자 리스트 생성
    for(int i = 1; i <= 45; i++){
      numbers.add(i);
    }
    
    //리스트를 섞기
    Collections.shuffle(numbers);

    for(int i = 0; i < 6; i++){
      randomNumbers[i] = numbers.get(i);
    }

    //오름차순으로 정렬
    for(int i = 0; i < 5; i++){
      for(int j = i + 1; j < 6; j++){
        if (randomNumbers[i] > randomNumbers[j]) {
          int temp = randomNumbers[i];
          randomNumbers[i] = randomNumbers[j];
          randomNumbers[j] = temp;
        }
      }
    }
    //문자열로 변환
    randomNum = "";
    for(int i = 0; i < 6; i++){
      if (i > 0) randomNum += " ";
      randomNum += String.format("%2d", randomNumbers[i]);

    }
  }

  //랜덤으로 생성된 로또 번호를 출력한다.
  public void lotteryNum() {
    System.out.println("\n[INFO] 로또 당첨 번호 안내 드립니다.");
    System.out.println("\n★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆");
    System.out.println("★☆★☆★☆★☆★☆[" + randomNum + "]★☆★☆★☆★☆★☆");
    System.out.println("★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆");
    SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    Calendar time = Calendar.getInstance();
    String format_time1 = format1.format(time.getTime());
    System.out.println(format_time1);

    System.out.println("==================================================================");

  }

  //로또 결과를 출력한다.
  public void result(){
    Membership m = Membership.getInstance();
    Purchase p = Purchase.getInstance();

    System.out.println("[INFO] "+ m.name + "님의 당첨 결과 3초 후에 안내 드립니다.");
    System.out.println("\n=================================================================");
    Loading l = new Loading();
    l.loading();
    //당첨 번호 개수 계산
    int matchCount = 0;
    for(int i = 0; i < 6; i++){
      for (int j = 0; j < 6; j++) {
        if (p.customerNumbers[i] == randomNumbers[j]) {
          matchCount++;
          break;
        }
      }
    }


    
    System.out.println("[INFO] " + m.name + "님의 로또번호는 " + p.customerNum + "입니다.");
    System.out.println("당첨번호는 " + randomNum + "입니다.");

    if (matchCount == 6) {
      System.out.println("[INFO] 축하드립니다. 1등에 당첨되셨습니다! (6개 일치)");
    }else if (matchCount == 5) {
      System.out.println("[INFO] 축하드립니다. 2등에 당첨되셨습니다! (5개 일치)");
    }else if (matchCount == 4) {
      System.out.println("[INFO] 축하드립니다. 3등에 당첨되셨습니다! (4개 일치)");
    }else if (matchCount == 3) {
      System.out.println("[INFO] 축하드립니다. 4등에 당첨되셨습니다! (3개 일치)");
    }else{
      System.out.println("[INFO] 일치한 번호 " + matchCount + "개");
      System.out.println("[INFO] 낙첨입니다. 다음 기회를 노려보세요.");
    }
  
    System.out.println("\n==================================================================");
    System.out.println("[INFO] 로또 추첨이 완료되었습니다. 감사합니다.");
    System.out.println("\n==================================================================");
  }
}