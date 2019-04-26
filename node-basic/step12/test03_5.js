/* 요청 정보 다루기 - URL 분석기 사용 III
1) URL pathname을 활용하여 요청을 구분하기
2) GET 요청의 파라미터 값 꺼내기
*/

const http = require('http')
const url = require('url')

const server = http.createServer(function(request, response) {
  if (request.url == '/favicon.ico') {
    response.end();
    return;
  }
  console.log('클라이언트 요청이 들어왔네!')

  response.writeHead(200, {
    'Content-Type' : 'text/plain;charset=UTF-8'
  })

  // URL 분석기 실행
  var urlInfo = url.parse(request.url, true)

  // URL에서 '?' 앞에 나오는 경로(http://localhost:8888 은 제외)
  if (urlInfo.pathname != '/hello') {
    response.end('잘못된 URL 입니다.')
    return
  }

  // 클라이언트가 name 이라는 이름으로 보낸 값을 꺼낸다.
  var name = urlInfo.query.name

  response.write(name + '님 안녕하세요!')
  response.end();
})
server.listen(8888)

console.log("서버 실행 중...")
