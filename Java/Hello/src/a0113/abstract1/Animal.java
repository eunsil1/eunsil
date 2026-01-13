package a0113.abstract1;

// 미완성 설계도
// 객체 생성이 불가
// 상속해서 완성해야 사용가능
// 일반 메서드 + 추상 메서드 둘다 가질 수 있음
abstract class Animal {
  String name;
  public Object eat;

  public Animal(String name) {
    this.name = name;
  }

  // 추상메서드 - 몸체가 없는 메서드
  // 선언만 있고 구현이 없음
  // 자식클래스에서 오버라이딩해야함 - 책의 차례와 비슷함
  abstract void makeSound();
  
  public void eat(){
    System.out.println(name + "이(가) 먹습니다.");
  }
}
