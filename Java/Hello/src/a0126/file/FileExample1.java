package a0126.file;

import java.io.File;

public class FileExample1 {
  public static void main(String[] args) {
    File file = new File("c:/Users/tj/out1.txt");
    
    // 파일 존재 확인
    if (file.exists()) {
      System.out.println("파일이 존재합니다.");
      System.out.println("파일명: " + file.getName());
      System.out.println("절대 경로: " + file.getAbsolutePath());
      System.out.println("파일 크기: " + file.length() + "bytes");
      System.out.println("읽기 가능: " + file.canRead());
      System.out.println("쓰기 가능: " + file.canWrite());
    }else{
      System.out.println("파일이 존재하지 않습니다.");
    }

    // 파일생성
    try {
      if(file.createNewFile()){
        System.out.println("파일이 생성되었습니다.");
      }
    } catch (Exception e) {
      e.printStackTrace();
      // 자바에서 준비된 예외 메세지 출력
    }

    // 파일삭제
    if(file.delete()){
      System.out.println("파일이 삭제되었습니다.");
    }
  }
}
