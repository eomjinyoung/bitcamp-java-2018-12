/* 요청 정보 다루기 - URL 분석기 사용
*/

const http = require('http')

// URL 분석기가 들어있는 모듈 로딩
const url = require('url')

const server = http.createServer(function(request, response) {
  if (request.url == '/favicon.ico') {
    response.end();
    return;
  }
  console.log('클라이언트 요청이 들어왔네!')

  console.log('url=', request.url)

  // URL 분석기를 통해 url을 분석한다.
  // URL 예) http://localhost:8888/aaa/bbb/ccc.html?v1=xxx&v2=yyyy&v3=zzzz
  var urlInfo = url.parse(request.url)

  console.log('href=', urlInfo.href)
  console.log('pathname=', urlInfo.pathname)
  console.log('search=', urlInfo.search)
  console.log('query=', urlInfo.query)

  response.writeHead(200, {
    'Content-Type' : 'text/plain;charset=UTF-8'
  })
  response.write('안녕하세요!')
  response.end();
})
server.listen(8888)

console.log("서버 실행 중...")
