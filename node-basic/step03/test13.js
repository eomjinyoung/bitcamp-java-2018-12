/* 주제: 흐름 제어문 - 반복문 do ~ while
- 명령문을 먼저 실행한 후 계속 실행할 지 말 지 여부를 조건에 따라 결정한다.
- do ~ while은 최소한 한 번은 실행한다.
- 문법
do 명령문; while (조건식); <-- 명령문을 실행한 후 조건식의 결과가
                              true라면 다시 명령문을 반복하여 실행한다.
                              false라면 반복을 멈춘다.

do
  명령문;
while (조건식);

do {
  명령문1;
  명령문2;
  ...
} while (조건식);
*/
"use strict"

var count = 1;

do
  console.log(count++);
while (count <= 10);

console.log("-------------------------");

// 여러 개의 문장을 묶을 때는 반드시 블록({})을 사용하라!
do {
  console.log(count++); // count가 10 보다 크더라도 최소 한 번은 실행한다.
  console.log(">");
} while (count <= 10);







//
