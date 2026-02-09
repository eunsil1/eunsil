package a0209.string;

public class StringEx05 {
  public static void main(String[] args) {
    System.out.println("=== 이메일 검증 ===");
    String email = "user@example.com";
    boolean isValid = email.contains("@") && email.contains(".");
    System.out.println("이메일: " + email);
    System.out.println("이메일 유효: " + isValid);

    System.out.println("\n=== 파일 확장자 추출 ===");
    String filename = "document.pdf";
    int lastDot = filename.lastIndexOf(".");
    String extension = filename.substring(lastDot + 1);
    System.out.println("파일명: " + filename);
    System.out.println("확장자: " + extension); // "pdf"

    System.out.println("\n=== 공백 제거 ===");
    String text = "  Hello World  ";
    System.out.println("원본: [" + text + "]");
    System.out.println("trim: [" + text.trim() + "]");

    System.out.println("\n=== 문자열 반복 (Java 11+) ===");
    String repeated = "Hello ".repeat(3);
    System.out.println(repeated); // "Hello Hello Hello "

    System.out.println("\n=== 전화번호 포맷팅 ===");
    String phone = "01012345678";
    String formatted = phone.substring(0, 3) + "-" +
        phone.substring(3, 7) + "-" +
        phone.substring(7);
    System.out.println("원본: " + phone);
    System.out.println("포맷팅: " + formatted); // "010-1234-5678"

    System.out.println("\n=== 주민번호 마스킹 ===");
    String ssn = "123456-1234567";
    String masked = ssn.substring(0, 8) + "******";
    System.out.println("원본: " + ssn);
    System.out.println("마스킹: " + masked); // "123456-******"

    System.out.println("\n=== 단어 개수 세기 ===");
    String sentence = "Hello World Java Programming";
    String[] words = sentence.split(" ");
    System.out.println("문장: " + sentence);
    System.out.println("단어 개수: " + words.length);

    System.out.println("\n=== 문자열 뒤집기 ===");
    String original = "Hello";
    StringBuilder sb = new StringBuilder(original);
    String reversed = sb.reverse().toString();
    System.out.println("원본: " + original);
    System.out.println("뒤집기: " + reversed); // "olleH"

    System.out.println("\n=== 대소문자 변환 ===");
    String mixed = "HeLLo WoRLd";
    System.out.println("원본: " + mixed);
    System.out.println("대문자: " + mixed.toUpperCase());
    System.out.println("소문자: " + mixed.toLowerCase());

    System.out.println("\n=== 문자열 치환 ===");
    String text2 = "Java is great. Java is fun.";
    String replaced = text2.replace("Java", "Python");
    System.out.println("원본: " + text2);
    System.out.println("치환: " + replaced);
  }
}
