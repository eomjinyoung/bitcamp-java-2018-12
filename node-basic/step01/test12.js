/* 주제: let 변수의 중복 선언
- var 변수는 중복 선언하면 기존 변수를 덮어쓰지만,
  let 변수는 중복 선언할 수 없다. 오류를 발생시킨다.
*/
"use strict"
var name = "홍길동";
var name = "임꺽정";
console.log(name);

let name2 = "홍길동";
let name2 = "임꺽정"; // 오류!
console.log(name2);



//
