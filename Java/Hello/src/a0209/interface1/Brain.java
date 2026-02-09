package a0209.interface1;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Brain implements Interface{

  static HashMap<String,String> word = new HashMap<>();

  public Brain(){
    word.put("Peach", "복숭아");
    word.put("Plum", "자두");
    word.put("Pomegranate", "석류");
    word.put("Persimmon", "감");
  }


  @Override
  public void AddWord() {
    String Eng = null;
    String Kor = null;
    while (true) {
      Scanner sc = new Scanner(System.in);
      System.out.print("등록할 영단어를 입력하세요(목록으로 돌아가려면 Menu 입력: )");
      Eng = sc.nextLine();
      if (Eng.equalsIgnoreCase("Menu")) {
        break;
      }else{
        if(word.containsKey(Eng)){
          System.out.println("이미 등록된 단어입니다.");
        }else{
          System.out.print("뜻을 입력해주세요");
          Kor = sc.nextLine();
          try {
            word.put(Eng, Kor);
          } catch (Exception e) {
            System.out.println("입력이 잘못되었습니다.");
            e.printStackTrace();
          }finally{
            System.out.println("입력이 되었습니다.");
          }
        }
      }

    }
    
  }

  @Override
  public void DeleteWord() {
    Scanner sc = new Scanner(System.in);
    while (true) {
      System.out.println("========================단어목록========================");
      if (word.isEmpty()) {
        System.out.println("저장된 단어가 없습니다.");
      }else{
        for(Map.Entry<String,String> entry : word.entrySet()){
          System.out.println("- " + entry.getKey() + " : " + entry.getValue());
          //- peach : 복숭아
        }
      }
      System.out.println("======================================================");
      System.out.print("삭제할 단어를 입력하세요.(목록으로 돌아가려면 Menu 입력: )");
      String del = sc.nextLine().trim();
      //입력값 앞뒤 공백 제거 해서 입력 받음
      if (del.equalsIgnoreCase("menu")) {
        System.out.println("메뉴로 돌아갑니다.");
        break;
      }
      if (word.containsKey(del)) {
        System.out.println("입력한 단어" + del + " : " + word.remove(del) + "가 삭제되었습니다.");
      }else{
        System.out.println("존재하지 않는 단어입니다.");
      }


    }
    
  }

  @Override
  public void Exit() {
    System.out.println("영단어를 종료합니다.");

    
  }

  @Override
  public void Menu() {
    System.out.println("등록된 단어 수: \n" + word.size());    
    System.out.println("---- 선택 해주세요. ----");
    System.out.println(" 1. 단어 등록 ");
    System.out.println(" 2. 단어 조회 ");
    System.out.println(" 3. 단어 삭제 ");
    System.out.println(" 4. 테스트 ");
    System.out.println(" 5. 단어 목록보기");
    System.out.println(" 6. 종료 ");
    System.out.println("-----------------------");
  }

  @Override
  public void Test() {
    Scanner sc = new Scanner(System.in);
    while (true) {
      System.out.println("1. 시작하기");
      System.out.println("2. 종료하기");
      System.out.print("번호를 입력하세요: ");
      if (!sc.hasNextInt()) { //숫자가 아니면?
        System.out.println("숫자를 입력하세요");
        sc.next(); //잘못된 문자열을 버퍼 지우기        
        continue; //다시 반복문 처음으로 이동
      }
      int a = sc.nextInt(); //여기서 사용자가 입력한 값이 읽혀서 a에 저장됨
      sc.nextLine();
      if (a == 1) {
        int correctCount = 0;
        int totalCount = 0;
        System.out.println("====뜻에 맞는 영단어를 입력하세요====");
        for(Map.Entry<String,String> entry : word.entrySet()){
          System.out.println(entry.getValue()); //뜻
          System.out.print("답 입력: ");
          String answer = sc.nextLine().trim();
          if (answer.equalsIgnoreCase(entry.getKey())) {
            System.out.println("정답입니다.");
            correctCount++;
          }else{
            System.out.println("오답 (정답은 : " + entry.getKey() + ") 입니다.");
          }
          totalCount++;
        }
        System.out.println("결과: " + correctCount + "/" + totalCount + "\n");
        System.out.println("=================================");
      }else if(a == 2){
        System.out.println("종료합니다.");
        break;
      }else{
        System.out.println("1 또는 2를 입력하세요.");
      }
    }
    
  }

  @Override
  public void WordList() {
    System.out.println("========================단어목록========================");
    if (word.isEmpty()) {
      System.out.println("저장된 단어가 없습니다.");
    } else {
      for (Map.Entry<String, String> entry : word.entrySet()) {
        System.out.println("- " + entry.getKey() + " : " + entry.getValue());
        // - peach : 복숭아
      }
    }
    System.out.println("======================================================");
    
  }

  @Override
  public void WordSearch() {
    while (true) {
      Scanner sc = new Scanner(System.in);
      System.out.print("조회할 영단어를 입력하세요(목록으로 돌아가려면 Menu 입력: )");
      String Eng = sc.nextLine();
      if (Eng.equalsIgnoreCase("Menu")) {
        break;
      }else{
        if (word.containsKey(Eng) == true) {
          System.out.println(word.get(Eng));
          //key 해당되는 value 출력
        }else{
          System.out.println("등록되지 않은 단어입니다.");
        }
      }

    }
    
  }
  
}
