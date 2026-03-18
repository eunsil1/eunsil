// 1. let과 const 테스트
function testLet() {
    let name = "홍길동";
    name = "김철수";
    document.getElementById("let-result").innerHTML = 
        `결과: ${name} (재할당 가능)`;
    console.log("let 테스트:", name);
}

function testConst() {
    const PI = 3.14159;
    // PI = 3.14;  // 에러 발생!
    document.getElementById("const-result").innerHTML = 
        `결과: ${PI} (재할당 불가)`;
    console.log("const 테스트:", PI);
}

// 2. 화살표 함수 테스트
function testArrow() {
    // 기존 함수
    function add1(a, b) {
        return a + b;
    }
    
    // 화살표 함수
    const add2 = (a, b) => {
        return a + b;
    };
    
    // 한 줄 화살표 함수
    const add3 = (a, b) => a + b;
    
    const result1 = add1(5, 3);
    const result2 = add2(5, 3);
    const result3 = add3(5, 3);
    
    document.getElementById("arrow-result").innerHTML = 
        `기존 함수: ${result1}<br>` +
        `화살표 함수: ${result2}<br>` +
        `간단한 화살표 함수: ${result3}`;
    console.log("화살표 함수 테스트:", result1, result2, result3);
}

// 3. 템플릿 리터럴 테스트
function testTemplate() {
    const name = "홍길동";
    const age = 25;
    
    // 기존 방식
    const message1 = "이름: " + name + ", 나이: " + age;
    
    // 템플릿 리터럴
    const message2 = `이름: ${name}, 나이: ${age}`;
    
    document.getElementById("template-result").innerHTML = 
        `기존 방식: ${message1}<br>` +
        `템플릿 리터럴: ${message2}`;
    console.log("템플릿 리터럴 테스트:", message1, message2);
}

// 4. 기본 매개변수 테스트
function testDefault() {
    function greet(name = "손님") {
        return `안녕하세요, ${name}님!`;
    }
    
    const result1 = greet();
    const result2 = greet("홍길동");
    
    document.getElementById("default-result").innerHTML = 
        `매개변수 없음: ${result1}<br>` +
        `매개변수 있음: ${result2}`;
    console.log("기본 매개변수 테스트:", result1, result2);
}

// 5. 배열 구조 분해 테스트
function testDestructureArray() {
    const colors = ["빨강", "파랑", "노랑"];
    const [red, blue, yellow] = colors;
    
    document.getElementById("destructure-array-result").innerHTML = 
        `빨강: ${red}<br>` +
        `파랑: ${blue}<br>` +
        `노랑: ${yellow}`;
    console.log("배열 구조 분해 테스트:", red, blue, yellow);
}

// 6. 객체 구조 분해 테스트
function testDestructureObject() {
    const person = { name: "홍길동", age: 25, city: "서울" };
    const { name, age } = person;
    
    document.getElementById("destructure-object-result").innerHTML = 
        `이름: ${name}<br>` +
        `나이: ${age}`;
    console.log("객체 구조 분해 테스트:", name, age);
}

// 7. 스프레드 연산자 테스트
function testSpread() {
    const arr1 = [1, 2, 3];
    const arr2 = [4, 5, 6];
    
    // 배열 복사
    const arr3 = [...arr1];
    
    // 배열 합치기
    const arr4 = [...arr1, ...arr2];
    
    document.getElementById("spread-result").innerHTML = 
        `원본 배열1: [${arr1.join(", ")}]<br>` +
        `복사 배열: [${arr3.join(", ")}]<br>` +
        `합친 배열: [${arr4.join(", ")}]`;
    console.log("스프레드 연산자 테스트:", arr3, arr4);
}

// 8. 삼항 연산자 테스트
function testTernary() {
    const age = 20;
    const status = age >= 18 ? "성인" : "미성년자";
    
    document.getElementById("ternary-result").innerHTML = 
        `나이: ${age}세<br>` +
        `상태: ${status}`;
    console.log("삼항 연산자 테스트:", status);
}

// 9. 옵셔널 체이닝 테스트
function testOptional() {
    const user1 = { name: "홍길동", address: { city: "서울" } };
    const user2 = { name: "김철수" };  // address 없음
    
    const city1 = user1.address?.city;
    const city2 = user2.address?.city;
    
    document.getElementById("optional-result").innerHTML = 
        `user1의 도시: ${city1 || "없음"}<br>` +
        `user2의 도시: ${city2 || "없음"}`;
    console.log("옵셔널 체이닝 테스트:", city1, city2);
}

// 페이지 로드 시 콘솔에 안내 메시지
console.log("=== ES6+ 문법 예제 ===");
console.log("각 버튼을 클릭하여 테스트하세요!");
