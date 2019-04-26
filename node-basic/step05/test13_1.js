/* 주제: 객체 - this 변수의 활용
- this 변수의 용도
  객체에 저장된 변수나 함수를 사용할 때 this(객체 주소)가 필요하다.
*/
"use strict"

/* 객체 생성 방법1) 빈 객체를 만든 후 값을 저장하는 방법
var obj = {}
obj.v1 = 0
obj.f1 = function() {
  v1 = 100
}
*/

// 객체 생성 방법2) 객체를 생성할 때 바로 값을 설정하는 방법
var obj = {
  v1: 0,
  f1: function() {
    // 이 함수가 소속된 객체의 v1 변수에 값을 저장해보자!
    this.v1 = 100
  }
}

obj.f1()
console.log(obj.v1)
console.log('-----------------------')





//
