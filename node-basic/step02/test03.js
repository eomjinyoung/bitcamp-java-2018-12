/* 주제: 연산자 - 비교 연산자 II
- == vs ===
*/
"use strict"
var a = 7;
var b = "7";

/* "==" 연산자
- 두 값의 타입이 다를 경우 자동으로 타입의 변환(형변환;type conversion = type casting)이 일어난다.
  이렇게 개발자의 의사와 상관없이 내부에서 자동으로 타입이 바뀌는 것을
  "암시적 형변환"이라 부른다.
- 규칙:
  두 값의 타입이 다르고 그 중 하나가 number이면,
  다른 하나를 숫자로 타입으로 바꾼 후 비교한다.
*/
console.log(typeof a, typeof b);
console.log(a == b); // 7 == "7" --> 7 == 7 --> true
console.log(typeof a, typeof b);

/* "===" 연산자
- 같은 타입에 대해서만 비교를 수행한다.
- 타입이 다르면 무조건 false이다.
*/
console.log(a === b);  // false
console.log(a !== b);  // true

//
