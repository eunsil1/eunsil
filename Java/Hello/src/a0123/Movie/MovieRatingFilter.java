package a0123.Movie;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MovieRatingFilter {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
        
        // 영화 목록 초기화
        HashMap<String, Double> movies = new HashMap<>();
        
        movies.put("인터스텔라", 9.2);
        movies.put("어벤져스", 8.5);
        movies.put("기생충", 9.0);
        movies.put("겨울왕국", 8.7);
        movies.put("토이스토리", 9.1);
        movies.put("라라랜드", 8.8);
        movies.put("매트릭스", 8.9);
        
        System.out.print("최소 평점을 입력하세요: ");
        double minRating = scanner.nextDouble();

        System.out.println("\n=== 평점 " + minRating + " 이상인 영화 목록 ===" );
        boolean found = false;

        for(Map.Entry<String, Double> entry : movies.entrySet()){
          if (entry.getValue() >= minRating) {
            System.out.println(entry.getKey() + " : " + entry.getValue() + "점");
            found = true;
          }
        }

        if (!found) {
          System.out.println("해당 조건을 만족하는 영화가 없습니다.");
        }
        scanner.close();
        
  }
}
