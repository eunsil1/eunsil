package a0126.file;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class File3_1 {
  public static void main(String[] args) throws IOException {
    PrintWriter pw = new PrintWriter("c:/Users/tj/out.txt","UTF-8");
    for(int i = 1; i < 11; i++){
      String data = i + " 번째 줄입니다.";
      pw.println(data);
      
    }
    pw.close();

    PrintWriter pw2 = new PrintWriter(new FileWriter("c:/Users/tj/out.txt", true));
    for(int i = 11; i < 21; i++){
      pw2.println(i + " 번째 줄입니다.");
    }
    pw2.close();

  }
    
}
