/* 주제: 흐름 제어문 - 반복문 for
- 인덱스의 증가/감소에 따라 반복을 시킬 때 주로 사용한다.
- 보통 배열을 다룰 때 사용한다.
- 문법
for (변수초기화 문장; 조건식; 증가 또는 감소문)
  명령문;

for (변수초기화 문장; 조건식; 증가 또는 감소문) {
  명령문1;
  명령문2;
  ...
}
*/
"use strict"

for (var count = 1; count <= 10; count++) {
  console.log(count);
}
console.log("----------------------------");

for (var a = 2; a <= 9; a++) {
  for (var b = 1; b <= 9; b++) {
    console.log(a + " * " + b + " = " + (a * b));
  }
}
console.log("------------------------------");

var names = ["홍길동", "임꺽정", "유관순", "안중근", "윤봉길", "김구"];

for (var i = 0; i < names.length; i++) {
  console.log(names[i]);
}
console.log("------------------------------");












//
