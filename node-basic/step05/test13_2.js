/* 주제: 객체 - this 변수의 활용 II
- this 변수를 내부 함수에서 사용하는 방법
*/
"use strict"

var obj1 = {}
obj1.v1 = 100
obj1.f1 = function() {
  //this = 호출할 때 앞에 준 객체
  console.log(this)
  console.log(this.v1)
}

obj1.f1()

function f2() {
  console.log(this)
  console.log('hello')
}

f2()





//
