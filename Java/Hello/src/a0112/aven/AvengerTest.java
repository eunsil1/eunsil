package a0112.aven;

public class AvengerTest {
  public static void main(String[] args) {
    Avenger thor = new Avenger("토르", 150);
    Avenger thanos = new Avenger("타노스", 100);

    thor.punch(thanos);
    thanos.punch(thor);
  }
}
