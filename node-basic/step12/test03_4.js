/* 요청 정보 다루기 - URL 분석기 사용 II
=> query 문자열에서 특정 이름의 값만 추출하기
*/

const http = require('http')
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
  // => parse()의 두 번째 파라미터 값을 true로 설정하면,
  //    개발자가 뽑아 쓰기 쉽도록 query 문자열을 잘라서 정리해둔다.
  var urlInfo = url.parse(request.url, true)
  console.log('query=', urlInfo.query)

  // query 문자열에서 v1, v2, v3 값을 따로 추출하기
  console.log('v1=', urlInfo.query.v1)
  console.log('v2=', urlInfo.query.v2)
  console.log('v3=', urlInfo.query.v3)

  response.writeHead(200, {
    'Content-Type' : 'text/plain;charset=UTF-8'
  })
  response.write('안녕하세요!')
  response.end();
})
server.listen(8888)

console.log("서버 실행 중...")
