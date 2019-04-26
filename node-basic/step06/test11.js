/* 주제: Node.js에서 제공하는 글로벌 객체(built-in 객체)
*/
"use strict"

// node가 실행하는 파일의 디렉토리 경로
console.log(__dirname)

// node가 실행하는 파일의 경로(디렉토리 포함)
console.log(__filename)

// 'path' 모듈 사용
const path = require('path')

console.log(path.join('c:/workspace/', 'javascript01/', 'step05/', 'test01.js'))

// path.join() 사용의 이점:
// - 앞의 경로에 '/'가 붙었으면 뒤의 경로를 붙일 때 /를 안붙인다.
// - 앞의 경로에 '/'가 안붙었으면 뒤의 경로를 붙일 대 /를 자동으로 붙인다.
console.log(path.join(__dirname, 'test01.js'))

// - 경로를 붙일 때 상위 폴더로 이동하는 '..' 명령이나
//   현재 폴더를 의미하는 '.' 명령을 자동으로 처리하여 경로를 계산한다.
console.log(path.join(__dirname, '/../../electron01-bit/step04/main.js'))



//
