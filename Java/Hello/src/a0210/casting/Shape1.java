package a0210.casting;

class Shape {
  void draw() {
    System.out.println("도형 그리기");
  }
}

class Circle extends Shape {
  void draw() {
    System.out.println("원 그리기");
  }
}

public class Shape1 {
  public static void main(String[] args) {
    Shape s = new Circle(); // 빈칸
    s.draw();
  }
}
