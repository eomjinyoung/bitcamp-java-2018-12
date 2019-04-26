/* 주제: 객체 - read only 프로퍼티 만들기
- 프로퍼티(property)
  => 객체에 저장하는 값(숫자, 논리값, 함수, 객체, 문자열, ...)을 프로퍼티라 부른다.
- 일반적인 문법으로 프로퍼티를 만들면, read/write 모두 허용된다.
var obj = {
  프로퍼티명1: 값,
  프로퍼티명2: 값
}

obj.프로퍼티명3 = 값

function 생성자() {
  this.프로퍼티명 = 값
  ...
}

- 때로는 외부에서 값을 직접 조작하지 못하게 할 필요가 있다.
  그럴 때 read only 프로퍼티로 설정하는 것이다.

*/
"use strict"

var obj = {}
obj.result = 10;

/*
read only 프로퍼티 추가하기
- 객체를 전문적으로 조작할 수 있는 "Object"라는 도구가 있다.
- 그 도구의 기능(function)을 이용하여 read only 프로퍼티를 만들 수 있다.
Object.defineProperty(객체, 프로퍼티명, 옵션정보를 저장한 객체)
*/
Object.defineProperty(obj, 'result2', {
  value: '100'
})

console.log(obj.result)
console.log(obj.result2)
console.log('-----------------------')

obj.result = 20
console.log(obj.result)
obj.result2 = 200 // result2 는 read only 프로퍼티이기 때문에 값을 변경할 수 없다.
console.log(obj.result2)




//
