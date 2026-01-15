package a0115.rpg1;

public class RpgTest1 {
  public static void main(String[] args) {
    Wizard wizard = new Wizard();
    wizard.name = "간달프";
    wizard.hp = 100;
    wizard.mp = 80;
    wizard.speed = 3;
    wizard.punch();
    wizard.move();
    wizard.fireball();

    System.out.println();
    
    Knight knight = new Knight();
    knight.name = "아더";
    knight.hp = 150;
    knight.stamina = 70;
    knight.punch();
    knight.slash();
  }
}

class Novice{
  String name;
  int hp;
  int speed;
  void punch(){
    System.out.printf("%s(HP: %d)의 펀치\n", name, hp);
  }
  void move(){
    System.out.printf("%s(%d)의 속도로 이동\n", name, speed);
  }
}

class Wizard extends Novice{
  int mp;
  void fireball(){
    System.out.printf("%s(HP: %d MP: %d)의 파이어볼\n", name, hp, mp);
  }
}

class Knight extends Novice{
  int stamina;
  void slash(){
    System.out.printf("%s(HP: %d ST: %d)의 슬래쉬\n", name, hp, stamina);
  }
}
