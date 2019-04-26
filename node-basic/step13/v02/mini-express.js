/* 미니 Express 만들기 - 모듈 함수를 호출하면 객체 리턴
*/

module.exports = function() {
  return {
    get() {
      console.log('get()...')
    },
    post() {
      console.log('post()...')
    }
  }
}
