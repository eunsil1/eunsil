package a0112.monster;

public class Monster {
  private String name;
  private int hp; //몬스터 체력
  private static int maxHp = 30; //몬스터 최대 체력

  
  public Monster(String s) {
    name = s;
    hp = maxHp;
  }


  public void attack(Monster enemy) {
    System.out.printf("[%s]의 공격 -> ",name);
    enemy.hp -= 10; //수비자의 hp가 10씩 감소
    System.out.printf("%s의 체력: %d/%d\n",enemy.name,enemy.hp,Monster.maxHp);
    }


  public static void battle(Monster a, Monster b) {
    while (a.hp > 0 && b.hp > 0) {
      // a = 오크, b = 해골
      Monster attacker = (Math.random() < 0.5) ? a:b;
      // 0 ~ 1 사이의 숫자가 랜덤하게 ex) 0.3이면 a가 공격자
      Monster defender = (attacker == a) ? b:a;
      // 공격권자 == a 와 같으면 b가 수비자
      // 공격자와 수비자를 랜덤하게 결정
      attacker.attack(defender);
    }
  }
  
}
