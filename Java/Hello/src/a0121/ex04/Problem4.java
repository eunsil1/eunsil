package a0121.ex04;

public class Problem4 {
  public static void main(String[] args) {
    // 1. 모음 개수 세기
        System.out.println("모음 개수: " + StringUtil.countVowels("Hello"));

        // 2. 대문자 변환
        System.out.println("대문자: " + StringUtil.toUpperCase("hello world"));

        // 3. 단어 포함 확인
        System.out.println("World 포함: " + StringUtil.containsWord("Hello World", "World"));
        System.out.println("Java 포함: " + StringUtil.containsWord("Hello World", "Java"));

        // 4. 문자 교체
        System.out.println("문자 교체: " + StringUtil.replaceChar("Hello", 'l', 'L'));
    }
  }

 
