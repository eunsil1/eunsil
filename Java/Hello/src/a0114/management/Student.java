package a0114.management;

public class Student {
  private String studentId;
  private String name;
  private int age;
  private String major;
  
  public Student(String studentId, String name, int age, String major) {
    this.studentId = studentId;
    this.name = name;
    this.age = age;
    this.major = major;
  }

  public String getStudentId() {
    return studentId;
  }

  public void setStudentId(String studentId) {
    this.studentId = studentId;
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

  

  

}
