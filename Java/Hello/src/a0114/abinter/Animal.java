package a0114.abinter;

// 부모클래스
abstract class Animal {

  protected String name;
  protected int age;

  public Animal(String name, int age) {
    this.name = name;
    this.age = age;
  }
  
  // 일반메서드
  public void eat(){
    System.out.println(name + "이(가) 먹습니다.");
  }

  public void sleep(){
    System.out.println(name + "이(가) 잡니다.");
  }

  // 추상메서드
  public abstract void makeSound();
  public abstract void move();
  
} 
