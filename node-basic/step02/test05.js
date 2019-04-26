/* 주제: 연산자 - 논리 연산자
- &&, ||, !
*/
"use strict"
var a = true,
    b = false;

console.log(a && b); // false
console.log(a || b); // true
console.log(!a); // false

// &&
// 두 값 모두 true 일 때만 true이다.
console.log(
  true && true,
  true && false,
  false && true,
  false && false);

// ||
// 둘 중 한 개라도 true이면 true를 리턴한다.
console.log(
  true || true,
  true || false,
  false || true,
  false || false);









//
