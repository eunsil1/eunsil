package a0126.fileread1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileRead1 {
  public static void main(String[] args) {
    String filename = "c:/Users/tj/out.txt";
    ArrayList<String> result = readLine(filename);
    System.out.println("===파일 내용 출력===");
    for(String line : result){
      System.out.println(line);
    }
    System.out.println("====================");
    System.out.println("총 줄 수 : " + result.size());
    
  }

  private static ArrayList<String> readLine(String filename) {
    ArrayList<String> lines = new ArrayList<>();
    try(BufferedReader br = new BufferedReader(new FileReader(filename))) {
      String line;
      while ((line = br.readLine()) != null) {
        lines.add(line);
      }
    } catch (IOException e) {
      System.out.println("파일 읽기 오류 : " + e.getMessage());
    }
    return lines;
  }
}
