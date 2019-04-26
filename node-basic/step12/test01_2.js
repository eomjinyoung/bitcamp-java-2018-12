/* HTTP 서버 만들기 - 클라이언트에게 응답하기
*/

const http = require('http')
const server = http.createServer(function(request, response) {
  console.log('클라이언트 요청이 들어왔네!')

  // HTTP 응답을 완료한다. 그래야만 클라이언트에게 응답을 완료한다.
  response.end();
})
server.listen(8888)

console.log("서버 실행 중...")
