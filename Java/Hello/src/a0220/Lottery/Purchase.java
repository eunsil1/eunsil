package a0220.Lottery;

import java.util.Scanner;


public class Purchase {
  // 싱글톤 생성
  private static Purchase p;

  public static Purchase getInstance() {
    if (p == null) {
      p = new Purchase();
    }
    return p;
  }

  public static void freeInstance() {
    p = null;
  }

  private Purchase() {
  };

  String customerNum = "";
  int[] customerNumbers = new int[6];

  public void purchaseQuestion() {
    Membership m = Membership.getInstance();
    Scanner sc = new Scanner(System.in);

    System.out.println("\n[INFO]" + m.name + "님, 안녕하세요."
        + "\n로또를 구매하시겠습니까?"
        + "\n[1] 예 \t [2] 아니요");

    String answer = "";

    for (;;) {
      System.out.print("\n입력 : ");
      answer = sc.next();

      if (answer.equals("1") || answer.equals("예")) {
        letsLottery(); // 로또번호 입력 메서드
        System.out.println("\n=================================================================");
        break;
      } else if (answer.equals("2") || answer.equals("아니요")) {
        System.out.println("\n[ERROR] 로또 구매가 종료되었습니다.");
        System.exit(0);
      } else {
        System.out.println("\n[ERROR] 잘못된 입력입니다. 다시 입력해주세요.");
      }
    }
  }

  private void letsLottery() {
    Scanner sc = new Scanner(System.in);
    System.out.println("\n [INFO] 1~45 범위의 숫자로 총 6개의 로또 번호를 입력해주세요");
    System.out.println("[INFO] 중복된 번호는 입력할 수 없습니다.");

    int[] numbers = new int[6];

    for(int i = 0; i < 6; i++){
      String numStr = "";
      int num = 0;
      boolean isValid = false;

      while (!isValid) {
        System.out.print((i+1) + "번째 번호 입력: " );
        numStr = sc.next();
        try {
          num = Integer.parseInt(numStr);

          //범위 체크
          if (num < 1 || num > 45) {
            System.out.println("[ERROR] 1~45 범위의 숫자만 입력해 주세요");
            continue;
          }
          //중복 체크
          boolean isDuplicate = false;
          for(int j = 0; j < i; j++){
            if (numbers[j] == num) {
              isDuplicate = true;
              break;
            }
          }
          if(isDuplicate){
            System.out.println("[ERROR] 중복된 번호입니다. 다른 번호를 입력해주세요.");
          }else{
            numbers[i] = num;
            isValid = true;
          }
          //numbers[] - 입력된 저장
          //num - 현재 입력된 숫자
          //isDuplicate - 중복 여부 판단
          //isValid - 입력 성공 여부



        } catch (NumberFormatException e) {
          System.out.println("[ERROR] 숫자만 입력해 주세요");
        }

      }
    }

    // 오름차순 정렬
    for (int i = 0; i < 5; i++) {
      for (int j = i + 1; j < 6; j++) {
        if (numbers[i] > numbers[j]) {
          int temp = numbers[i];
          numbers[i] = numbers[j];
          numbers[j] = temp;
        }
      }
    }
    //배열 복사 및 문자열 생성
    this.customerNumbers = numbers.clone();
    customerNum = "";
    for (int i = 0; i < 6; i++) {
      if (i > 0)
      customerNum += " ";
      customerNum += String.format("%2d", numbers[i]);
    }

    System.out.print("\n[INFO] 입력하신 번호는 [");
    for(int i = 0; i < 6; i++){
      if (i > 0) System.out.println(" ");
      System.out.print(String.format("%2d", numbers[i]));
    }
    System.out.println("] 입니다.");


    System.out.println("\n 입력하신 번호로 로또 구매 진행하시겠습니까?"
        + "\n[1] 예 \t[2] 아니요");

    String answer = "";

    for (;;) {
      System.out.print("\n입력 : ");
      answer = sc.next();

      if (answer.equals("1") || answer.equals("예")) {
        order();
        break;
      } else if (answer.equals("2") || answer.equals("아니요")) {
        System.out.println("\n[ERROR] 로또 구매가 종료되었습니다.");
        System.exit(0);
      } else {
        System.out.println("\n[ERROR] 잘못된 입력입니다. 다시 입력해주세요.");
      }

    }

  }

  private void order() {
    Membership m = Membership.getInstance();
    Scanner sc = new Scanner(System.in);
    String pw = "";
    for (;;) {
      System.out.println("\n[INFO] 결제 비밀번호를 입력해주세요.");
      System.out.print("입력 : ");
      pw = sc.next();
      if (pw.equals(m.pw)) {
        System.out.println("\n[INFO] 결제가 완료되었습니다.");
        System.out.println("\n[INFO] 3초 후에 로또 추첨이 진행됩니다.");
        break;
      } else {
        System.out.println("\n[ERROR] 결제 비밀번호가 일치하지 않습니다. 다시 입력해주세요.");
      }
    }

  }

}
