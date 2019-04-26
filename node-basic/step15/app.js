/* Express 프레임워크 적용 - 전통적인 웹 프로그래밍 방식
=> 서버에서 UI를 만들어 클라이언트로 보낸다.
*/
var express = require('express')
var bodyParser = require('body-parser')
var path = require('path')
var cons = require('consolidate');

var app = express()

// 웹브라우저가 요청하는 파일이 어떤 폴더에 있는지 지정한다.
app.use(express.static('public'))

// POST 요청 데이터 분석기를 등록한다.
// => 이 분석기를 등록해야만 POST 요청으로 들어온 데이터를 꺼낼 수 있다.
app.use(bodyParser.urlencoded({extended: true}))


app.engine('hbs', cons.handlebars) // 사용할 템플릿 엔진을 등록한다.
app.set('view engine', 'hbs') // 기본 템플릿 엔진으로 어떤 것을 사용할 지 지정한다.
app.set('views', path.join(__dirname, '/views')) // 템플릿 파일이 있는 위치를 지정한다.

// 서비스 라우터를 등록한다.
// => http://localhost:8888/student/.... 요청을 처리할 라우터를 등록한다.
app.use('/student', require('./control/student-control'))
// => http://localhost:8888/lecture/.... 요청을 처리할 라우터를 등록한다.
app.use('/lecture', require('./control/lecture-control'))

app.get('/', function(request, response) {
  response.render('index')
})

app.listen(8888, function() {
  console.log('서버가 시작되었습니다.')
})
