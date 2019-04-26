/* 주제: 블록 변수 - let
- 변수가 선언된 블록 안에서만 사용할 수 있다.
- 문법
let 글로벌변수명;
*/
"use strict"
var name = "홍길동";
{
  let name = "임꺽정"; // 이 블록에서만 사용할 변수를 만든다.
  console.log(name);
}
console.log(name);

{
  let age = 20; // 이 블록에서만 사용할 변수를 만든다. 블록을 벗어나면 제거된다.
  console.log(age);
}
console.log(age); // age라는 변수는 없다. 실행 오류!




//
