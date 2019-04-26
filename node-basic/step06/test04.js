/* 주제: setTimeout(callback, delay[, ...args])
- 지정된 시간이 지나면 자동으로 호출될 함수를 등록하는 방법
  딱 한 번만 호출된다.
- callback : 지정된 시간이 지났을 때 호출될 함수 주소
- delay : 타임아웃 시간(밀리초)
- ...args : callback을 호출할 때 넘겨줄 값들
*/
"use strict"

// 1초 뒤에 호출될 함수를 심는다.
function f1() {
  console.log('1초 지났음!')
}
setTimeout(f1, 1000) // 비동기 방식 호출. 1초 후에 호출될 함수를 등록 후 바로 리턴한다.
console.log('첫 번째 타임아웃 함수를 심었다.')

setTimeout(function() { // 비동기 방식 호출. 3초 후에 호출될 함수를 등록 후 바로 리턴한다.
  console.log('3초 지났음!')
}, 3000)
console.log('두 번째 타임아웃 함수를 심었다.')





//
