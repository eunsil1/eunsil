package a0122.ex04;

public class Problem4 {
  public static void main(String[] args) {
    
    // 1. 문자 개수 세기
    System.out.println("'l' 개수: " + StringUtil.countChar("Hello", 'l'));

    // 2. 문자열 뒤집기
    System.out.println("뒤집기: " + StringUtil.reverseString("자바"));

    // 3. 대문자 변환
    System.out.println("대문자: " + StringUtil.toUpperCase("hello"));
    System.out.println("대문자: " + StringUtil.toUpperCase("Java"));

    // 4. 공백 제거
    System.out.println("공백 제거: " + StringUtil.removeSpaces("Hello World"));

  }

 
    
}
