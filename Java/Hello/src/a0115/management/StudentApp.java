package a0115.management;

import java.util.Scanner;

public class StudentApp {
    private static Student[] studentsArray = new Student[100];
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean run = true;

        while (run) {
            System.out.println("-----------------------------------------------------------------------");
            System.out.println("1. 학생등록 | 2. 학생목록 | 3. 학생검색 | 4. 학생수정 | 5. 학생삭제 | 6. 종료");
            System.out.println("-----------------------------------------------------------------------");
            System.out.print("선택> ");

            int selNum = 0; //try-catch문에서는 초기화가 먼저 필요함
            try {
                // try 안에서만 값이 대입
                selNum = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                // 잘못된 입력 처리
                // selNum 값이 안들어감
                // 자바는 초기화 됐는지 확인하고 검사 - 에러
            }
            

            switch (selNum) {
                case 1:
                    registerStudent();
                    break;
                case 2:
                    studentList();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    updateStudent();
                    break;
                case 5:
                    deleteStudent();
                    break;
                case 6:
                    System.out.println("프로그램 종료");
                    run = false;
                    break;
                default:
                    break;
            }

        }

    }

    // 삭제
    private static void deleteStudent() {
        System.out.println("-----------");
        System.out.println("학생삭제");
        System.out.println("-----------");
        System.out.print("학번: ");
        String studentId = scanner.nextLine();
        Student student = findStudent(studentId);
        if (student == null) {
            System.out.println("결과: 학생을 찾을 수 없습니다.");
            return;
        }
        // 배열에서 해당 학생을 찾아 null로 지정
        for (int i = 0; i < studentsArray.length; i++){
            if(studentsArray[i] != null && studentsArray[i].equals(student)){
                studentsArray[i] = null;
                System.out.println("결과 : 학생이 삭제되었습니다.");
                break;
            }
        }
    }

    // 수정
    private static void updateStudent() {
        System.out.println("-----------");
        System.out.println("학생수정");
        System.out.println("-----------");

        System.out.print("학번: ");
        String studentId = scanner.nextLine();
        Student student = findStudent(studentId);
        if (student == null) {
            System.out.println("결과: 학생을 찾을 수 없습니다.");
            return;
        }
        System.out.print("이름(수정): ");
        String name = scanner.nextLine();

        System.out.print("나이(수정): ");
        int age = 0;
        try {
            age = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("잘못된 입력입니다. 숫자를 입력하세요");
            return;
        }

        System.out.print("전공(수정): ");
        String major = scanner.nextLine();

        System.out.print("국어(수정): ");
        int kor = 0;
        try {
            kor = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("잘못된 입력입니다. 숫자를 입력하세요.");
            return;
        }

        System.out.print("영어(수정): ");
        int eng = 0;
        try {
            eng = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("잘못된 입력입니다. 숫자를 입력하세요.");
            return;
        }

        System.out.print("수학(수정): ");
        int math = 0;
        try {
            math = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("잘못된 입력입니다. 숫자를 입력하세요.");
            return;
        }

        // Student 정보수정
        student.setName(name);
        student.setAge(age);
        student.setMajor(major);
        student.setKor(kor);
        student.setEng(eng);
        student.setMath(math);
        System.out.println("결과: 학생 정보가 수정되었습니다.");

    }

    // 학생 검색
    private static void searchStudent() {
        System.out.println("-----------");
        System.out.println("학생검색");
        System.out.println("-----------");

        System.out.print("학번: ");
        String studentId = scanner.nextLine();
        Student student = findStudent(studentId);
        if (student == null) {
            System.out.println("학생을 찾을 수 없습니다..");
        }else{
            System.out.println("결과: 학생을 찾았습니다.");
            System.out.printf("학번:%s, 이름:%s, 나이:%d, 전공:%s, 국어:%d, 영어:%d, 수학:%d, 총점:%d, 평균:%.2f\n",
                    student.getStudentId(),
                    student.getName(),
                    student.getAge(),
                    student.getMajor(),
                    student.getKor(),
                    student.getEng(),
                    student.getMath(),
                    student.calculateTotal(),
                    student.calculateAverage());
        }
    }

    // 학생 출력
    private static void studentList() {
        System.out.println("-----------");
        System.out.println("학생목록");
        System.out.println("-----------");
        boolean hasStudent = false;
        for (int i = 0; i < studentsArray.length; i++) {
            if (studentsArray[i] != null) {
                Student student = studentsArray[i];
                // System.out.printf("%s %s %d %s 국어:%d 영어:%d 수학:%d 총점:%d 평균:%.2f\n",
                //         student.getStudentId(),
                //         student.getName(),
                //         student.getAge(),
                //         student.getMajor(),
                //         student.getKor(),
                //         student.getEng(),
                //         student.getMath(),
                //         student.calculateTotal(),
                //         student.calculateAverage());
                System.out.println(student);
            }
        }
    }

    // 학생등록
    private static void registerStudent() {
        System.out.println("-----------");
        System.out.println("학생등록");
        System.out.println("-----------");

        System.out.print("학번: ");
        String studentId = scanner.nextLine();

        if (findStudent(studentId) != null) {
            System.out.println("이미 존재하는 학번입니다.");
            return;
        }
        // 이름 나이 입력(null이면)
        System.out.print("이름: ");
        String name = scanner.nextLine();

        System.out.print("나이: ");
        int age = 0;
        try {
            age = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("잘못된 입력입니다. 숫자를 입력하세요");
            return;
        }
        System.out.print("전공: ");
        String major = scanner.nextLine();

    
        System.out.print("국어: ");
        int kor = 0;
        try {
            kor = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("잘못된 입력입니다. 숫자를 입력하세요");
            return;
        }

        System.out.print("영어: ");
        int eng = 0;
        try {
            eng = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("잘못된 입력입니다. 숫자를 입력하세요");
            return;
        }

        System.out.print("수학: ");
        int math = 0;
        try {
            math = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("잘못된 입력입니다. 숫자를 입력하세요");
            return;
        }

        Student newStudent = new Student(studentId, name, age, major, kor, eng,
        math);

        // // setter 메서드를 이용해서 데이터 초기화(넣기)
        // Student newStudent = new Student();
        // newStudent.setStudentId(studentId);
        // newStudent.setName(name);
        // newStudent.setAge(age);
        // newStudent.setMajor(major);
        // newStudent.setKor(kor);
        // newStudent.setEng(eng);
        // newStudent.setMath(math);

        for (int i = 0; i < studentsArray.length; i++) {
            if (studentsArray[i] == null) {
                studentsArray[i] = newStudent;
                System.out.println("결과: 학생이 등록되었습니다.");
                break;
            }
        }
    }

    private static Student findStudent(String studentId) {
        Student student = null;
        for (int i = 0; i < studentsArray.length; i++) {
            if (studentsArray[i] != null) { // ⭐ null 체크
                String dbStudentId = studentsArray[i].getStudentId(); // db에 있는 id를 차레대로 저장 비교
                if (dbStudentId.equals(studentId)) { // 키보드로 입력한(studentId) db에 있는 id(dbStudentId)
                    student = studentsArray[i];
                    break;
                }
            }
        }
        return student;
    }
}
