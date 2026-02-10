package a0210.casting1;

public class FullTimeEmployee extends Employee{
  private double bonus;

  public FullTimeEmployee(String name, int id, double baseSalary, double bonus) {
    super(name, id, baseSalary);
    this.bonus = bonus;
  }

  @Override
  double calculateSalary() {
    return baseSalary + bonus;
  }

  void workFullTime(){
    System.out.println(name + "이(가) 정규직으로 일합니다.");
  }

  
  

}
