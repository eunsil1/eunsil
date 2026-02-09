package a0209.string;

public class StringBasics {
  public static void main(String[] args) {
    // 방법1
    String str1 = "Hello";
    String str2 = "Hello";

    // 방법 2: new 연산자 사용
    String str3 = new String("Hello");
    String str4 = new String("Hello");

    // 방법3: 배열로부터
    char[] chars = { 'H', 'e', 'l', 'l', 'o' };
    String str5 = new String(chars);

    System.out.println("\n=== String 비교 ===");
    System.out.println("str1 == str2: " + (str1 == str2)); // true (같은 객체)
    System.out.println("str1 == str3: " + (str1 == str3)); // false (다른 객체)
    System.out.println("str3 == str4: " + (str3 == str4)); // false (다른 객체)

    System.out.println("str1.equals(str2): " + str1.equals(str2)); // true
    System.out.println("str1.equals(str3): " + str1.equals(str3)); // true
    System.out.println("str3.equals(str4): " + str3.equals(str4)); // true

    System.out.println("\n=== String 연결 (Concatenation) ===");
    String firstName = "홍";
    String lastName = "길동";

    String fullName1 = firstName + " " + lastName;
    System.out.println("fullName1: " + fullName1);

    // += 연산자
    String message = "Hello";
    message += " World";
    System.out.println("message: " + message);

    // 여러 문자열 연결
    String result = "안녕하세요, " + firstName + lastName + "님!";
    System.out.println("result: " + result);

    System.out.println("\n=== String 불변성 (Immutability) ===");
    String original = "Hello";
    System.out.println("원본: " + original);

    String modified = original.toUpperCase(); // 새로운 String 객체 생성
    System.out.println("변경 후: " + modified);
    System.out.println("원본은 여전히: " + original); // 원본은 변경되지 않음

    System.out.println("\n=== null과 빈 문자열 ===");
    String nullStr = null;
    String emptyStr = "";
    String blankStr = "   ";

    // System.out.println(nullStr.length()); // NullPointerException 발생!
    System.out.println("emptyStr.isEmpty(): " + emptyStr.isEmpty());
    System.out.println("blankStr.isEmpty(): " + blankStr.isEmpty());
    System.out.println("blankStr.trim().isEmpty(): " + blankStr.trim().isEmpty());

    System.out.println("\n=== String과 char 배열 변환 ===");
    String text = "Hello";

    // String → char[]
    char[] charArray = text.toCharArray();
    System.out.print("char 배열: ");
    for (char c : charArray) {
      System.out.print(c + " ");
    }
    System.out.println();

    // char[] → String
    char[] newChars = { 'J', 'a', 'v', 'a' };
    String newString = new String(newChars);
    System.out.println("새로운 String: " + newString);
  }
}
