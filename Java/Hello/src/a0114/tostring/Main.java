package a0114.tostring;

public class Main extends Object{
  public static void main(String[] args) {
    
    Person p1 = new Person("홍길동", 20);
    Person p2 = new Person("이순신", 45);
    // System.out.println(p1.name); 접근제어자가 private가 아니면 가능

    System.out.println(p1.getName());
    System.out.println(p1.getAge());
    System.out.println(p1);
    System.out.println(p2);
  }
}
