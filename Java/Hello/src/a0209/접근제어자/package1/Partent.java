package a0209.접근제어자.package1;

public class Partent {
  protected String message = "나는 protected 변수 입니다.";
  protected void showMessage(){
    System.out.println("Protected 메서드 실행: " + message);
  }
}
