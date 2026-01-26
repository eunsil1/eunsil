package a0126.file;

import java.io.FileOutputStream;
import java.io.IOException;

public class File2 {
  public static void main(String[] args) throws IOException{ 
    FileOutputStream output = new FileOutputStream("c:/Users/tj/out.txt");
    for (int i = 1; i < 11; i++) {
      String data = i + "번째 줄입니다. \r\n";
      // window 줄바꿈 단어 \r\n 리눅스에선 \n
      output.write(data.getBytes());
    }

    output.close();


  }
}
