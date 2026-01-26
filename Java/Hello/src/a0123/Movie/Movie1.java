package a0123.Movie;

import java.util.HashMap;
import java.util.Scanner;

public class Movie1 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    HashMap<String,Double> movies = new HashMap<>();
    movies.put("인터스텔라", 9.2);
    movies.put("어벤져스", 8.5);
    movies.put("기생충", 9.0);
    movies.put("겨울왕국", 8.7);
    movies.put("토이스토리", 9.1);
    movies.put("라라랜드", 8.8);
    movies.put("매트릭스", 8.9);

    HashMap<String,Double> userRatings = new HashMap<>();

    while (true) {

      System.out.print("===영화 평점 입력 ===");
      System.out.print("\n영화를 보고 평점을 입력하세요 (종료: '종료) ");
      String movieName = scanner.nextLine();

      if (movieName.equals("종료")) {
        System.out.println("프로그램을 종료합니다.");
        break;
      }

      if (!movies.containsKey(movieName)) {
        System.out.println("해당 영화가 없습니다. 다시 입력해주세요");
        continue;
      } 

      double rating;
      while(true){
        System.out.print("평점을 입력하세요 (0.0 ~ 10.0): ");
      rating = scanner.nextDouble();
      scanner.nextLine();

      if (rating < 0.0 || rating > 10.0) {
        System.out.println("평점은 0.0부터 10.0 사이여야 합니다.");
      }else{
        break;
      }
    }
      userRatings.put(movieName, rating);
      System.out.println(movieName + "에 " + rating + "점을 주셨습니다.");
    }

    System.out.println("\n=== 입력한 평점 ===");
    double sum = 0.0;
    
    for(String movie : userRatings.keySet()){
      double rating = userRatings.get(movie);
      System.out.println(movie + ": " + rating + "점");
      sum += rating;
    }

    if (!userRatings.isEmpty()) {
      double avg = sum / userRatings.size();
      System.out.printf("\n평균 평점: %.2f점",avg);
    }

    System.out.println("\n=== 추천 영화 (9.0점 이상) ===");
    boolean found = false;
    
    for(String movie : movies.keySet())
    if (movies.get(movie) >= 9.0) {
      System.out.println(movie + ": " + movies.get(userRatings) + "점");
      found = true;
    }
    if(!found){
      System.out.println("추천할 영화가 없습니다.");
    }
    
      
    }
    
  }

