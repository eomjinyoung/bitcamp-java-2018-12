/* 주제: 연산자 - delete
- 객체에서 프로퍼티(변수나 함수 등)를 삭제할 때 사용한다.
*/
"use strict"

var v1 = 100
console.log(v1);

//delete v1 // 실행 오류! 객체에 소속되지 않은 변수는 delete을 사용할 수 없다.

console.log('-------------------------')
var obj = {}
obj.v1 = 100
console.log(obj.v1)

delete obj.v1 // 객체에 소속된 변수는 delete을 사용하여 제거할 수 있다.
console.log(obj.v1)








//
