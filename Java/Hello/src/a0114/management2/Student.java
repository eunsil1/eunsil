package a0114.management2;

public class Student {
  private String studentId;
  private String name;
  private int age;
  private String major;
  private int kor;
  private int eng;
  private int math;


  private int sum;
  private double avg;
 


  public Student(String studenId, String name, int age, String major, int kor, int eng, int math, int sum, double avg) {
    this.studentId = studenId;
    this.name = name;
    this.age = age;
    this.major = major;
    this.kor = kor;
    this.eng = eng;
    this.math = math;
    this.sum = sum;
    this.avg = avg;
    
    calculateTotal();
    calculateAverage();

  }

  private void calculateAverage() {
    this.avg = sum / 3.0;
  }

  private void calculateTotal() {
    this.sum = kor + eng + math;
  }

  public String getStudentId() {
    return studentId;
  }

  public void setStudenId(String studenId) {
    this.studentId = studenId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getMajor() {
    return major;
  }

  public void setMajor(String major) {
    this.major = major;
  }

  public int getKor() {
    return kor;
  }

  public void setKor(int kor) {
    this.kor = kor;
  }

  public int getEng() {
    return eng;
  }

  public void setEng(int eng) {
    this.eng = eng;
  }

  public int getMath() {
    return math;
  }

  public void setMath(int math) {
    this.math = math;
  }

  public void getSum(int sum) {
    this.sum = sum;
  }

  public void getAvg(double avg){
    this.avg = avg;
  }

  

  
}
