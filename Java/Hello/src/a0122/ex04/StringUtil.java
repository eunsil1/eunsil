package a0122.ex04;

public class StringUtil {

  public static int countChar(String text, char ch) {
    if (text == null) {
      return 0;
    }
    
    int count = 0;

    for(int i = 0; i < text.length(); i++){
      if (text.charAt(i) == ch) {
        count++;
      }
    }
    return count;
  }

  public static String reverseString(String text) {
    if (text == null) {
      return null;
    }

    String result = "";
    for (int i = text.length() - 1; i >= 0; i--) {
      result += text.charAt(i);
    }
    return result;
  }

  public static String toUpperCase(String text) {
    if (text == null) {
      return null;
    }

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < text.length(); i++) {
      char ch = text.charAt(i);
      if (ch >= 'a' && ch <= 'z') { 
        sb.append((char) (ch - 32)); 
      } else {
        sb.append(ch);
      }
    }
    return sb.toString();
  }

  public static String removeSpaces(String text) {
    if (text == null) {
      return null;
    }
    
    String result = "";

    for (int i = 0; i < text.length(); i++) {
      if (text.charAt(i) != ' ') {
        result += text.charAt(i);
      }
    }

    return result;
  }
  
}
