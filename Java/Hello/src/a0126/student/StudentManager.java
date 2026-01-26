package a0126.student;

import java.io.*;
import java.util.Scanner;

public class StudentManager {

    static final String FILE_PATH = "c:/Users/TJ/student/data.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("=== 학생 관리 프로그램 ===");
            System.out.println("1. 학생 정보 등록 (새로 저장)");
            System.out.println("2. 학생 목록 읽기");
            System.out.println("3. 학생 정보 추가");
            System.out.println("4. 종료");
            System.out.print("선택 > ");

            int menu = Integer.parseInt(sc.nextLine());

            switch (menu) {
                case 1:
                    saveNew(sc);
                    break;
                case 2:
                    readFile();
                    break;
                case 3:
                    append(sc);
                    break;
                case 4:
                    System.out.println("프로그램 종료");
                    return;
                default:
                    System.out.println("잘못된 선택입니다.");
            }
        }
    }

    // 1️⃣ 새로 저장 (덮어쓰기)
    static void saveNew(Scanner sc) {
        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(FILE_PATH))) {

            System.out.println("학생 정보 입력 (빈 줄 입력 시 종료)");
            while (true) {
                String input = sc.nextLine();
                if (input.isEmpty()) break;

                bw.write(input);
                bw.newLine();
            }
            System.out.println("저장 완료");

        } catch (IOException e) {
            System.out.println("파일 저장 오류");
        }
    }

    // 2️⃣ 목록 읽기
    static void readFile() {
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            System.out.println("저장된 학생 정보가 없습니다.");
            return;
        }

        try (BufferedReader br = new BufferedReader(
                new FileReader(file))) {

            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            System.out.println("파일 읽기 오류");
        }
    }

    // 3️⃣ 학생 정보 추가
    static void append(Scanner sc) {
        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(FILE_PATH, true))) {

            System.out.println("추가할 학생 정보 입력 (빈 줄 입력 시 종료)");
            while (true) {
                String input = sc.nextLine();
                if (input.isEmpty()) break;

                bw.write(input);
                bw.newLine();
            }
            System.out.println("추가 완료");

        } catch (IOException e) {
            System.out.println("파일 추가 오류");
        }
    }
}

