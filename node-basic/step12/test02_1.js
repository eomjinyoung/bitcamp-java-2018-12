/* 클라이언트로 출력하기
*/

const http = require('http')
const server = http.createServer(function(request, response) {
  console.log('클라이언트 요청이 들어왔네!')

  response.write('Hello, World!')
  response.write('안녕하세요!')
  response.end();
})
server.listen(8888)

console.log("서버 실행 중...")

/*
서버에서 결과를 보내면 클라이언트 출력한다.
그러나, 서버에서 보낸 데이터가 어떻게 인코딩된 데이터인지 알지 못하기 때문에
한글을 출력할 때 깨진다. 
*/
