/* 기존 코드에 새 서비스 추가
=> 'hello.do' 서비스를 추가하자!
  즉 else if 문을 추가해야 한다.
=> 문제점
  서비스를 추가할 때 마다 else if 문을 추가해야 한다.
  서비스가 추가 될 수록 소스 코드를 읽기 어려워진다.
=> 해결책?
  함수로 묶어 놓으면 읽기 쉽다.
  test07_3.js를 보라!
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

  } else if (urlInfo.pathname == '/hello.do') {
    response.write('안녕하세요!')
    response.end()

  } else {
    response.write('해당 URL을 지원하지 않습니다.')
    response.end()
  }
})

server.listen(8888)

console.log("서버 실행 중...")
