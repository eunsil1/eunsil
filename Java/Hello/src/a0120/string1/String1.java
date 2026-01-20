package a0120.string1;

public class String1 {
  public static void main(String[] args) {
    String str = new String("Java");
    System.out.println("원본 문자열: " + str);
    for(int i = 0; i < str.length(); i++){
      System.out.print(str.charAt(i) + " ");
    }
    System.out.println(str.charAt(1));
    System.out.println("\n charat() 메서드 호출 후 원본 문자열: " + str);
  }
}
