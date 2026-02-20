package a0220.enum1;

public class Test1 {
  public static void main(String[] args) {
    Day today = Day.MONDAY;
    if (today == Day.MONDAY) {
      System.out.println("월요일입니다.");
    }

    switch (today) {
      case MONDAY:
        System.out.println("월요일");
        break;
      case FRIDAY:
        System.out.println("금요일");
        break;
      
    }
  }
}
