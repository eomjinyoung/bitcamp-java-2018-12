/* 주제: 함수 - 함수를 리턴 받기
- 함수를 값처럼 리턴 받을 수 있다.
*/
"use strict"

function calculator(op) {
  switch (op) {
  case "+":
    // 익명 함수를 정의한 후 그 익명 함수의 주소를 리턴한다.
    return function(a, b) {return a + b;};
  case "-":
    // 익명 함수를 정의한 후 그 익명 함수의 주소를 리턴한다.
    return function(a, b) {
      return a - b;
    };
  }
}

// + 계산을 수행하는 함수(의 주소)를 리턴 받는다.
var f = calculator("+");

// 변수에 함수(의 주소)가 들어 있다면, 함수처럼 사용할 수 있다.
var value = f(10, 20);
console.log(value);

f = calculator("-");
value = f(10, 20);
console.log(value);

// 실무에서는 리턴 받은 함수(의 주소)를 바로 사용하는 경우가 많다.
value = calculator("-")(10, 20);
console.log(value);

// 리턴 값을 바로 출력할 수 있다.
console.log(calculator("-")(10, 20));














//
