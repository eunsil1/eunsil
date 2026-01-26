package a0126.file;

import java.io.FileReader;
import java.io.IOException;

public class FileExample2 {
  public static void main(String[] args) {
    // try catch resource
    try(FileReader fr = new FileReader("c:/Users/tj/out1.txt")){
      int data;
      // 0 ~ 65535 읽는 문자 끝 -1
      while ((data = fr.read()) != -1) {
        System.out.print((char) data);
      }
    }catch(IOException e){
      System.out.println("파일 읽기 오류: " + e.getMessage());
    }


    // 방법 2: 수동 close (비권장)
    FileReader fr = null;
    try {
      fr = new FileReader("data.txt");
      int data;
      while ((data = fr.read()) != -1) {
        System.out.print((char) data);
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (fr != null) {
        try {
          fr.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
