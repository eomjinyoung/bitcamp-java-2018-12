/* 주제: 글로벌 변수 - var
- 블록에 제한 받지 않는다.
- 문법
var 글로벌변수명;
- 블록 안에 선언해도 블록 밖에서 사용할 수 있다.
*/
"use strict"
var name = "홍길동";
{
  var name = "임꺽정"; //이전에 선언된 name 변수를 덮어쓴다.
  console.log(name);
}
console.log(name);

{
  var age = 20; // 글로벌 변수이다. 당연히 블록 밖에서 사용할 수 있다.
}
console.log(age);




//
