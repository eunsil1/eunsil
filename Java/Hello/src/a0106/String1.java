package a0106;

public class String1 {
  public static void main(String[] args) {
    String str1 = "Hello";
    String str2 = "Hello";
    String str3 = new String("Hello");

    // == 연산자 : 참조(주소)비교
    System.out.println(str1 == str2); //100 == 100 true
    System.out.println(str2 == str3); //100 == 200 false

    // equals()메서드 : 내용비교
    System.out.println(str1.equals(str2)); //hello .equals hello true
    System.out.println(str1.equals(str3)); //hello .equals hello true
  }
}
