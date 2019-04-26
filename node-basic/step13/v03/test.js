var miniExpress = require('./mini-express')

var app = miniExpress()

app.listen(8888, function() {
  console.log('서버가 시작되었습니다.')
})
