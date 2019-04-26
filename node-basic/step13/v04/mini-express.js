/* 미니 Express 만들기 - GET, POST 요청을 다루는 함수를 관리하는 기능 추가
*/
var http = require('http')
var url = require('url')
var path = require('path')
var fs = require('fs')

var getHandler = {}
var postHandler = {}

function notFound(request, response) {
  response.writeHead(200, {
    'Content-Type' : 'text/html;charset=UTF-8'
  })

  fs.readFile(path.join(__dirname, './html/http-404.html'), function(err, data) {
    if (err) throw err
    response.end(data)
  })
}

var server = http.createServer(function(request, response) {
  var urlInfo = url.parse(request.url, true)

  var handler = null;
  if (request.method == 'GET') {
    handler = getHandler[urlInfo.pathname]
    request.query = urlInfo.query

  } else if (request.method == 'POST') {
    handler = postHandler[urlInfo.pathname]
  }

  if (handler) {
    handler(request, response)
  } else {
    notFound(request, response)
  }
})

module.exports = function() {
  return {
    // 주어진 url로 GET 요청이 들어왔을 때 호출될 함수를 등록한다.
    get(url, handler) {
      getHandler[url] = handler
    },

    // 주어진 url로 POST 요청이 들어왔을 때 호출될 함수를 등록한다.
    post(url, handler) {
      postHandler[url] = handler
    },

    // 웹서버를 시작시킨다.
    listen(port, cb) {
      server.listen(port, cb)
    }
  }
}
