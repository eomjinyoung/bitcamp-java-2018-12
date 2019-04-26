/* 주제: 스태틱 타입 바인딩과 다이나믹 타입 바인딩
스태틱 타입 바인딩(static type binding)
  - 변수(메모리)의 용도를 고정하는 방식
  - 변수를 만들 때 선언한 용도대로만 값을 넣어야 한다.
  - 예) C, C++, C#, Java 등
다이나믹 타입 바인딩(dynamic type binding)
  - 변수(메모리)의 용도가 가변적이다.
  - 값을 넣는 순간 그 용도로 자동 설정된다.
  - 예) JavaScript 등 스크립트 류는 보통 동적 타입 바인딩 방식이다.
*/
"use strict"
var name;
console.log(typeof name);

name = "홍길동";
console.log(typeof name);

name = '임꺽정';
console.log(typeof name);

name = 20;
console.log(typeof name);

name = 3.14;
console.log(typeof name);

name = true;
console.log(typeof name);

name = new Object();
console.log(typeof name);

name = [];
console.log(typeof name);

name = function() {};
console.log(typeof name);

name = null;
console.log(typeof name);

name = undefined;
console.log(typeof name);

name = NaN;
console.log(typeof name);







//
