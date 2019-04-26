/* 주제: setInterval(callback, delay[, ...args])
- 지정된 시간이 지나면 자동으로 호출될 함수를 등록하는 방법
  clearInterval()을 호출하여 타이머를 멈출 때까지 계속 반복한다.
- callback : 지정된 시간이 지났을 때 호출될 함수 주소
- delay : 타임아웃 시간(밀리초)
- ...args : callback을 호출할 때 넘겨줄 값들
*/
"use strict"

var count = 0;

{
  let timer = setInterval(function() { // 비동기 방식 호출. 1초 후에 호출될 함수를 등록 후 바로 리턴한다.
    console.log('1초 지났음!')
    if (++count == 10)
      clearInterval(timer) // 지정한 타이머를 멈춘다.
  }, 1000)
}

var timer = 'ok' // let 변수는 덮어 쓸 수 없다. 즉 중복해서 선언할 수 없다.
//timer = 'ok'
console.log(timer)





//
