package a0114.abinter;

public class CompareEx {
  public static void main(String[] args) {
    Bird bird = new Bird("짹짹", 1, "참새");
    Fish fish = new Fish("니모", 2, "바다");
    Duck duck = new Duck("도날드", 3, "노란색");

    System.out.println("=== 새 ===");
    bird.eat();
    bird.sleep();
    bird.makeSound();
    bird.move();
    bird.fly();

    System.out.println("\n=== 물고기 ===");
    fish.eat();
    fish.sleep();
    fish.makeSound();
    fish.move();
    fish.swim();

    System.out.println("\n=== 오리 ===");
    duck.eat();
    duck.sleep();
    duck.makeSound();
    duck.move();
    duck.fly();

    // 다형성
    System.out.println("\n=== 다형성 ===");
    Animal[] animals = {bird, fish, duck};
    for(Animal animal : animals){
      animal.makeSound();
      animal.move();
    }

    Flyable[] flyables = { bird, duck };
    for (Flyable flyable : flyables) {
      flyable.fly();
    }

    Swimmable[] swimmables = { fish, duck };
    for (Swimmable swimmable : swimmables) {
      swimmable.swim();
    }
  }

}
