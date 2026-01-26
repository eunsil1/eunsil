package a0126.file;

import java.io.FileInputStream;
import java.io.IOException;

public class File5 {
  public static void main(String[] args) throws IOException{
    byte[] b = new byte[1024];
    FileInputStream input = new FileInputStream("c:/Users/tj/out.txt");
    input.read(b);
    System.out.println(new String(b));
    // byte 배열을 문자열로 변경
    // 파일 불러오기
    input.close();
  }
}
