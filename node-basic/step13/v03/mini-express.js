/* 미니 Express 만들기 - 웹서버를 만드는 코드를 캡슐화 한다. 
*/
var http = require('http')

var server = http.createServer(function(request, response) {
  response.end()
})

module.exports = function() {
  return {
    listen(port, cb) {
      server.listen(port, cb) // port 번호로 서버를 시작시킨다.
                              // 서버가 가동되면 'listening' 이벤트가 발생하고
                              // 지정된 함수 'cb'가 호출된다.
                              // 물론 cb가 없다면, 호출되지 않는다.
                              // 즉 서버가 시작된 후 알림을 받고 싶다면,
                              // 두 번째 파라미터에 함수를 전달하라!
    }
  }
}
