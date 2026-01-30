package a0130.streamEx;

public class Author {
  private String name;
  private String country;
  
  public Author(String name, String country) {
    this.name = name;
    this.country = country;
  }

  public String getName() {
    return name;
  }

  public String getCountry() {
    return country;
  }

  @Override
  public String toString() {
    return "Author [name=" + name + ", country=" + country + "]";
  }

  
}
