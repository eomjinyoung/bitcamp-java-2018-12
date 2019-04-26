/* GET 요청과 POST 요청의 데이터 다루기
=> GET 요청 데이터는 url에서 값을 꺼내면 된다.
  'url' 모듈의 분석기를 사용하여 값을 꺼낸다.
=> 테스트 하는 방법
1) test06_1.js 를 실행하여 웹서버를 가동시킨다.
2) 웹 브라우저에서 test06_1.html 파일을 로딩한다.
3) 입력폼에 값을 입력한 후 전송 버튼을 클릭한다.
4) test06_1.js의 응답 결과를 확인한다.
*/

const http = require('http')
const url = require('url')

const server = http.createServer(function(request, response) {
  var urlInfo = url.parse(request.url, true)
  if(urlInfo.pathname != '/get.do') {
    response.end()
    return
  }

  response.writeHead(200, {
    'Content-Type' : 'text/plain;charset=UTF-8'
  })
  // GET 요청으로 들어온 파라미터 값을 꺼내 출력한다.
  response.write('name=' + urlInfo.query.name + '\n')
  response.write('age=' + urlInfo.query.age + '\n')
  response.write('tel=' + urlInfo.query.tel + '\n')
  response.end();
})

server.listen(8888)

console.log("서버 실행 중...")
