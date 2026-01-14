package a0114.abinter;
//자식클래스 오리(상속 + 여러 인터페이스 구현)
class Duck extends Animal implements Swimmable, Flyable {

  private String color;
  
  public Duck(String name, int age, String color) {
    super(name, age);
    this.color = color;
  }

  @Override
  public void makeSound() {
    System.out.println(name + "이(가) 꽥꽥 웁니다.");
  }

  @Override
  public void move() {
    System.out.println(name + "이(가) 걸어다닙니다.");
  }

  @Override
  public void swim() {
    System.out.println(name + "이(가) 연못에서 수영합니다.");
  }

  @Override
  public void fly() {
    System.out.println(name + "이(가) 낮게 날아다닙니다.");
  }

  
 
  }
  
  

