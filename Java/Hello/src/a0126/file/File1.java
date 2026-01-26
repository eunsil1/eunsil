package a0126.file;

import java.io.FileOutputStream;
import java.io.IOException;

public class File1 {
  public static void main(String[] args) throws IOException{ // 예외처리 필수
    FileOutputStream output = new FileOutputStream("c:/Users/tj/out.txt");
    output.close();


  }
}
