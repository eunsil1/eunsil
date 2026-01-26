package a0126.readwrite;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class MemoPad {
  private static final String OUTPUT_FILE = "c:/Users/tj/memo/out2.txt"; //상수
  //맨처음 파일, 폴더 없음
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    while (true) {
      System.out.println("=== 메모장 프로그램 ===");
      System.out.println("1. 새 메모 작성");
      System.out.println("2. 메모 읽기");
      System.out.println("3. 메모 추가");
      System.out.println("4. 종료");
      System.out.print("선택 > ");

      int choice = sc.nextInt();
      sc.nextLine(); // 버퍼 비우기

      switch (choice) {
        case 1:
          writeNewMemo(sc);
          break;
        case 2:
          readMemo();
          break;
        case 3:
          appendMemo(sc);
          break;
        case 4:
          System.out.println("프로그램을 종료합니다.");
          sc.close();
          return;
        default:
          System.out.println("잘못된 선택입니다.");
      }
      System.out.println();
    }

  }
  private static void appendMemo(Scanner sc) {
    System.out.println("추가 메모 내용입력(종료: 빈줄 입력)");
    StringBuilder content = new StringBuilder();
    String line;
    while (true) {
      line = sc.nextLine();
      if (line.isEmpty()) {
        break;
      }
      content.append(line).append("\n");
    }

    File outputFile = new File(OUTPUT_FILE);
    File parentDir = outputFile.getParentFile(); // c:/Users/tj/memo/
    if (parentDir != null && !parentDir.exists()) { // 폴더가 없으면
      parentDir.mkdirs();// 폴더를 만들어라
    }

    try (BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FILE, true))) {
      bw.write(content.toString()); // 파일이 없으면 생성, 있으면 덮어쓰기
      System.out.println("메모가 " + OUTPUT_FILE + "에 추가 되었습니다.");
    } catch (Exception e) {
      System.out.println("파일 쓰기 오류: " + e.getMessage());
    }
  }

  private static void readMemo() {
    File file = new File(OUTPUT_FILE);
    if (!file.exists()) {
      System.out.println("파일이 없습니다.");
      return;
    }
    try(BufferedReader br = new BufferedReader(new FileReader(OUTPUT_FILE))) {
      System.out.println("=== 메모 내용 ===");
      String line;
      while ((line = br.readLine()) != null) {
        System.out.println(line);
      }
    } catch (Exception e) {
      System.out.println("파일 읽기 오류: " + e.getMessage());
    }
  }

  private static void writeNewMemo(Scanner sc) {
    System.out.println("메모 내용입력(종료: 빈줄 입력)");
    StringBuilder content = new StringBuilder();
    String line;
    while (true) {
      line = sc.nextLine();
      if (line.isEmpty()) {
        break;
      }
      content.append(line).append("\n");
    }

    //출력 디렉토리(폴더) 확인 및 생성
    File outputFile = new File(OUTPUT_FILE);
    File parentDir = outputFile.getParentFile(); // c:/Users/tj/memo/
    if (parentDir != null && !parentDir.exists()) { //폴더가 없으면
      parentDir.mkdirs();//폴더를 만들어라
      System.out.println("디렉토리가 생성되었습니다." + parentDir.getPath());
      //만들어진 폴더를 화면에 보여주세요
    }

    // 파일 존재 여부 확인
    boolean fileExists = outputFile.exists(); // c:/Users/tj/memo/out2.txt 가 있는지 확인여부

    try(BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FILE))) {
      bw.write(content.toString()); //파일이 없으면 생성, 있으면 덮어쓰기
      if (fileExists) {
        System.out.println("기존 파일 덮어쓰고 " + OUTPUT_FILE + "에 저장되었습니다.");
      }else{
        System.out.println("새 파일을 생성하여 " + OUTPUT_FILE + "에 저장되었습니다.");
      }
    } catch (Exception e) {
      System.out.println("파일 쓰기 오류: " + e.getMessage());
    }
  }
}
