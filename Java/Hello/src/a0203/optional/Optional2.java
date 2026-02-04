package a0203.optional;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Optional2 {
  public static void main(String[] args) {
    UserRepository repo = new UserRepository();

    System.out.println("=== 사용자 조회 시스템 예제 ===\n");

    // 기존 방식 (null 체크 필요)
    System.out.println("1. 기존 방식 (null 체크):");
    User user1 = repo.findById(1);
    if (user1 != null) {
      System.out.println("  찾은 사용자: " + user1.getName());
    } else {
      System.out.println("  사용자를 찾을 수 없습니다");
    }

    // 존재하지 않는 사용자
    User user2 = repo.findById(999);
    if (user2 != null) {
      System.out.println("  찾은 사용자: " + user2.getName());
    } else {
      System.out.println("  사용자를 찾을 수 없습니다 (ID: 999)");
    }

    // Optional 방식 깔끔한 처리
    repo.findByIdOptional(1)
        .ifPresent(user -> System.out.println("찾는 사용자: " + user.getName()));

    // 값이 없을때
    String name = repo.findByIdOptional(999)
        .map(User::getName)
        .orElse("사용자를 찾을 수 없습니다.");
    System.out.println("ID 999 조회 결과: " + name);

    // 사용자 정보 전체 출력
    System.out.println("\n3. 사용자 정보 조회:");
    repo.findByIdOptional(2)
        .ifPresentOrElse(
            user -> {
              System.out.println("  이름: " + user.getName());
              System.out.println("  나이: " + user.getAge());
            },
            () -> System.out.println("  사용자를 찾을 수 없습니다"));

    // 여러 사용자 조회
    System.out.println("\n4. 여러 사용자 조회:");
    List<Integer> ids = Arrays.asList(1, 2, 999, 3);
    ids.forEach(id -> {
      repo.findByIdOptional(id)
          .map(User::getName)
          .ifPresentOrElse(
              name2 -> System.out.println("  ID " + id + ": " + name2),
              () -> System.out.println("  ID " + id + ": 없음"));
    });
  }
}

class User {
  private String name;
  private int age;

  public User(String name, int age) {
    this.name = name;
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  @Override
  public String toString() {
    return "User{name='" + name + "', age=" + age + "}";
  }
}

class UserRepository {
  private Map<Integer, User> users = new HashMap<>();

  public UserRepository() {
    users.put(1, new User("김철수", 25));
    users.put(2, new User("이영희", 30));
    users.put(3, new User("박민수", 28));
  }

  // ❌ 기존 방식: null 반환 가능
  public User findById(int id) {
    return users.get(id); // null 가능
  }

  // ✅ Optional 방식: 명시적으로 null 가능성 표현
  public Optional<User> findByIdOptional(int id) {
    User user = users.get(id);
    return Optional.ofNullable(user);
  }

  
}

      
       
