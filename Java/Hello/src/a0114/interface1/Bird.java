package a0114.interface1;

class Bird implements Flyable{// flayable은 implements를 붙여서 상속시킴

  @Override
  public void fly() {
    System.out.println("새가 날아갑니다.");
  } 
  
}
