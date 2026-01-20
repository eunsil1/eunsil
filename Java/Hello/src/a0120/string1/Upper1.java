package a0120.string1;

public class Upper1 {
  public static void main(String[] args) {
    String str = new String("Java");
    System.out.println("원본 문자열 : " + str);

    System.out.println(str.toLowerCase()); //소문자로 변경
    System.out.println(str.toUpperCase()); //대문자로 변경
    System.out.println("두 메소드 호출 후 원본 문자열 : " + str);
  }
}
