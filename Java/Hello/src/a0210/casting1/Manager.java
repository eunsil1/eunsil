package a0210.casting1;

public class Manager extends Employee{
  private double teamBonus;
  private int teamSize;

  public Manager(String name, int id, double baseSalary, double teamBonus, int teamSize) {
    super(name, id, baseSalary);
    this.teamBonus = teamBonus;
    this.teamSize = teamSize;
  }

  @Override
  double calculateSalary() {
    return baseSalary + teamBonus + teamSize;
  }

  void manageTeam() {
    System.out.println(name + "이(가) " + teamSize + "명의 팀을 관리합니다.");
  }

}
