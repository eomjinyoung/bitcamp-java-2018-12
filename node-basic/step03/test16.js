/* 주제: 흐름 제어문 - 반복문 for.. of
- 배열 뿐만아니라  iterable objects
  (including Array, Map, Set, String, TypedArray, arguments object and so on)의
  값을 추출할 때 사용한다.
- for .. in 과의 차이점은 변수에 저장되는 것은 인덱스나 프로퍼티명이 아니라
  값이라는 점이다.
- 문법
for (var value in 배열 또는 iterable 객체)
  명령문;

for (var value in 배열 또는 iterable 객체) {
  명령문1;
  명령문2;
  ...
}
*/
"use strict"

var names = ["홍길동", "임꺽정", "유관순", "안중근", "윤봉길", "김구"];

for (var index in names) { // for..in 에서 변수에 저장되는 것은 인덱스 또는 프로퍼티명
  console.log(index);
}
console.log("------------------------------");

for (var value of names) { // for..of 에서 변수에 저장되는 것은 값이다.
  console.log(value);
}
console.log("------------------------------");








//
