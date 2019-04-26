/* 주제: 객체 - 일반 객체와 함수 객체
- 일반 객체
  => 다음과 같이 new 또는 {}으로 만드는 객체
var obj = new 생성자()
var obj = {}
  => prototype이 없다.

- 함수 객체 = 일반 객체 + 함수 코드 + prototype
function 함수명() {}
var 함수명 = function() {}
*/
"use strict"

// 일반 객체
var obj1 = {
  name: '홍길동',
  age: 20
}
obj1.tel = '111-1111' // 객체에 값 보관
obj1.f1 = function() {console.log('hello')} // 객체에 함수 보관
obj1.ok = {} // 객체에 객체 보관

// 함수 객체
var obj2 = function() {
  console.log('test..')
}
obj2.tel = '222-2222' // 객체에 값 보관
obj2.f1 = function() {console.log('hi')} // 객체에 함수 보관
obj2.ok = {} // 객체에 객체 보관

// 함수 객체의 코드를 실행하고 싶다면, 다음과 같이 호출하라!
obj2()

/*
일반 객체나 함수 객체나 둘 다 객체의 기능을 갖고 있기 때문에
똑 같이 사용할 수는 있다.
하지만, 가능한 용도를 구분해서 사용하라!

일반 객체의 용도
- 일반적인 값 저장
- 그 값을 다루는 함수 저장

함수 객체의 용도
- 함수가 해야할 작업 코드를 저장
- 함수가 초기화시킨 인스턴스의 값들을 다루는 함수를 prototype에 저장
- 함수와 관련된 기능을 수행하는 다른 함수를 저장

*/




//
