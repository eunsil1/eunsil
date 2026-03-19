// 카운터 - Vanilla JavaScript

let count = 0;

const countEl = document.getElementById('count');
const btnDecrease = document.getElementById('btnDecrease');
const btnIncrease = document.getElementById('btnIncrease');
const btnReset = document.getElementById('btnReset');

function updateDisplay() {
    countEl.textContent = count;
}

function increase() {
    count++;
    updateDisplay();
}

function decrease() {
    count--;
    updateDisplay();
}

function reset() {
    count = 0;
    updateDisplay();
}

btnIncrease.addEventListener('click', increase);
btnDecrease.addEventListener('click', decrease);
btnReset.addEventListener('click', reset);

// 초기 표시
updateDisplay();
