package a0130.streamEx;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.Comparator;

public class BookMain {
  public static void main(String[] args) {
    Author jkRowling = new Author("J.K. Rowling", "UK");
    Author georgeOrwell = new Author("George Orwell ", "UK");
    Author harukiMurakami = new Author("Haruki Murakami ", "Japan");
    Author stephenKing = new Author("Stephen King ", "USA");
    Author leoTolstoy = new Author("Leo Tolstoy", "Russia");

    List<Book> books = Arrays.asList(
      new Book(jkRowling, 1997, 15000, "Harry Potter"),
      new Book(georgeOrwell, 1949, 12000, "1984"),
      new Book(harukiMurakami, 2002, 18000, "Kafka on the Shore"),
      new Book(stephenKing, 1977, 20000, "The Shining"),
      new Book(jkRowling, 1998, 15000, "Harry Potter 2"),
      new Book(georgeOrwell, 1945, 11000, "Animal Farm"),
      new Book(harukiMurakami, 2013, 19000, "Colorless Tsukuru"),
      new Book(stephenKing, 1986, 22000, "It"),
      new Book(leoTolstoy, 1869, 25000, "War and Peace")
    );

    practice1(books);
    practice2(books);
    practice3(books);
    practice4(books);
    practice5(books);
    practice6(books);
    practice7(books);
    practice8(books);
  }

  private static void practice8(List<Book> books) {
    Optional <Book> result = books.stream()
    .min(Comparator.comparing(Book::getPrice));
    System.out.println(result);
    System.out.println(result.get().getPrice());
  }

  private static void practice7(List<Book> books) {
    Book result = books.stream()
    .max(Comparator.comparing(Book::getPrice))
    .orElse(null);
    System.out.println(result);
  }

  private static void practice6(List<Book> books) {
    List<String> result = books.stream()
    .map(Book::getAuthor)
    .map(Author::getName)
    .distinct()
    .sorted()
    .collect(Collectors.toList());
    System.out.println(result);
  }

  private static void practice5(List<Book> books) {
    List<Integer> result = books.stream()
      .filter(book -> "USA".equals(book.getAuthor().getCountry()))
      .map(Book::getPrice)
      .collect(Collectors.toList());
      System.out.println(result);
  }

  private static void practice4(List<Book> books) {
    boolean result = books.stream()
    .anyMatch(book -> "Japan".equals(book.getAuthor().getCountry()));
    System.out.println(result);
  }

  private static void practice3(List<Book> books) {
    List<Book> result = books.stream()
        .filter(book -> "UK".equals(book.getAuthor().getCountry())) 
        .sorted(Comparator.comparing(Book::getTitle)) 
        .collect(Collectors.toList()); 
    System.out.println(result);
  }

  private static void practice2(List<Book> books) {
    List<String> result = books.stream()
      .map((book) -> book.getAuthor().getCountry())
      .distinct()
      .collect(Collectors.toList());
      System.out.println(result);
  }

  private static void practice1(List<Book> books) {
    List<Book> result = books.stream()
      .filter(book -> book.getYear() >= 2000)
      .sorted(Comparator.comparing(Book::getPrice))
      .collect(Collectors.toList());
      System.out.println(result);
  }
}
