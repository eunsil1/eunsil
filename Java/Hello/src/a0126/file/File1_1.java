package a0126.file;

import java.io.FileOutputStream;
import java.io.IOException;

public class File1_1 {
  public static void main(String[] args) { 
    try {
      FileOutputStream output = new FileOutputStream("c:/Users/tj/out.txt");
      output.close();
    } catch (IOException e) {
      System.out.println("파일 처리중 오류 발생");
    }

  }
}
// IOException -> checked Exception
// 파일 경로가 잘못되거나, 권한이 없을때, 디스크 용량부족, 파일이 이미 열려있는 경우 -> 입출력 에러