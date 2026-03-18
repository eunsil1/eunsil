// 1. map() 테스트
function testMap() {
    const numbers = [1, 2, 3, 4, 5];
    const doubled = numbers.map(num => num * 2);
    
    document.getElementById("map-result").innerHTML = 
        `원본: [${numbers.join(", ")}]\n` +
        `결과: [${doubled.join(", ")}]`;
    console.log("map 테스트:", doubled);
}

// 2. filter() 테스트
function testFilter() {
    const numbers = [1, 2, 3, 4, 5, 6];
    const evens = numbers.filter(num => num % 2 === 0);
    
    document.getElementById("filter-result").innerHTML = 
        `원본: [${numbers.join(", ")}]\n` +
        `짝수만: [${evens.join(", ")}]`;
    console.log("filter 테스트:", evens);
}

// 3. find() 테스트
function testFind() {
    const users = [
        { id: 1, name: "홍길동" },
        { id: 2, name: "김철수" },
        { id: 3, name: "이영희" }
    ];
    const user = users.find(u => u.id === 2);
    
    document.getElementById("find-result").innerHTML = 
        `ID가 2인 사용자: ${JSON.stringify(user)}`;
    console.log("find 테스트:", user);
}

// 4. forEach() 테스트
function testForEach() {
    const fruits = ["사과", "바나나", "오렌지"];
    let result = "";
    fruits.forEach((fruit, index) => {
        result += `${index + 1}. ${fruit}\n`;
    });
    
    document.getElementById("forEach-result").innerHTML = result;
    console.log("forEach 테스트 완료");
}

// 5. reduce() 테스트
function testReduce() {
    const numbers = [1, 2, 3, 4, 5];
    const sum = numbers.reduce((acc, num) => acc + num, 0);
    const product = numbers.reduce((acc, num) => acc * num, 1);
    
    document.getElementById("reduce-result").innerHTML = 
        `배열: [${numbers.join(", ")}]\n` +
        `합계: ${sum}\n` +
        `곱: ${product}`;
    console.log("reduce 테스트:", sum, product);
}

// 6. some() / every() 테스트
function testSomeEvery() {
    const numbers = [1, 2, 3, 4, 5];
    const hasEven = numbers.some(num => num % 2 === 0);
    const allPositive = numbers.every(num => num > 0);
    const allEven = numbers.every(num => num % 2 === 0);
    
    document.getElementById("some-every-result").innerHTML = 
        `배열: [${numbers.join(", ")}]\n` +
        `짝수가 하나라도 있나? ${hasEven}\n` +
        `모두 양수인가? ${allPositive}\n` +
        `모두 짝수인가? ${allEven}`;
    console.log("some/every 테스트:", hasEven, allPositive, allEven);
}

// 7. includes() 테스트
function testIncludes() {
    const fruits = ["사과", "바나나", "오렌지"];
    const hasApple = fruits.includes("사과");
    const hasGrape = fruits.includes("포도");
    
    document.getElementById("includes-result").innerHTML = 
        `배열: [${fruits.join(", ")}]\n` +
        `사과가 있나? ${hasApple}\n` +
        `포도가 있나? ${hasGrape}`;
    console.log("includes 테스트:", hasApple, hasGrape);
}

// 8. 체이닝 테스트
function testChaining() {
    const numbers = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
    const result = numbers
        .filter(num => num % 2 === 0)  // 짝수만: [2, 4, 6, 8, 10]
        .map(num => num * 2)            // 2배: [4, 8, 12, 16, 20]
        .filter(num => num > 10);       // 10보다 큰 수만: [12, 16, 20]
    
    document.getElementById("chaining-result").innerHTML = 
        `원본: [${numbers.join(", ")}]\n` +
        `1단계 (짝수만): [2, 4, 6, 8, 10]\n` +
        `2단계 (2배): [4, 8, 12, 16, 20]\n` +
        `3단계 (10보다 큰 수만): [${result.join(", ")}]`;
    console.log("체이닝 테스트:", result);
}

// 9. React 패턴 테스트
function testReactPattern() {
    const items = [
        { id: 1, name: "사과", price: 1000 },
        { id: 2, name: "바나나", price: 2000 },
        { id: 3, name: "오렌지", price: 1500 }
    ];
    
    // React에서 사용하는 방식 (HTML로 시뮬레이션)
    const itemList = items.map(item => 
        `<div style="padding: 5px; border-bottom: 1px solid #ddd;">
            ${item.name} - ${item.price.toLocaleString()}원
        </div>`
    ).join("");
    
    document.getElementById("react-pattern-result").innerHTML = 
        `<strong>상품 목록:</strong><br>` + itemList;
    console.log("React 패턴 테스트:", items);
}

console.log("=== 배열 메서드 예제 ===");
console.log("각 버튼을 클릭하여 테스트하세요!");
