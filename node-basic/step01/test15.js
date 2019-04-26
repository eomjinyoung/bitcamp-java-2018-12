/* 주제: 배열
- 한 번에 여러 개의 메모리를 준비하는 방법
- 변수명과 인덱스 번호를 사용하여 메모리의 위치를 제어한다.
- 인덱스는 0부터 시작한다.
- 여러 개의 값을 다루기 편하다. 특히 반복문을 사용해 값을 다루기 편하다.
- 문법
var names = [];  <--- 빈 배열 생성
names[0] = "홍길동";
*/
"use strict"
// 배열 사용 전
var kor = 100, eng = 100, math = 100;
var sum = kor + eng + math;
var aver = sum / 3;
console.log(kor, eng, math, sum, aver);

// 배열 사용 후
var score = []; // 배열을 사용하기 전에 반드시 빈 배열을 만들어야 한다.
score[0] = 90;
score[1] = 80;
score[2] = 70
score[3] = score[0] + score[1] + score[2];
score[4] = score[3] / 3;
console.log(score[0], score[1], score[2], score[3], score[4]);
//
