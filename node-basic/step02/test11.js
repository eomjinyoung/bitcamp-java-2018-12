/* 주제: 연산자 - 콤마 연산자
- 명령문을 연속해서 작성할 때 사용
- 문법
식, 식, 식;
*/
"use strict"

var a, b = 20, c;
console.log(a, b, c);

var r;
r = 100 * 2, 5 / 3, 4 + 7; // 콤마는 왼쪽에서 오른쪽으로 순차적으로 실행한다.
console.log(r)

function f() {
  return 5, 7, 8; // 콤마는 왼쪽에서 오른쪽으로 실행하기 때문에 마지막 값이 출력된다.
}

console.log(f());








//
