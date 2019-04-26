/* 주제: 흐름 제어문 - 반복문 while
- 조건에 따라 명령문을 반복하고 싶을 때 사용한다.
- 문법
while (조건식) 명령문; <-- 조건식의 결과가 true인 동안 명령문을 반복하여 실행한다.
                           조건식의 결과가 false라면 반복을 멈춘다.

while (조건식)
  명령문;

while (조건식) {
  명령문1;
  명령문2;
  ...
}
*/
"use strict"

var count = 1;

while (count <= 10)
  console.log(count++);
console.log("-------------------------");

// while 중첩하기
var a = 2;
while (a <= 9) {
  var b = 1;
  while (b <= 9) {
    console.log(a + " * " + b + " = " + (a * b));
    b++;
  }
  a++;
}
console.log("-------------------------");









//
