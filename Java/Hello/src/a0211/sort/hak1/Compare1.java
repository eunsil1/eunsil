package a0211.sort.hak1;

public class Compare1 {
  public static void main(String[] args) {
    String str1 = "apple";
    String str2 = "banana";
    String str3 = "apple";
    System.out.println(str1.compareTo(str2));
    // 아스키코드로 비교 했을대 a(97)가 b(98)보다 작아서 음수-1
    System.out.println(str1.compareTo(str3));
    // 아스키코드로 비교 했을대 값은 값이기에 0
    System.out.println(str2.compareTo(str1));
    // 아스키 코드로 비교 했을때 b가 a보다 크기에 양수
  }
}
