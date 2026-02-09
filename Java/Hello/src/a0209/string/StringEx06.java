package a0209.string;

public class StringEx06 {
  public static void main(String[] args) {
    System.out.println("=== 길이와 빈 문자열 확인 ===");
    String str = "Hello World";
    System.out.println("문자열: \"" + str + "\"");
    System.out.println("길이: " + str.length());
    System.out.println("빈 문자열인가? " + str.isEmpty());

    String empty = "";
    System.out.println("\"" + empty + "\"는 빈 문자열인가? " + empty.isEmpty());

    System.out.println("\n=== 문자열 검색 ===");
    String text = "Hello World";

    // charAt(): 특정 위치의 문자
    System.out.println("charAt(0): " + text.charAt(0)); // 'H'
    System.out.println("charAt(6): " + text.charAt(6)); // 'W'

    // indexOf(): 문자열이나 문자의 첫 번째 위치
    System.out.println("indexOf('o'): " + text.indexOf('o')); // 4
    System.out.println("indexOf(\"World\"): " + text.indexOf("World")); // 6
    System.out.println("indexOf('x'): " + text.indexOf('x')); // -1 (없음)

    // lastIndexOf(): 마지막 위치
    System.out.println("lastIndexOf('o'): " + text.lastIndexOf('o')); // 7

    // contains(): 포함 여부
    System.out.println("contains(\"Hello\"): " + text.contains("Hello")); // true
    System.out.println("contains(\"Java\"): " + text.contains("Java")); // false

    // startsWith() / endsWith()
    System.out.println("startsWith(\"Hello\"): " + text.startsWith("Hello")); // true
    System.out.println("endsWith(\"World\"): " + text.endsWith("World")); // true

    System.out.println("\n=== 문자열 추출 ===");
    String str2 = "Hello World";

    // substring(): 부분 문자열 추출
    String sub1 = str2.substring(0, 5); // "Hello" (0부터 5 전까지)
    String sub2 = str2.substring(6); // "World" (6부터 끝까지)
    System.out.println("substring(0, 5): " + sub1);
    System.out.println("substring(6): " + sub2);

    // split(): 문자열 분리
    String date = "2024-01-15";
    String[] dateParts = date.split("-");
    System.out.println("날짜 분리: " + dateParts[0] + "년 " + dateParts[1] + "월 " + dateParts[2] + "일");

    String fruits = "사과,바나나,오렌지";
    String[] fruitArray = fruits.split(",");
    System.out.print("과일 목록: ");
    for (String fruit : fruitArray) {
      System.out.print(fruit + " ");
    }
    System.out.println();

    // trim(): 앞뒤 공백 제거
    String spaced = "  Hello World  ";
    System.out.println("원본: [" + spaced + "]");
    System.out.println("trim: [" + spaced.trim() + "]");

    System.out.println("\n=== 문자열 변환 ===");
    String str3 = "Hello World";

    // toUpperCase() / toLowerCase()
    System.out.println("toUpperCase(): " + str3.toUpperCase()); // "HELLO WORLD"
    System.out.println("toLowerCase(): " + str3.toLowerCase()); // "hello world"

    // replace(): 문자열 치환
    String replaced = str3.replace("World", "Java");
    System.out.println("replace(\"World\", \"Java\"): " + replaced); // "Hello Java"

    String replaced2 = str3.replace('o', 'O');
    System.out.println("replace('o', 'O'): " + replaced2); // "HellO WOrld"

    System.out.println("\n=== 문자열 비교 ===");
    String str4 = "apple";
    String str5 = "banana";
    String str6 = "Apple";

    // compareTo(): 사전식 비교
    System.out.println("compareTo(\"banana\"): " + str4.compareTo(str5)); // 음수
    System.out.println("compareTo(\"apple\"): " + str4.compareTo("apple")); // 0

    // compareToIgnoreCase(): 대소문자 무시 비교
    System.out.println("compareToIgnoreCase(\"Apple\"): " + str4.compareToIgnoreCase(str6)); // 0

    // equalsIgnoreCase(): 대소문자 무시 비교
    System.out.println("equalsIgnoreCase(\"Apple\"): " + str4.equalsIgnoreCase(str6)); // true

    System.out.println("\n=== 다른 타입으로 변환 ===");
    // valueOf(): 다른 타입을 String으로
    int num = 123;
    String numStr = String.valueOf(num);
    System.out.println("int 123 → String: " + numStr);

    double d = 3.14;
    String dStr = String.valueOf(d);
    System.out.println("double 3.14 → String: " + dStr);

    boolean b = true;
    String bStr = String.valueOf(b);
    System.out.println("boolean true → String: " + bStr);
  }
}
