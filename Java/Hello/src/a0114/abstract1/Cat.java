package a0114.abstract1;

class Cat extends Animal{

  public Cat(String name) {
    super(name);
  }

  @Override
  void sound() {
    System.out.println(name + "이(가) 야옹 웁니다.");
    
  }
  
  
}
