/* 주제: 흐름 제어문 - 반복문 for.. in
- 배열 뿐만아니라 객체의 프로퍼티를 추출할 때 사용한다.
- 문법
for (var key in 배열 또는 객체) // 배열의 특정 범위만 반복할 수 없다. 무조건 전체를 반복한다.
  명령문;

for (var key in 배열 또는 객체) {
  명령문1;
  명령문2;
  ...
}
*/
"use strict"

var names = ["홍길동", "임꺽정", "유관순", "안중근", "윤봉길", "김구"];

for (var key in names) { // <-- 배열인 경우 key 라는 변수에는 배열의 인덱스가 저장된다.
  console.log(key, names[key]);
}
console.log("------------------------------");

// 다음 for 문은 index를 직접 다룰 수 있다. 그러나 for..in 은 불가능하다.
for (var i = 0; i < names.length; i += 2) {
  console.log(i, names[i]);
}
console.log("------------------------------");

// 그러나 for ..in은 객체를 다룰 수 있다.
// - 객체는 값을 저장할 때 숫자 인덱스 대신, 문자로된 key를 사용하여 값을 저장한다.
var student = {
  name: "홍길동",
  age: 20,
  tel: "1111-1111",
  email: "test@test.com"
};

for (var key in student) {
  console.log(key, student[key]);
}
console.log("------------------------------");










//
