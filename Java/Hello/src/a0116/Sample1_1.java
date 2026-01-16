package a0116;

public class Sample1_1 {
  public static void main(String[] args) {
    int[] a = {1,2,3};
    
    // try{
    //   System.out.println(a[3]);
    // }catch(ArrayIndexOutOfBoundsException e){
    //   System.out.println("배열 인덱스 범위를 벗어났습니다.");
    // }
    // System.out.println("프로그램 정상 종료");
    
    if (a.length > 3) {
    System.out.println(a[3]);
    } else {
    System.out.println("인덱스 범위 초과");
    }

    
    }
  }
