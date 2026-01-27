package a0127.student;

public class Student {
  private String name;
  private int score;

  public Student(String name, int score) {
    this.name = name;
    this.score = score;
  }

  @Override
  public String toString() {
    return "Student [name=" + name + ", score=" + score + "]";
  }

  //파일 저장용 문자열
  public String toFileString() {
    return name + "|" + score;
  }

  public static Student fromFileString(String line) {
    try {
      String[] parts = line.split("\\|"); //parts[0] = "sam"; parts[1] = 90;
      if (parts.length == 2) {
        String name = parts[0].trim(); //공백제거 후 sam
        int score = Integer.parseInt(parts[1].trim()); 
        //"90" 문자를 공백제거 후 90 숫자 변환 후 score 대입
        return new Student(name, score);
      }
    } catch (NumberFormatException e) {
      return null;
    }
    return null;
  }

  public String getName() {
    return name;
  }

  public int getScore() {
    return score;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setScore(int score) {
    this.score = score;
  }

 

  
  
}
