/* HTTP 서버 만들기 - Single 스레드 방식
=> 한 번에 한 클라이언트 요청만 처리.
  즉 한 클라이언트의 요청 처리가 끝나야만 다음 클라이언트 요청을 처리한다.
=> 수 많은 사용자의 동시 요청을 처리하는 용도가 아니다.
=> 특정 한 개의 서비스를 순차적으로 빠르게 처리할 필요가 있을 때 사용한다.
*/

const http = require('http')
const server = http.createServer(function(request, response) {
  console.log('클라이언트 요청이 들어왔네!')

  // 요청이 들어오면 5초 후에 응답을 완료한다.
  setTimeout(function() {
    response.end();
  }, 5000)

})
server.listen(8888)

console.log("서버 실행 중...")
