package a0210.casting1;

public class Main1 {
  public static void main(String[] args) {
    System.out.println("=== 직원 관리 시스템 ===");
    // 다양한 타입의 직원들을 부모타입 배열에 저장(업캐스팅)
    Employee[] employees = new Employee[5];
    employees[0] = new FullTimeEmployee("홍길동", 1, 3000000, 500000);
    employees[1] = new PartTimeEmployee("김영희", 2, 0, 20, 15000);
    employees[2] = new Manager("박철수", 3, 5000000, 100000, 5);
    employees[3] = new FullTimeEmployee("이미영", 4, 2500000, 300000);
    employees[4] = new PartTimeEmployee("최민수", 5, 0, 15, 12000);

    System.out.println("==== 모든 직원 정보 출력 ====");
    for(Employee emp : employees){
      emp.printInfo(); //다형성
    }

    System.out.println("==== 전체 급여 합계 ====");
    double totalSalary = 0;
    for (Employee emp : employees) {
      totalSalary += emp.calculateSalary();
    }
    System.out.println("전체 급여 합계: " + String.format("%,.0f", totalSalary));

    System.out.println("\n=== 직원 타입별 처리 ===");
    for (Employee emp : employees) {
      if (emp instanceof FullTimeEmployee) {
        FullTimeEmployee ft = (FullTimeEmployee) emp;
        ft.workFullTime();
      }else if(emp instanceof PartTimeEmployee){
        PartTimeEmployee pt = (PartTimeEmployee) emp;
        pt.workParkTime();
      }else if(emp instanceof Manager){
        Manager mgr = (Manager) emp;
        mgr.manageTeam();
      }
    }

    System.out.println("\n=== 특정 직원 검색 ===");
    String searchName = "홍길동";
    Employee found = findEmployee(employees, searchName);
    if (found != null) {
      System.out.println("검색결과: ");
      found.printInfo();
    }else{
      System.out.println(searchName + "을(를) 찾을 수 없습니다.");
    }




  }

  private static Employee findEmployee(Employee[] employees, String searchName) {
    for(Employee emp : employees){
      if (emp.getName().equals(searchName)) {
        return emp;
      }
    }
    return null;
  }
}
