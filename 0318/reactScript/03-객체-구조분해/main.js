// 1. 객체 기본 문법 테스트
function testObject() {
    const person = {
        name: "홍길동",
        age: 25,
        city: "서울"
    };
    
    document.getElementById("object-result").innerHTML = 
        `이름: ${person.name}\n` +
        `나이: ${person.age}\n` +
        `도시: ${person.city}`;
    console.log("객체 테스트:", person);
}

// 2. 객체 구조 분해 테스트
function testDestructure() {
    const person = { name: "홍길동", age: 25, city: "서울" };
    const { name, age } = person;
    
    document.getElementById("destructure-result").innerHTML = 
        `구조 분해 후:\n` +
        `이름: ${name}\n` +
        `나이: ${age}`;
    console.log("구조 분해 테스트:", name, age);
}

// 3. 기본값 설정 테스트
function testDefaultValue() {
    const person = { name: "홍길동" };
    const { name, age = 0, city = "미정" } = person;
    
    document.getElementById("default-value-result").innerHTML = 
        `이름: ${name}\n` +
        `나이: ${age} (기본값)\n` +
        `도시: ${city} (기본값)`;
    console.log("기본값 테스트:", name, age, city);
}

// 4. 변수명 변경 테스트
function testRename() {
    const person = { name: "홍길동", age: 25 };
    const { name: userName, age: userAge } = person;
    
    document.getElementById("rename-result").innerHTML = 
        `원본: name=${person.name}, age=${person.age}\n` +
        `변경 후: userName=${userName}, userAge=${userAge}`;
    console.log("변수명 변경 테스트:", userName, userAge);
}

// 5. 중첩 객체 구조 분해 테스트
function testNested() {
    const user = {
        name: "홍길동",
        address: {
            city: "서울",
            district: "강남구"
        }
    };
    
    const { name, address: { city, district } } = user;
    
    document.getElementById("nested-result").innerHTML = 
        `이름: ${name}\n` +
        `도시: ${city}\n` +
        `구: ${district}`;
    console.log("중첩 구조 분해 테스트:", name, city, district);
}

// 6. 함수 매개변수에서 구조 분해 테스트
function testFunctionParam() {
    // 기존 방식
    function greet1(person) {
        return `안녕하세요, ${person.name}님!`;
    }
    
    // 구조 분해 사용
    function greet2({ name, age }) {
        return `안녕하세요, ${name}님! (${age}세)`;
    }
    
    const person = { name: "홍길동", age: 25 };
    const result1 = greet1(person);
    const result2 = greet2(person);
    
    document.getElementById("function-param-result").innerHTML = 
        `기존 방식: ${result1}\n` +
        `구조 분해: ${result2}`;
    console.log("함수 매개변수 테스트:", result1, result2);
}

// 7. 나머지 속성 테스트
function testRest() {
    const person = { name: "홍길동", age: 25, city: "서울", job: "개발자" };
    const { name, ...rest } = person;
    
    document.getElementById("rest-result").innerHTML = 
        `이름: ${name}\n` +
        `나머지 속성: ${JSON.stringify(rest)}`;
    console.log("나머지 속성 테스트:", name, rest);
}

// 8. 객체 스프레드 연산자 테스트
function testObjectSpread() {
    const person1 = { name: "홍길동", age: 25 };
    const person2 = { ...person1, city: "서울" };
    const person3 = { ...person1, age: 30 };
    
    document.getElementById("object-spread-result").innerHTML = 
        `원본: ${JSON.stringify(person1)}\n` +
        `속성 추가: ${JSON.stringify(person2)}\n` +
        `속성 덮어쓰기: ${JSON.stringify(person3)}`;
    console.log("객체 스프레드 테스트:", person1, person2, person3);
}

// 9. React 패턴 테스트
function testReactPattern() {
    // React 컴포넌트를 시뮬레이션
    function UserCard({ name, age, email }) {
        return `
            <div style="border: 1px solid #ddd; padding: 10px; margin: 5px 0;">
                <h3>${name}</h3>
                <p>나이: ${age}세</p>
                <p>이메일: ${email}</p>
            </div>
        `;
    }
    
    const user = { name: "홍길동", age: 25, email: "hong@test.com" };
    const cardHtml = UserCard(user);
    
    document.getElementById("react-pattern-result").innerHTML = 
        `<strong>사용자 카드:</strong>` + cardHtml;
    console.log("React 패턴 테스트:", user);
}

console.log("=== 객체와 구조 분해 예제 ===");
console.log("각 버튼을 클릭하여 테스트하세요!");
