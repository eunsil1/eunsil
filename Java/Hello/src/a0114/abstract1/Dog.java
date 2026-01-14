package a0114.abstract1;

class Dog extends Animal{

  public Dog(String name) {
    super(name);
  }

  @Override
  void sound() { //부모클래스의 sound() 메서드를 반드시 구현 시켜줘야함 (abstract이기 때문)
    System.out.println(name + "이(가) 멍멍 짖습니다.");
  }
  
  
}
