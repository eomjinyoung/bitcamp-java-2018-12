/* 미니 Express 만들기 - POST 요청 시 파라미터 데이터를 처리하는 코드 추가
*/
var http = require('http')
var url = require('url')
var path = require('path')
var fs = require('fs')
var qs = require('querystring')

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

function findHandler(method, servicePath) {
  if (method == 'GET') {
    return getHandler[servicePath]
  } else if (method == 'POST') {
    return postHandler[servicePath]
  }
  return null
}

var server = http.createServer(function(request, response) {
  var urlInfo = url.parse(request.url, true)

  var handler = findHandler(request.method, urlInfo.pathname);

  if (handler) {
    if (request.method == 'GET') {
      request.query = urlInfo.query
      handler(request, response)

    } else if (request.method == 'POST') {
      var queryString = ''
      request.on('data', function(data) {
        queryString += data
      })
      request.on('end', function() {
        request.query = qs.parse(queryString)
        handler(request, response)
      })
    }
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
