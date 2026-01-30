package a0128.ramda;
  
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

class Student {
  private String name;
  private int score;

  public Student(String name, int score) {
    this.name = name;
    this.score = score;
  }

  public String getName() {
    return name;
  }

  public int getScore() {
    return score;
  }

  @Override
  public String toString() {
    return name + ": " + score + "점";
  }
}

public class LambdaExample {
  public static void main(String[] args) {
    List<Student> students = Arrays.asList(
        new Student("김철수", 85),
        new Student("이영희", 92),
        new Student("박민수", 78),
        new Student("최지영", 95),
        new Student("정수진", 88));

    // 1. 점수로 정렬
    students.sort((s1, s2) -> s2.getScore() - s1.getScore());
    System.out.println("점수 내림차순:");
    students.forEach(s -> System.out.println(s));

    // 2. 90점 이상 학생만 필터링
    List<Student> topStudents = students.stream()
        .filter(s -> s.getScore() >= 90)
        .collect(Collectors.toList());
    System.out.println("\n90점 이상 학생:");
    topStudents.forEach(System.out::println);

    // 3. 학생 이름만 추출
    List<String> names = students.stream()
        .map(Student::getName)
        .collect(Collectors.toList());
    System.out.println("\n학생 이름 목록: " + names);

    // 스트림 - 데이터의 흐름
    // 리스트 - 스트림 생성 -> 가공(필터/변환) -> 결과를 출력
    // 데이터를 저장하지 않음
    // 원본컬렉션(students)을 변경하지 않음
    // 한번 사용하면 다시 사용 불가

    // 컬렉션.stream()
    //     .중간연산()
    //     .중간연산()
    //     .최종연산();

    // names.stream()
    //     .filter(n -> n.length() >= 5)
    //     .forEach(System.out::println);

    
    // 4. 평균 점수 계산
    double average = students.stream()
        .mapToInt(Student::getScore)
        .average()
        .orElse(0.0);
    System.out.println("\n평균 점수: " + average);
  }
}

