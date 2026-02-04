package a0202.book;

import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class BookService {
  private boolean reOrder = false;
  private int rentalNum = 1;
  Map<String,Integer> rentalList;

  public BookService(){
    rentalList = new LinkedHashMap<>();
  }

  Book book = Book.getInstance();
  Member member;
  Thread t = new Thread();
  Scanner sc = new Scanner(System.in);

  public void start(){
    System.out.println("어서오세요 더조은도서관입니다.");
    member = new Member(rentalNum);
    book.getMenu();
    rental();
    totalrental(member);
    try{
      System.out.println("기다려 주시면 대여하신 도서를 준비하겠습니다.");
      t.sleep(2000);
      end();
    }catch(Exception e){
      e.printStackTrace();
    }
  }

  private void end() {
    int s = 1;
    StringBuffer message = new StringBuffer();
    message.append("\n\n")
    .append("+----------------------------------------------------+\n ")
    .append("|                                                    | \n ")
    .append("|          회원 " + member.getMemberNum() + " 회원님 대여하신 도서 준비되었습니다      | " + "\n");
    System.out.print(message);
    for (Map.Entry<String, Integer> rental : member.getRentalList().entrySet()) {
      System.out.printf(" | [%d] %-20s : %2d일  |\n", s++, rental.getKey(), rental.getValue());
    }
    System.out.println(" +----------------------------------------------------+");
  }

  private void totalrental(Member member2) {
    int s = 1;
    int totalMoney = 0;
    int bookPrice = 0;
    DecimalFormat f = new DecimalFormat("#,###원");
    String name = member.getMemberNum() + "번";
    StringBuffer message = new StringBuffer();
    message.append("\n\n ")
        .append("+----------------------------------------------------+\n ")
        .append("|                                                    | \n ")
        .append("|             회원 " + name + "회원님 의 대여 내역 입니다         | " + "\n");
    for(Map.Entry<String,Integer> rental : member.getRentalList().entrySet()){
      String bookName = rental.getKey();
      int rentalCount = rental.getValue();
      int bookUnitPrice = book.menu.get(bookName);
      bookPrice = bookUnitPrice * rentalCount;
      totalMoney =  totalMoney + bookPrice;
      String pay = f.format(bookPrice);
      message.append(String.format(" | [%d] %-20s : %2d일  %7s원 |\n", s, bookName, rentalCount, pay));
      s++;
    }
    message.append(" |                                                    |\n ")
        .append("+----------------------------------------------------+ \n")
        .append(" ============ 총 결제 금액은 " + f.format(totalMoney) + "입니다 ========== \n");
    System.out.println(message);
    payment(totalMoney);
  }

  private void payment(int totalMoney) {
    System.out.println("\n결제를 도와드리겠습니다. 카드 넣어주세요");
    int payResult = member.getBalance() - totalMoney;
    try {
      System.out.println("\n결제중입니다....");
      Thread.sleep(2000);
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (payResult < 0) {
      System.out.println("잔액부족, 대여 다시해주세요");
    }else{
      member.setBalance(payResult);
      System.out.println("결제가 완료 되었습니다");
      System.out.println("이용해주셔서 감사합니다");
      rentalNum++;
    }
  }

  private void rental() {
    System.out.println("\n 취소를 원하시면 0번을 눌러주세요");
    end:while(true){
      try{
        System.out.println("\n원하는 도서의 번호를 선택해주세요: ");
        String choice = sc.next();
        int choiceNum = Integer.parseInt(choice.substring(0,1));
        if (choiceNum == 0) {
          System.out.println("대여를 취소합니다");
          System.exit(0);
        }
        sc.nextLine();

        String bookName = book.bookList.get(choiceNum - 1);
        System.out.println("선택 하신 도서는: " + bookName + "입니다. 며칠 대여하시겠습니까?");
        int rentalCount = sc.nextInt();
        sc.nextLine();

        if (reOrder) {
          for(String b1 : rentalList.keySet()){
            if(b1.equals(bookName)){
              int addCount = rentalList.get(b1).intValue() + rentalCount;
              rentalList.replace(bookName, addCount);
            }else{
              rentalList.put(bookName,rentalCount);
            }
          }
        }else{
          rentalList.put(bookName,rentalCount);
        }
        member.setRentalList(rentalList);
        addCount();
        break end;
      }catch(Exception e){
        System.out.println("잘못된 선택입니다.");
      }
    }
  }

  private void addCount() {
    reOrder = false;
    System.out.println("\n대여를 계속 하시겠습니까?");
    System.out.println("예(Y)/아니오(N)");
    String yesOrNo = sc.next();
    if (yesOrNo.equals("예") || yesOrNo.equals("y")) {
      book.getMenu();
      reOrder = true;
      rental();
    }else if(yesOrNo.equals("아니오") || yesOrNo.equals("N")){
      return;
    }
  }

}
