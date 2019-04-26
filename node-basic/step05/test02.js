/* 주제: 객체 - 값 초기화시키기
- 문법
  var 변수명 = {프로퍼티명: 값, 프로퍼티명: 값, 프로퍼티명: 값, ...}
*/
"use strict"

// 배열 초기화
var names = ["홍길동", "임꺽정", "유관순"]
names[5] = "윤봉길"
console.log(names);
console.log('---------------------')

// 객체 초기화
var obj = {"name": "홍길동", "name2": "임꺽정", "name3": "유관순"}
obj.name4 = "안중근"
console.log(obj)

console.log(typeof names)
console.log(typeof obj)

// 객체를 초기화시킬 때 프로퍼티 이름은 그냥 적어도 된다.
// 물론 공백이나 특수 문자를 포함할 때는 반드시 큰 따옴표(""),
// 작은 따옴표('')로 감싸야 한다.
var obj2 = {name: "홍길동", name2: "임꺽정", name3: "유관순"}
console.log(obj2)






//
