package a0209.접근제어자.package2;

import a0209.접근제어자.package1.Partent;

public class Child extends Partent {
  public void display(){
    System.out.println("자식 클래스에 접근: " + message);
    showMessage();
  }
}
