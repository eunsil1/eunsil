package a0102;

public class While {
  public static void main(String[] args) {
    int i = 1;
    while (i <= 5) {
      System.out.println("Hello world " + i);
      i++;      
    }

    int j = 10;
    while (j >= 1) {
      System.out.println("Hello world " + j);
      j--;
    }

    System.out.println("1~100까지 합");
    int sum = 0;
    int num = 1;
    while (num <= 100) {
      sum = sum + num;
      num++;
    }
      System.out.println("1~100 합: " + sum);
  }
}


