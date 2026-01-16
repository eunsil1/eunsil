package a0115.management;

public class A {
  public static void main(String[] args) {
    // 모든 변수는 초기화가 필요함!
    B bbb = new B();
    B bbc = new B("김정식", 20);
    System.out.println(bbb.name);
    System.out.println(bbb.age);

    // int kkk;
    // kkk = 0;
    // System.out.println(kkk);

    System.out.println(bbc.name);
    System.out.println(bbc.age);
  }
}
