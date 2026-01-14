package a0114.abinter;

class Fish extends Animal implements Swimmable {
  private String habitat;

  public Fish(String name, int age, String habitat) {
    super(name, age);
    this.habitat = habitat;
  }

  @Override
  public void makeSound() {
    System.out.println(name + "은(는) 소리를 내지 않습니다.");
  }

  @Override
  public void move() {
    System.out.println(name + "이(가) 헤엄쳐 다닙니다.");
  }

  @Override
  public void swim() {
    System.out.println(name + "이(가) " + habitat + "에서 수영합니다.");
  }
}