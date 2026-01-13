package a0113.ani1;

// 자식클래스 dog (Animal 상속)
public class Dog extends Animal {


  String breed; //자식클래스만의 추가 필드

  public Dog(String name, int age, String breed) {
    super(name, age); //부모클래스의 name과 age(생성자)를 가져오겠다
    this.breed = breed;
  }

  // 메서드 오버라이딩 : 부모클래스의 메서드를 재정의(덮어쓰기 같은 느낌)
  @Override
  public void makeSound() {
    System.out.println(name + "이(가) 멍멍 짖습니다.");
  }

  @Override
  public void displayInfo() {
    super.displayInfo(); //부모클래스에 있는 이름과 나이를 더해서 출력
    System.out.println("품종: " + breed);
  }

  // 자식클래스만의 메서드
  public void bark(){
    System.out.println(name + "이(가) 왈왈 짖습니다.");
  }

  

  

  

}
