package a0126.filewrite1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileWrite1 {
  public static void main(String[] args) {
    String filename = "c:/Users/tj/out1.txt";
    //저장할 데이터 생성

    ArrayList<String> list = new ArrayList<>();
    list.add("1번째 학생입니다.");
    list.add("2번째 학생입니다.");
    list.add("3번째 학생입니다.");
    list.add("자바파일 출력 연습");

    //파일로 저장
    writeLines(filename, list);
    System.out.println("파일 저장 완료");
  }

  private static void writeLines(String filename, ArrayList<String> lines) {
    try(BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
      for(String line : lines){
        bw.write(line);
        bw.newLine(); //줄바꿈(\r\n)
      }
    } catch (IOException e) {
      System.out.println("파일 쓰기 오류 : " + e.getMessage());
    }
  }
}
