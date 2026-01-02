package a0102;

public class Switch1 {
  public static void main(String[] args) {
    String grade = "D";


    switch (grade) {
      case "A":
        System.out.println("우수");
        break;
      case "B":
        System.out.println("보통");
        break;
      case "C":
        System.out.println("미흡");
        break;
    
      default:
        System.out.println("재평가");
        break;
    }
  }
}
