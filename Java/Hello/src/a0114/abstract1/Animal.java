package a0114.abstract1;

abstract class Animal { //public 대신 abstract 사용
  String name;

  public Animal(String name) {
    this.name = name;
  }
  
  abstract void sound(); //반드시 구현 - 추상 메서드

  public void eat(){ //일반 메서드 포함 가능함
    System.out.println(name + "이(가) 먹습니다.");
  }
}
