package a0209.string;

public class StringEx04 {
  public static void main(String[] args) {
    StringBuilder sb = new StringBuilder();

    // 문자열 추가
    sb.append("Hello");
    sb.append(" ");
    sb.append("World");
    System.out.println("초기: " + sb.toString()); //Hello World

    sb.insert(5, ",");
    System.out.println("삽입 후: " + sb.toString());

    sb.delete(5, 6);
    System.out.println("삭제 후: " + sb.toString()); // "Hello World"

    // 치환
    sb.replace(0, 5, "Hi");
    System.out.println("치환 후: " + sb.toString()); // "Hi World"

    // 반전
    StringBuilder sb2 = new StringBuilder("Hello");
    sb2.reverse();
    System.out.println("반전: " + sb2.toString()); // "olleH"

    System.out.println("\n=== 성능 비교 ===");

    // ❌ 비효율적 방법 (매번 새 객체 생성)
    long start1 = System.currentTimeMillis(); //현재시간
    String result1 = "";
    for (int i = 0; i < 10000; i++) {
      result1 += i;
    }

    // result1 = new String(result1 + i);
    long end1 = System.currentTimeMillis(); //작업끝난시간
    System.out.println("+ 연산자 사용 시간: " + (end1 - start1) + "ms");

    // ✅ 효율적 방법 (같은 객체에 추가)
    long start2 = System.currentTimeMillis();
    StringBuilder sb3 = new StringBuilder();
    for (int i = 0; i < 10000; i++) {
      sb3.append(i);
    }
    String result2 = sb3.toString();
    long end2 = System.currentTimeMillis();
    System.out.println("StringBuilder 사용 시간: " + (end2 - start2) + "ms");
  }
}
