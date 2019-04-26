/* 주제: 객체 - this 변수의 활용 II
- this 변수를 내부 함수에서 사용하는 방법
*/
"use strict"

var obj1 = {
  v1: 100,
  f1() {
    console.log(this.v1)
    return function() {
      console.log(this)
      console.log('Hi~~~')
    }
  }
}

var f2 = obj1.f1()
f2()

var obj2 = {}
obj2.x = f2

obj2.x()





//
