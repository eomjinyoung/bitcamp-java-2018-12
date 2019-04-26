/* 여러 URL 처리
=> 조건문을 사용하여 URL을 구분한다.
=> 테스트 방법
1) test07_1.js를 실행하여 웹서버를 구동하라!
2) test07_1.html을 웹브라우저로 로딩하라!
3) 화면에서 GET 요청 또는 POST 요청 버튼을 눌러봐라!
4) 서버의 응답 결과를 확인하라!
*/

const http = require('http')
const url = require('url')
const qs = require('querystring')

const server = http.createServer(function(request, response) {
  var urlInfo = url.parse(request.url, true)

  response.writeHead(200, {
    'Content-Type' : 'text/plain;charset=UTF-8'
  })

  if (urlInfo.pathname == '/get.do') {
    // GET 요청으로 들어온 파라미터 값을 꺼내 출력한다.
    response.write('GET 요청 처리!\n')
    response.write('name=' + urlInfo.query.name + '\n')
    response.write('age=' + urlInfo.query.age + '\n')
    response.write('tel=' + urlInfo.query.tel + '\n')
    response.end()

  } else if (urlInfo.pathname == '/post.do') {
    var buf = ''
    // POST 요청으로 들어오는 데이터를 모은다.
    request.on('data', (data) => {
      buf += data
    })
    // 데이터를 모두 받았으면 처리한다.
    request.on('end', () => {
      var params = qs.parse(buf)
      response.write('POST 요청 처리!\n')
      response.write('name=' + params.name + '\n')
      response.write('age=' + params.age + '\n')
      response.write('tel=' + params.tel + '\n')
      response.end()
    })

  } else {
    response.write('해당 URL을 지원하지 않습니다.')
    response.end()
  }
})

server.listen(8888)

console.log("서버 실행 중...")
