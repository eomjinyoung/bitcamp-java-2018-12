/* 주제: 배열 사용법 I
- 배열의 길이 알아내기
배열명.length

- 배열 초기화시키기
var 배열명 = [값, 값, 값, ...];

- 배열에 값을 추가하기
배열명[인덱스] = 값;

- 값 꺼내기
배열명[인덱스]

- 배열에는 서로 다른 종류의 값을 넣을 수 있다.
*/
"use strict"
var a1 = []; // 빈 배열 만들기
console.log(a1.length);

var a2 = [10, 20, 30, 40]; // 4개의 정수 값으로 배열 초기화시키기
console.log(a2.length);
console.log("----------------------");

a1[0] = 100;
a1[1] = 200;
console.log(a1.length);

// 중간 인덱스를 건너뛰고 값을 넣으면 가장 큰 인덱스까지가 배열 크기이다.
a1[100] = 300;
console.log(a1.length);
console.log("----------------------");

// 값 꺼내기
console.log(a1[0]);
console.log(a1[1]);
console.log(a1[50]); // 값을 넣지 않은 항목?
console.log(a1[101]); // 인덱스의 범위를 벗어난 항목?
console.log("----------------------");

// 배열에 다른 종류의 값을 섞어 넣기
a2[4] = "홍길동";
a2[5] = false;
a2[6] = function() {};
a2[7] = null;
a2[8] = {};

for (var i = 0; i < a2.length; i++) {
  console.log(i, ":", a2[i]);
}










//
